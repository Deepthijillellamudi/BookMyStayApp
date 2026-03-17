import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    synchronized void addRequest(Reservation r) {
        queue.add(r);
    }

    synchronized Reservation getRequest() {
        return queue.poll();
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    synchronized boolean allocateRoom(String type) {
        int available = inventory.getOrDefault(type, 0);

        if (available > 0) {
            inventory.put(type, available - 1);
            return true;
        }
        return false;
    }
}

class BookingProcessor extends Thread {
    BookingQueue queue;
    RoomInventory inventory;

    BookingProcessor(BookingQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        Reservation r = queue.getRequest();

        if (r != null) {
            boolean success = inventory.allocateRoom(r.roomType);

            if (success) {
                System.out.println("Booked for " + r.guestName + " (" + r.roomType + ")");
            } else {
                System.out.println("Failed for " + r.guestName + " (" + r.roomType + ")");
            }
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();

        queue.addRequest(new Reservation("Akhil", "Single Room"));
        queue.addRequest(new Reservation("Ravi", "Single Room"));
        queue.addRequest(new Reservation("Sita", "Double Room"));

        Thread t1 = new BookingProcessor(queue, inventory);
        Thread t2 = new BookingProcessor(queue, inventory);
        Thread t3 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();
        t3.start();
    }
}
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
    Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.add(r);
    }

    Reservation getNext() {
        return queue.poll();
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    void reduceRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

class BookingService {
    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
    private int roomCounter = 1;

    void process(BookingQueue queue, RoomInventory inventory) {
        Reservation r = queue.getNext();

        if (r == null) return;

        if (inventory.getAvailability(r.roomType) > 0) {

            String roomId = r.roomType.replace(" ", "") + roomCounter++;

            allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());

            if (!allocatedRooms.get(r.roomType).contains(roomId)) {
                allocatedRooms.get(r.roomType).add(roomId);
                inventory.reduceRoom(r.roomType);

                System.out.println("Booking Confirmed!");
                System.out.println("Guest: " + r.guestName);
                System.out.println("Room Type: " + r.roomType);
                System.out.println("Room ID: " + roomId);
                System.out.println();
            }
        } else {
            System.out.println("No rooms available for " + r.roomType);
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService();

        queue.addRequest(new Reservation("Akhil", "Single Room"));
        queue.addRequest(new Reservation("Ravi", "Double Room"));
        queue.addRequest(new Reservation("Sita", "Suite Room"));

        service.process(queue, inventory);
        service.process(queue, inventory);
        service.process(queue, inventory);
    }
}
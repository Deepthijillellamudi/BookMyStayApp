import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}

class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.add(r);
        System.out.println("Request added for " + r.guestName);
    }

    void displayQueue() {
        System.out.println("\nBooking Queue:");
        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        bookingQueue.addRequest(new Reservation("Akhil", "Single Room"));
        bookingQueue.addRequest(new Reservation("Ravi", "Double Room"));
        bookingQueue.addRequest(new Reservation("Sita", "Suite Room"));

        bookingQueue.displayQueue();
    }
}
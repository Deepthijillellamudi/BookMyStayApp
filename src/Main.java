import java.util.*;

class Reservation {
    String reservationId;
    String roomType;
    String roomId;

    Reservation(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    void increaseRoom(String type) {
        inventory.put(type, inventory.getOrDefault(type, 0) + 1);
    }

    void display() {
        System.out.println("Inventory: " + inventory);
    }
}

class CancellationService {
    private HashMap<String, Reservation> bookings = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();

    void addBooking(Reservation r) {
        bookings.put(r.reservationId, r);
    }

    void cancel(String reservationId, RoomInventory inventory) {
        if (!bookings.containsKey(reservationId)) {
            System.out.println("Invalid reservation ID");
            return;
        }

        Reservation r = bookings.get(reservationId);

        rollbackStack.push(r.roomId); // track released room
        inventory.increaseRoom(r.roomType); // restore inventory

        bookings.remove(reservationId);

        System.out.println("Cancelled: " + reservationId);
        System.out.println("Released Room ID: " + rollbackStack.peek());
    }
}

public class BookMyStay {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        service.addBooking(new Reservation("R101", "Single Room", "Single1"));
        service.addBooking(new Reservation("R102", "Double Room", "Double1"));

        service.cancel("R101", inventory);

        inventory.display();
    }
}
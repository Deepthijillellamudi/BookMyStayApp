import java.util.*;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, -1);
    }

    void reduceRoom(String type) throws InvalidBookingException {
        int available = getAvailability(type);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for " + type);
        }

        inventory.put(type, available - 1);
    }
}

class BookingValidator {
    static void validate(String roomType, RoomInventory inventory) throws InvalidBookingException {
        if (inventory.getAvailability(roomType) == -1) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (inventory.getAvailability(roomType) == 0) {
            throw new InvalidBookingException("Room not available: " + roomType);
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        String roomType = "Suite Room"; // try changing input

        try {
            BookingValidator.validate(roomType, inventory);
            inventory.reduceRoom(roomType);
            System.out.println("Booking successful for " + roomType);
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("System continues running...");
    }
}
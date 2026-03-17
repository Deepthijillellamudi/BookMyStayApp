import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getAll() {
        return history;
    }
}

class BookingReportService {
    void generateReport(List<Reservation> history) {
        System.out.println("Booking History Report:");
        for (Reservation r : history) {
            r.display();
        }
        System.out.println("Total Bookings: " + history.size());
    }
}

public class BookMyStay {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService report = new BookingReportService();

        history.addReservation(new Reservation("R101", "Akhil", "Single Room"));
        history.addReservation(new Reservation("R102", "Ravi", "Double Room"));
        history.addReservation(new Reservation("R103", "Sita", "Suite Room"));

        report.generateReport(history.getAll());
    }
}
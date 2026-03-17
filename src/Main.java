import java.util.*;

class AddOnService {
    String name;
    double cost;

    AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {
    private HashMap<String, List<AddOnService>> serviceMap = new HashMap<>();

    void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    double calculateTotal(String reservationId) {
        double total = 0;
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.cost;
            }
        }
        return total;
    }

    void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services != null) {
            System.out.println("Services for Reservation " + reservationId + ":");
            for (AddOnService s : services) {
                System.out.println(s.name + " - " + s.cost);
            }
            System.out.println("Total Add-On Cost: " + calculateTotal(reservationId));
        } else {
            System.out.println("No services selected.");
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "R101";

        manager.addService(reservationId, new AddOnService("Breakfast", 200));
        manager.addService(reservationId, new AddOnService("WiFi", 100));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 500));

        manager.displayServices(reservationId);
    }
}
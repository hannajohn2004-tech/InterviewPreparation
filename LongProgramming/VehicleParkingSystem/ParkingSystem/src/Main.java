import java.util.*;

class Vehicle {
    String number;
    String type;

    Vehicle(String number, String type) {
        this.number = number;
        this.type = type;
    }
}

class Slot {
    int slotId;
    Vehicle vehicle;

    Slot(int slotId) {
        this.slotId = slotId;
        this.vehicle = null;
    }

    boolean isEmpty() {
        return vehicle == null;
    }

    void park(Vehicle v) {
        vehicle = v;
    }

    void remove() {
        vehicle = null;
    }
}

class ParkingLot {
    List<Slot> slots;

    ParkingLot(int size) {
        slots = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            slots.add(new Slot(i));
        }
    }

    void parkVehicle(Vehicle v) {
        for (Slot s : slots) {
            if (s.isEmpty()) {
                s.park(v);
                System.out.println("Parked at slot: " + s.slotId);
                return;
            }
        }
        System.out.println("Parking Full");
    }

    void removeVehicle(String number) {
        for (Slot s : slots) {
            if (!s.isEmpty() && s.vehicle.number.equals(number)) {
                s.remove();
                System.out.println("Vehicle removed from slot: " + s.slotId);
                return;
            }
        }
        System.out.println("Vehicle not found");
    }

    void display() {
        for (Slot s : slots) {
            if (s.isEmpty()) {
                System.out.println("Slot " + s.slotId + ": Empty");
            } else {
                System.out.println("Slot " + s.slotId + ": " + s.vehicle.number + " (" + s.vehicle.type + ")");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot lot = new ParkingLot(5);

        while (true) {
            System.out.println("1.Park 2.Remove 3.Display 4.Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter vehicle number: ");
                String num = sc.next();
                System.out.print("Enter vehicle type: ");
                String type = sc.next();
                lot.parkVehicle(new Vehicle(num, type));
            } else if (choice == 2) {
                System.out.print("Enter vehicle number: ");
                String num = sc.next();
                lot.removeVehicle(num);
            } else if (choice == 3) {
                lot.display();
            } else {
                break;
            }
        }
    }
}
package parkingLot;

import parkingLot.entities.*;
import parkingLot.service.ParkingLotSystem;
import parkingLot.strategy.fee.VehicleBasedFeeStrategy;

import java.util.Optional;

public class ParkingLotDemo {
    public static void main(String[] args) {
        ParkingLotSystem parkingLot = ParkingLotSystem.getInstance();

        // 1. Initialize the parking lot with floors and spots
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        floor1.addSpot(new ParkingSpot("F1-S2", VehicleSize.MEDIUM));
        floor1.addSpot(new ParkingSpot("F1-S3", VehicleSize.LARGE));

        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot("F2-S1", VehicleSize.MEDIUM));
        floor2.addSpot(new ParkingSpot("F2-S2", VehicleSize.MEDIUM));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        parkingLot.setFeeStrategy(new VehicleBasedFeeStrategy());

        // 2. Simulate vehicle entries
        System.out.println("\n--- Vehicle Entries ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        Vehicle bike = new Bike("B-1");
        Vehicle car = new Car("C-1");
        Vehicle truck = new Truck("T-1");

        Optional<ParkingTicket> bikeTicket = parkingLot.parkVehicle(bike);
        Optional<ParkingTicket> carTicket = parkingLot.parkVehicle(car);
        Optional<ParkingTicket> truckTicket = parkingLot.parkVehicle(truck);

        System.out.println("\n--- Availability after parking ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        // 3. Simulate another car entry (should go to floor 2)
        Vehicle car2 = new Car("C-2");
        Optional<ParkingTicket> car2Ticket = parkingLot.parkVehicle(car2);

        // 4. Simulate a vehicle entry that fails (no available spots)
        Vehicle bike2 = new Bike("B-2");
        Optional<ParkingTicket> bike2Ticket = parkingLot.parkVehicle(bike2);

        // 5. Simulate vehicle exits and fee calculation
        if (carTicket.isPresent()) {
            Optional<Double> feeOpt = parkingLot.unparkVehicle(car.getRegistrationNumber());
            if (feeOpt.isPresent()) {
                System.out.printf("Car C-1 unparked. Fee: $%.2f\n", feeOpt.get());
            }
        }

        System.out.println("\n--- Availability after one car leaves ---");
        floor1.displayAvailability();
        floor2.displayAvailability();
    }
}
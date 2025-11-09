package parkingLot.service;

import parkingLot.entities.ParkingFloor;
import parkingLot.entities.ParkingSpot;
import parkingLot.entities.ParkingTicket;
import parkingLot.entities.Vehicle;
import parkingLot.strategy.fee.FeeStrategy;
import parkingLot.strategy.fee.FlatRateFeeStrategy;
import parkingLot.strategy.parking.BestFirstStrategy;
import parkingLot.strategy.parking.ParkingStrategy;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotSystem {
    private static ParkingLotSystem instance;
    private final List<ParkingFloor> floors;
    private final Map<String, ParkingTicket> activeTickets;
    private ParkingStrategy parkingStrategy;
    private FeeStrategy feeStrategy;

    public ParkingLotSystem() {
        this.parkingStrategy = new BestFirstStrategy();
        this.feeStrategy = new FlatRateFeeStrategy();
        this.activeTickets = new ConcurrentHashMap<>();
        this.floors = new ArrayList<>();
    }

    public static ParkingLotSystem getInstance() {
        if (instance == null) {
            synchronized (ParkingLotSystem.class) {
                if (instance == null) {
                    instance = new ParkingLotSystem();
                }
            }
        }
        return instance;
    }

    public static void setInstance(ParkingLotSystem instance) {
        ParkingLotSystem.instance = instance;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public Map<String, ParkingTicket> getActiveTickets() {
        return activeTickets;
    }

    public ParkingStrategy getParkingStrategy() {
        return parkingStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public FeeStrategy getFeeStrategy() {
        return feeStrategy;
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void addFloor(ParkingFloor floor) {
        this.floors.add(floor);
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> spot = this.parkingStrategy.findSpot(floors, vehicle);

        if (spot.isPresent()) {
            ParkingSpot parkingSpot = spot.get();
            parkingSpot.parkVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(parkingSpot, vehicle, Instant.now());
            activeTickets.put(vehicle.getRegistrationNumber(), ticket);

            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getRegistrationNumber(), parkingSpot.getSpotId(), ticket.getTicketId());

            return Optional.of(ticket);
        }

        System.out.println("No available spot for " + vehicle.getRegistrationNumber());
        return Optional.empty();
    }

    public Optional<Double> unparkVehicle(String vehicleNumber) {
        ParkingTicket ticket = activeTickets.remove(vehicleNumber);
        if (ticket == null) {
            System.out.printf("No ticket found for vehicle %s\n", vehicleNumber);
            return Optional.empty();
        }

        ParkingSpot spot = ticket.getSpot();
        spot.unparkVehicle();
        ticket.setExitTimestamp();

        Double parkingFee = feeStrategy.calculateFee(ticket);
        return Optional.of(parkingFee);
    }
}

package parkingLot.strategy.parking;

import parkingLot.entities.ParkingFloor;
import parkingLot.entities.ParkingSpot;
import parkingLot.entities.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle);
}

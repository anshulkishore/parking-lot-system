package parkingLot.strategy.parking;

import parkingLot.entities.ParkingFloor;
import parkingLot.entities.ParkingSpot;
import parkingLot.entities.Vehicle;

import java.util.*;

public class FarthestFirstStrategy implements ParkingStrategy{
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
        List<ParkingFloor> reverseParkingFloors = new ArrayList<>(parkingFloors);
        Collections.reverse(reverseParkingFloors);

        for(ParkingFloor floor : reverseParkingFloors) {
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }

        return Optional.empty();
    }
}

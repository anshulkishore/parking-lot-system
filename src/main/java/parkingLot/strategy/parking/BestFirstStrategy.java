package parkingLot.strategy.parking;

import parkingLot.entities.ParkingFloor;
import parkingLot.entities.ParkingSpot;
import parkingLot.entities.Vehicle;

import java.util.List;
import java.util.Optional;

public class BestFirstStrategy implements ParkingStrategy{
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
        Optional<ParkingSpot> bestParkingSpot = Optional.empty();

        for(ParkingFloor floor : parkingFloors) {
            Optional<ParkingSpot> spotOnThisFloor = floor.findAvailableSpot(vehicle);
            if (spotOnThisFloor.isPresent()) {
                if (bestParkingSpot.isEmpty()) {
                    bestParkingSpot = spotOnThisFloor;
                } else {
                    if (spotOnThisFloor.get().getSpotSize().ordinal() < bestParkingSpot.get().getSpotSize().ordinal()) {
                        bestParkingSpot = spotOnThisFloor;
                    }
                }

            }
        }

        return bestParkingSpot;
    }
}

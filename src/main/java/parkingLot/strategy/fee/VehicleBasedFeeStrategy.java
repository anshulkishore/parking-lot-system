package parkingLot.strategy.fee;

import parkingLot.entities.ParkingTicket;
import parkingLot.entities.VehicleSize;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

public class VehicleBasedFeeStrategy implements FeeStrategy{

    private final Map<VehicleSize, Double> rateMap = Map.of(
            VehicleSize.SMALL, 10.0,
            VehicleSize.MEDIUM, 15.0,
            VehicleSize.LARGE, 20.0
    );
    @Override
    public double calculateFee(ParkingTicket ticket) {
        Instant entryTime = ticket.getEntryTimestamp();
        Instant exitTime = ticket.getExitTimestamp();
        long hours = Duration.between(entryTime, exitTime).toHours() + 1;

        return hours * rateMap.get(ticket.getVehicle().getSize());
    }
}

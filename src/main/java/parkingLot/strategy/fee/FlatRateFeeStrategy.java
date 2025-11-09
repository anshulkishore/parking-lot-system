package parkingLot.strategy.fee;

import parkingLot.entities.ParkingTicket;

import java.time.Duration;
import java.time.Instant;

public class FlatRateFeeStrategy implements FeeStrategy{
    private final double RATE_PER_HOUR = 10.0;
    @Override
    public double calculateFee(ParkingTicket ticket) {
        Instant entryTime = ticket.getEntryTimestamp();
        Instant exitTime = ticket.getExitTimestamp();
        long hours = Duration.between(entryTime, exitTime).toHours() + 1;
        return hours * RATE_PER_HOUR;
    }
}

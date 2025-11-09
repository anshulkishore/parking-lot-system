package parkingLot.strategy.fee;

import parkingLot.entities.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket ticket);
}

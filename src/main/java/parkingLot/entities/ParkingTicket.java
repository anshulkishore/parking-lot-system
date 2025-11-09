package parkingLot.entities;

import java.time.Instant;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketId;
    private final ParkingSpot spot;
    private final Vehicle vehicle;
    private final Instant entryTimestamp;
    private Instant exitTimestamp;

    public ParkingTicket(ParkingSpot spot, Vehicle vehicle, Instant entryTimestamp) {
        this.ticketId = UUID.randomUUID().toString();
        this.spot = spot;
        this.vehicle = vehicle;
        this.entryTimestamp = entryTimestamp;
        this.exitTimestamp = null;
    }

    public String getTicketId() {
        return ticketId;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Instant getEntryTimestamp() {
        return entryTimestamp;
    }

    public Instant getExitTimestamp() {
        return exitTimestamp;
    }

    public void setExitTimestamp() {
        this.exitTimestamp = Instant.now();
    }
}

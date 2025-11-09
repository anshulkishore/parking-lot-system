package parkingLot.entities;

public class ParkingSpot {
    private final String spotId;
    private final VehicleSize spotSize;
    private Boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public String getSpotId() {
        return spotId;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public Boolean isAvailable() {
        return !isOccupied;
    }

    public Boolean canFitVehicle(Vehicle vehicle) {
        VehicleSize vehicleSize = vehicle.getSize();
        switch (vehicleSize) {
            case SMALL: return spotSize == VehicleSize.SMALL;
            case MEDIUM: return spotSize == VehicleSize.MEDIUM || spotSize == VehicleSize.LARGE;
            case LARGE: return spotSize == VehicleSize.LARGE;
            default: return true;
        }
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public void unparkVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }
}

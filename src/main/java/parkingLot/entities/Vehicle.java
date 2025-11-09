package parkingLot.entities;

public abstract class Vehicle {
    private final VehicleSize size;
    private final String registrationNumber;

    protected Vehicle(VehicleSize size, String registrationNumber) {
        this.size = size;
        this.registrationNumber = registrationNumber;
    }

    public VehicleSize getSize() {
        return size;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}

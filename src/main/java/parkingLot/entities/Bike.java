package parkingLot.entities;

public class Bike extends Vehicle{
    public Bike(String registrationNumber) {
        super(VehicleSize.SMALL, registrationNumber);
    }
}

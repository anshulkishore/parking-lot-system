package parkingLot.entities;

public class Truck extends Vehicle{
    public Truck(String registrationNumber) {
        super(VehicleSize.LARGE, registrationNumber);
    }
}

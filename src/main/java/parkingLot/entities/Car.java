package parkingLot.entities;

public class Car extends Vehicle{
    public Car(String registrationNumber) {
        super(VehicleSize.MEDIUM, registrationNumber);
    }
}

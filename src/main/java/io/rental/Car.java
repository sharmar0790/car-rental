package io.rental;

public class Car {
    private final String make;
    private final String model;
    private final String registrationNumber;
    private final String rentalGroup;
    private final double costPerDay;

    public Car(String make, String model, String registrationNumber, String rentalGroup, double costPerDay) {
        this.make = make;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.rentalGroup = rentalGroup;
        this.costPerDay = costPerDay;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public double getCostPerDay() {
        return costPerDay;
    }
}

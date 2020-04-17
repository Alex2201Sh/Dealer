package com.github.alex.dealer.data;

public class Car {
    private Long carId;
    private String model;
    private String vinNumber;


    public Car(Long carId, String model, String vinNumber) {
        this.carId = carId;
        this.model = model;
        this.vinNumber = vinNumber;
    }

    public Long getCarId() {
        return carId;
    }

    public String getModel() {
        return model;
    }

    public String getRegNumber() {
        return vinNumber;
    }

}

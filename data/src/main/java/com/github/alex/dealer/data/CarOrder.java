package com.github.alex.dealer.data;

public class CarOrder {
    private Long orderId;
    private String user;
    private String date;
    private String vinNumber;

    public CarOrder(String user, String date, String vinNumber) {
        this.user = user;
        this.date = date;
        this.vinNumber = vinNumber;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public String getVinNumber() {
        return vinNumber;
    }
}

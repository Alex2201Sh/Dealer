package com.github.alex.dealer.data;

public class User {
    private Long id;
    private String first_name;
    private String last_name;
    private String phone;

    public User(Long id, String first_name, String last_name, String phoneNumber) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phoneNumber;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phone;
    }

}

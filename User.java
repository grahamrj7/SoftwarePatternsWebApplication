package com.example.store;

public class User {

    private String name;
    private String address;
    private String paymentMethod;

    public User(String name, String address, String paymentMethod) {
        this.name = name;
        this.address = address;
        this.paymentMethod = paymentMethod;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPaymentMethod() { return paymentMethod; }
}

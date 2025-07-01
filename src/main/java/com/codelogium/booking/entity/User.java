package com.codelogium.booking.entity;

public class User {
    int id;
    String fullName;
    String passportNumber;
    int balance;

    public User(int id, String fullName, String passportNumber, int balance) {
        this.id = id;
        this.fullName = fullName;
        this.passportNumber = passportNumber;
        this.balance = balance;
    }

    // copy constructor
    public User(User source) {
        this.id = source.getId();
        this.fullName = source.getFullName();
        this.passportNumber = source.getPassportNumber();
        this.balance = source.getBalance();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassportNumber() {
        return this.passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

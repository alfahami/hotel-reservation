package com.codelogium.booking.entity;

import java.time.LocalDate;
import java.time.Period;

public class Booking {
    
    User user;
    Room room;
    LocalDate checkin;
    LocalDate checkout;
    int duration; // to be automatically calculated


    public Booking(User user, Room room, LocalDate checkin, LocalDate checkout, int duration) {
        this.user = user;
        this.room = room;
        this.checkin = checkin;
        this.checkout = checkout;
        setDuration(duration); // implicity calling stayDuration in order to get the number of nights   
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckin() {
        return this.checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return this.checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = stayDuration(checkin, checkout);
    }

    // Calculate automatically the number of nights
    private int stayDuration(LocalDate checkin, LocalDate checkout) {
        return Period.between(checkin, checkout).getDays();
    }
}

package com.codelogium.booking.entity;

import java.time.LocalDate;
import java.time.Period;

public class Booking {
    String bookingNumber;
    User user;
    Room room;
    LocalDate checkIn;
    LocalDate checkOut;
    int duration; // to be automatically calculated


    public Booking(User user, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        setDuration(duration); // implicity calling stayDuration in order to get the number of nights   
    }

    public Booking(Booking source) {
        this.bookingNumber = source.bookingNumber;
        this.user = source.getUser();
        this.room = source.getRoom();
        this.checkIn = source.getCheckIn();
        this.checkOut = source.getCheckOut();
        this.duration = source.getDuration(); // implicity calling stayDuration in order to get the number of nights   
    }

    public String getBookingNumber() {
        return this.bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
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

    public LocalDate getCheckIn() {
        return this.checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return this.checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = stayDuration(checkIn, checkOut);
    }

    // Calculate automatically the number of nights
    private int stayDuration(LocalDate checkIn, LocalDate checkOut) {
        return Period.between(checkIn, checkOut).getDays();
    }
}

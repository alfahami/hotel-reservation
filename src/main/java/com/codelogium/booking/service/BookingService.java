package com.codelogium.booking.service;

import java.time.LocalDate;

import com.codelogium.booking.constants.RoomType;

public interface BookingService {

    void setRoom(int roomNumber, RoomType roomType, int rate);
    void bookRoom(int userId, int roomNumber, LocalDate checkIn, LocalDate checkOut);
    void printAll();
    void setUser(int userId, int balance);

    void printAllUser();
}

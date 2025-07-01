package com.codelogium.booking.service;

import java.time.LocalDate;

import com.codelogium.booking.constants.RoomType;
import com.codelogium.booking.entity.Room;
import com.codelogium.booking.repository.RoomRepository;
import com.codelogium.booking.repository.UserRepository;

public class BookingServiceImpl implements BookingService {
    
    private UserRepository userRepository;
    private RoomRepository roomRepository;

    @Override
    public void setRoom(int roomNumber, RoomType roomType, int rate) {
        Room room = roomRepository.findRoomByNumber(roomNumber);
        if(room != null) {
            room.setRoomNumber(roomNumber);
            // TODO: check for room type correctness
            room.setType(roomType);
            room.setRate(rate);

            // save the updated room in the arraylist
            roomRepository.updateRoom(roomNumber, room);
        }
    }

    @Override
    public void bookRoom(int userId, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bookRoom'");
    }

    @Override
    public void printAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printAll'");
    }

    @Override
    public void setUser(int userId, int balance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUser'");
    }

    @Override
    public void printAllUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printAllUser'");
    }
    
}

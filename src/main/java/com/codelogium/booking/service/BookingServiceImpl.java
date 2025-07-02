package com.codelogium.booking.service;

import java.time.LocalDate;

import com.codelogium.booking.constants.RoomType;
import com.codelogium.booking.entity.Booking;
import com.codelogium.booking.entity.Room;
import com.codelogium.booking.entity.User;
import com.codelogium.booking.repository.BookingRepository;
import com.codelogium.booking.repository.RoomRepository;
import com.codelogium.booking.repository.UserRepository;

public class BookingServiceImpl implements BookingService {
    
    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private BookingRepository bookingRepository;

    public BookingServiceImpl(UserRepository userRepository, RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

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
        else {
            // if the room doesn't exist we create it
            Room newRoom = new Room(roomNumber, roomType, rate); //TODO: handle Exception when the type is not correct

            // update room list
            roomRepository.createRoom(newRoom); 
        }
    }

    @Override
    public void bookRoom(int userId, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        User user = userRepository.retrieveUser(userId);
        Room room = roomRepository.findRoomByNumber(roomNumber);

        if(Booking.stayDuration(checkIn, checkOut) < 0) {
            System.out.println("Check out date can't be greater than checkin date");
        }

        else if(room.getRate() * Booking.stayDuration(checkIn, checkOut) > user.getBalance()) {
            //TODO: custom Exception 
            System.out.println("User doesn't have enough balance to book the room for such period");
            // throw new RuntimeException("User doesn't have enough balance to book the room for such period");
        } 
        else {
            Booking newBooking = new Booking(user, room, checkIn, checkOut);
            bookingRepository.createBooking(newBooking); 
        }
    }

    @Override
    public void printAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printAll'");
    }

    @Override
    public void setUser(int userId, int balance) {
        User user = userRepository.retrieveUser(userId);
        if(user != null) {
            user.setBalance(balance);
            userRepository.updateUser(userId, user);
        } 
        else {
            // Create the user if not found
            User newUser = new User(userId, null, null, balance);
            userRepository.createUser(newUser);
        }
    }

    @Override
    public void printAllUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printAllUser'");
    }
    
}

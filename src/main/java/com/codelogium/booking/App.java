package com.codelogium.booking;

import java.util.Scanner;

import com.codelogium.booking.constants.RoomType;
import com.codelogium.booking.repository.BookingRepository;
import com.codelogium.booking.repository.RoomRepository;
import com.codelogium.booking.repository.UserRepository;
import com.codelogium.booking.service.BookingService;
import com.codelogium.booking.service.BookingServiceImpl;

public class App 
{
    
    public static void main( String[] args )
    {

        // Setting up our ArrayList
         RoomRepository roomRepository = new RoomRepository();
         UserRepository userRepository = new UserRepository();
         BookingRepository bookingRepository = new BookingRepository();


         BookingService bookingService = new BookingServiceImpl(userRepository, roomRepository, bookingRepository);

         // Create 3 rooms
         bookingService.setRoom(1, RoomType.STANDARD, 1000);
         bookingService.setRoom(2, RoomType.JUNIOR, 2000);
         bookingService.setRoom(3, RoomType.SUITE, 3000);
    }

    
}

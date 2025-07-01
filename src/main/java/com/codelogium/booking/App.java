package com.codelogium.booking;

import java.time.LocalDate;

import com.codelogium.booking.constants.RoomType;
import com.codelogium.booking.entity.User;
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

         // Create 3 user
         User user1 = new User(1, "John Doe", "NBE111", 5000);
         User user2 = new User(2, "Jane Doe", "NBE222", 10000);
         User user3 = new User(3, "Smith Sailor", "NBE444", 50000);

         userRepository.createUser(user1);
         userRepository.createUser(user2);
         userRepository.createUser(user3);

         // Booking
         bookingService.bookRoom(1, 2, LocalDate.parse("2026-06-30"), LocalDate.parse("2026-07-07"));

    }

    
}

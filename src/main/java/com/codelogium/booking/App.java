package com.codelogium.booking;

import java.time.LocalDate;

import com.codelogium.booking.constants.RoomType;
import com.codelogium.booking.entity.User;
import com.codelogium.booking.repository.BookingRepository;
import com.codelogium.booking.repository.RoomRepository;
import com.codelogium.booking.repository.UserRepository;
import com.codelogium.booking.service.BookingService;
import com.codelogium.booking.service.BookingServiceImpl;

public class App {

    public static void main(String[] args) {

        // Setting up our ArrayList
        RoomRepository roomRepository = new RoomRepository();
        UserRepository userRepository = new UserRepository();
        BookingRepository bookingRepository = new BookingRepository();

        BookingService bookingService = new BookingServiceImpl(userRepository, roomRepository, bookingRepository);

        // Create 3 rooms
        bookingService.setRoom(1, RoomType.STANDARD, 1000);
        bookingService.setRoom(2, RoomType.JUNIOR, 2000);
        bookingService.setRoom(3, RoomType.SUITE, 3000);

        bookingService.printAll();

        // Create 3 user
        User user1 = new User(1, "John Doe", "NBE111", 5000);
        User user2 = new User(2, "Jane Doe", "NBE222", 10000);
        User user3 = new User(3, "Smith Sailor", "NBE444", 50000);

        userRepository.createUser(user1);
        userRepository.createUser(user2);
        userRepository.createUser(user3);

        bookingService.printAllUser();

        System.out.println("===============================================================\n");

        // User 1 books Room 2 from 30/06/2026 to 07/07/2026
        System.out.println("User 1 tries to book Room 2 from 30/06/2026 to 07/07/2026: ");
        bookingService.bookRoom(1, 2, LocalDate.parse("2026-06-30"), LocalDate.parse("2026-07-07"));

        // User 1 tries booking room 2 from 07/07/2026 to 30/06/2026
        System.out.println("\nUser 1 tries booking room 2 from 30/06/2026 to 07/07/2026: ");

        bookingService.bookRoom(1, 2, LocalDate.parse("2026-07-07"), LocalDate.parse("2026-06-30"));

        // User 1 tries booking room 1 from 07/07/2026 to 08/07/2026
        System.out.println("\nUser 1 tries booking room 1 from 07/07/2026 to 08/07/2026: ");

        bookingService.bookRoom(1, 1, LocalDate.parse("2026-07-07"), LocalDate.parse("2026-07-08"));

        // User 2 tries booking room 1 from 07/07/2026 to 09/07/2026
        System.out.println("\nUser 2 tries booking room 1 from 07/07/2026 to 30/06/2026: ");

        bookingService.bookRoom(2, 1, LocalDate.parse("2026-07-07"), LocalDate.parse("2026-07-09"));

        // User 2 tries booking room 1 from 07/07/2026 to 08/07/2026
        System.out.println("\nUser 2 tries booking room 1 from 07/07/2026 to 30/06/2026: ");

        bookingService.bookRoom(2, 3, LocalDate.parse("2026-07-07"), LocalDate.parse("2026-07-08"));

        System.out.println("\n===============================================================\n");

        // SetRoom(1, suite, 10000)
        bookingService.setRoom(1, RoomType.SUITE, 10000);

        bookingService.printAll();
        bookingService.printAllUser();

    }

}

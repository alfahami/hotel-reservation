package com.codelogium.booking.service;

import java.time.LocalDate;
import java.util.List;

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

    public BookingServiceImpl(UserRepository userRepository, RoomRepository roomRepository,
            BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void setRoom(int roomNumber, RoomType roomType, int rate) {
        Room room = roomRepository.findRoomByNumber(roomNumber);
        if (room != null) {
            room.setRoomNumber(roomNumber);
            // TODO: check for room type correctness
            room.setType(roomType);
            room.setRate(rate);

            // save the updated room in the arraylist
            roomRepository.updateRoom(roomNumber, room);
        } else {
            // if the room doesn't exist we create it
            Room newRoom = new Room(roomNumber, roomType, rate); // TODO: handle Exception when the type is not correct

            // update room list
            roomRepository.createRoom(newRoom);
        }
    }

    @Override
    public void bookRoom(int userId, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        User user = userRepository.retrieveUser(userId);
        Room room = roomRepository.findRoomByNumber(roomNumber);

        if (Booking.stayDuration(checkIn, checkOut) < 0) {
            System.out.println("Check out date can't be greater than checkin date");
        }

        else if (room.getRate() * Booking.stayDuration(checkIn, checkOut) > user.getBalance()) {
            // TODO: custom Exception
            System.out.println("User doesn't have enough balance to book the room for such period");
            // throw new RuntimeException("User doesn't have enough balance to book the room
            // for such period");
        } else {
            // Check for availability
            if (room.getIsAvailable()) {

                Booking newBooking = new Booking(user, room, checkIn, checkOut);
                // update user balance after room is booked
                int index = userRepository.getObjectIndex(userId);
                user.setBalance(user.getBalance() - room.getRate() * Booking.stayDuration(checkIn, checkOut));
                // update the arrayList with the new user
                userRepository.updateUser(index, user);

                bookingRepository.createBooking(newBooking);

                // Room is no longer available
                room.setIsAvailable(false);
                // save the updated room
                roomRepository.updateRoom(roomRepository.getRoomIndex(roomNumber), room);

            } else {
                System.err.println("Requested Room is not available");
            }
        }
    }

    @Override
    public void setUser(int userId, int balance) {
        User user = userRepository.retrieveUser(userId);
        if (user != null) {
            user.setBalance(balance);
            userRepository.updateUser(userId, user);
        } else {
            // Create the user if not found
            User newUser = new User(userId, null, null, balance);
            userRepository.createUser(newUser);
        }
    }

    @Override
    public void printAllUser() {
        List<User> users = userRepository.findAllUser();
        if (!users.isEmpty()) {
            System.out.printf("%36s", "\n\nALL REGISTERED USERS\n");
            // Print top border
            for (int i = users.size() - 1; i >= 0; i--) {
                System.out.print("+------------------------");
            }
            System.out.println("+");

            // Print headers
            for (int i = users.size() - 1; i >= 0; i--) {
                System.out.printf("| %-22s ", "User " + (i + 1));
            }
            System.out.println("|");

            // Print separator
            for (int i = users.size() - 1; i >= 0; i--) {
                System.out.print("+------------------------");
            }
            System.out.println("+");

            // Print ID row
            for (User user : users) {
                System.out.printf("| %-22s ", "- ID : " + user.getId());
            }
            System.out.println("|");

            // Print Full Name row
            for (User user : users) {
                System.out.printf("| %-22s ", "- Name : " + user.getFullName());
            }
            System.out.println("|");

            // Print Passport row
            for (User user : users) {
                System.out.printf("| %-22s ", "- Passport : " + user.getPassportNumber());
            }
            System.out.println("|");

            // Print Balance row
            for (User user : users) {
                System.out.printf("| %-22s ", "- Balance : $" + user.getBalance());
            }
            System.out.println("|");

            // Print bottom border
            for (int i = users.size() - 1; i >= 0; i--) {
                System.out.print("+------------------------");
            }
            System.out.println("+\n\n");
        } else {
            System.out.println("No users available.");
        }

    }

    @Override
    public void printAll() {
        // Print existing room
        printRooms(roomRepository.findAll());
    }

    private void printRooms(List<Room> rooms) {
        if (!rooms.isEmpty()) {
            System.out.printf("%36s", "\n\nALL REGISTERED ROOMS\n");
            // Print top border
            for (int i = 0; i < rooms.size(); i++) {
                System.out.print("+------------------------");
            }
            System.out.println("+");

            // Print headers
            for (int i = rooms.size() - 1; i >= 0; i--) {
                System.out.printf("| %-22s ", rooms.get(i).getId());
            }
            System.out.println("|");

            // Print separator
            for (int i = 0; i < rooms.size(); i++) {
                System.out.print("+------------------------");
            }
            System.out.println("+");

            // Print ID row
            for (int i = rooms.size() - 1; i >= 0; i--) {
                System.out.printf("| %-22s ", "- ID : " + (i + 1));
            }
            System.out.println("|");

            // Print Type row
            for (Room room : rooms) {
                System.out.printf("| %-22s ", "- Type : " + room.getType());
            }
            System.out.println("|");

            // Print Price row
            for (int i = rooms.size() - 1; i >= 0; i--) {
                System.out.printf("| %-22s ", "- Price/night : " + rooms.get(i).getRate());
            }
            System.out.println("|");

            // Print Availabilty row
            for (int i = rooms.size() - 1; i >= 0; i--) {
                System.out.printf("| %-22s ",
                        "- Availability : " + ((rooms.get(i).getIsAvailable() == true) ? "Yes" : "No"));
            }
            System.out.println("|");

            // Print bottom border
            for (int i = 0; i < rooms.size(); i++) {
                System.out.print("+------------------------");
            }
            System.out.println("+\n\n");
        } else {
            System.out.println("No rooms available.");
        }
    }

}

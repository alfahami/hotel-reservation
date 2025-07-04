package com.codelogium.booking.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        int index = roomRepository.getRoomIndex(roomNumber);
        if (room != null) {
            room.setRoomNumber(roomNumber);
            // TODO: check for room type correctness
            room.setType(roomType);
            room.setRate(rate);

            // save the updated room in the arraylist
            roomRepository.updateRoom(index, room);
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
            System.out.println("Error: Check out date can't be greater than checkin date");
        }

        else if (room.getRate() * Booking.stayDuration(checkIn, checkOut) > user.getBalance()) {
            // TODO: custom Exception
            System.out
                    .println("Error: User " + userId + " doesn't have enough balance to book the room for such period");
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
                System.out.println("Log: " + room.getId() + " is successfully booked by " + user.getFullName() + " for "
                        + newBooking.getDuration() + " night(s)");

            } else {
                System.err.println("Error: Requested Room is not available");
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
            System.out.printf("%49s", "ALL REGISTERED USERS\n");
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
            System.out.println("No users available.\n");
        }

    }

    @Override
    public void printAll() {
        // Print existing room
        printRooms(roomRepository.findAll());
        // Print existing bookings
        printBookings(bookingRepository.findAllBookings());
    }

    private void printBookings(List<Booking> bookings) {
        if (!bookings.isEmpty()) {
            System.out.printf("%42s", "EXISTING BOOKINGS\n");
            // Print top border
            for (int i = bookings.size() - 1; i >= 0; i--) {
                System.out.print("+--------------------------------");
            }
            System.out.println("+");

            // Print headers
            for (int i = bookings.size() - 1; i >= 0; i--) {
                System.out.printf("| %-30s ", "Booking " + (i + 1));
            }
            System.out.println("|");

            // Print separator
            for (int i = bookings.size() - 1; i >= 0; i--) {
                System.out.print("+--------------------------------");
            }
            System.out.println("+");

            // Print Booking Number row
            for (Booking booking : bookings) {
                System.out.printf("| %-30s ", "- Number : " + booking.getBookingNumber());
            }
            System.out.println("|");

            // Print User row
            for (Booking booking : bookings) {
                System.out.printf("| %-30s ", "- User : " + booking.getUser().getFullName());
            }
            System.out.println("|");

            // Print Room row
            for (Booking booking : bookings) {
                System.out.printf("| %-30s ", "- Room : " + booking.getRoom().getId());
            }
            System.out.println("|");

            // Print Check-in row
            for (Booking booking : bookings) {
                System.out.printf("| %-30s ",
                        "- Check-in : " + booking.getCheckIn().format(DateTimeFormatter.ofPattern("dd/MM/yyy")));
            }
            System.out.println("|");

            // Print Check-out row
            for (Booking booking : bookings) {
                System.out.printf("| %-30s ",
                        "- Check-out : " + booking.getCheckOut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
            System.out.println("|");

            // Print Duration row
            for (Booking booking : bookings) {
                System.out.printf("| %-30s ", "- Duration : " + booking.getDuration() + " night(s)");
            }
            System.out.println("|");

            // Print bottom border
            for (int i = bookings.size() - 1; i >= 0; i--) {
                System.out.print("+--------------------------------");
            }
            System.out.println("+\n\n");
        } else {
            System.out.println("No bookings available yet.\n");
        }
    }

    private void printRooms(List<Room> rooms) {
        if (!rooms.isEmpty()) {
            System.out.printf("%49s", "ALL REGISTERED ROOMS\n");
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
            for (int i = rooms.size() - 1; i >= 0; i--) {
                System.out.printf("| %-22s ", "- Type : " + rooms.get(i).getType());
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

package com.codelogium.booking.repository;

import java.util.ArrayList;

import com.codelogium.booking.entity.Booking;

public class BookingRepository {
    
    private ArrayList<Booking> datastore = new ArrayList<>();

    public Boolean createBooking(Booking booking) {
        return this.datastore.add(booking);
    }

    public Booking retrieveBooking(String bookingNumber) {
        return findBooking(bookingNumber);
    }

    public Booking updateBooking(String bookingNumber, Booking booking) {
        Booking retrievedBooking = findBooking(bookingNumber);
        return this.datastore.set(datastore.indexOf(retrievedBooking), booking);
    }

    public Boolean removeBooking(String bookingNumber) {
        return this.datastore.remove(findBooking(bookingNumber));
    }

    private Booking findBooking(String bookingNumber) {
        for (Booking booking : datastore) {
            if(booking.getBookingNumber().equals(bookingNumber)) return new Booking(booking);
        }

        return null; //TODO: custom Exception
    }
}

package com.codelogium.booking.repository;

import java.util.ArrayList;

import com.codelogium.booking.entity.Booking;

public class BookingRepository {
    
    private ArrayList<Booking> datastore = new ArrayList<>();

    public Boolean createBooking(Booking booking) {
        booking.setBookingNumber("Booking" + datastore.size()+ 1);
        return datastore.add(booking);
    }

    public Booking retrieveBooking(String bookingNumber) {
        return findBooking(bookingNumber);
    }

    public Booking updateBooking(String bookingNumber, Booking booking) {
        Booking retrievedBooking = findBooking(bookingNumber);
        return datastore.set(datastore.indexOf(retrievedBooking), booking);
    }

    public Boolean removeBooking(String bookingNumber) {
        return datastore.remove(findBooking(bookingNumber));
    }

    public int getBookingIndex(String bookingNumber) {
        for (int i = 0; i < datastore.size(); i++) {
            if(datastore.get(i).getBookingNumber().equals(bookingNumber)) return i;
        }
        return -1;
    }

    private Booking findBooking(String bookingNumber) {
        for (Booking booking : datastore) {
            if(booking.getBookingNumber().equals(bookingNumber)) return new Booking(booking);
        }

        return null; //TODO: custom Exception
    }
}

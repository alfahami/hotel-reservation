package com.codelogium.booking.entity;

import com.codelogium.booking.constants.RoomType;

public class Room {
    String id;
    int roomNumber;
    RoomType type;
    int rate;


    public Room(String id, int roomNumber, RoomType type, int rate) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.rate = rate;
    }

    // copy constructor
    public Room(Room source) {
        this.id = source.id;
        this.roomNumber = source.roomNumber;
        this.type = source.type;
        this.rate = source.rate;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getType() {
        return this.type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public int getRate() {
        return this.rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}

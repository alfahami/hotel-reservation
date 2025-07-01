package com.codelogium.booking.repository;

import java.util.ArrayList;

import com.codelogium.booking.entity.Room;

public class RoomRepository {
    
    private ArrayList<Room> datastore = new ArrayList<>();

    public Boolean createRoom(Room room) {
        return this.datastore.add(room);
    }

    public Room retrieveRoom(String id) {
        return findRoom(id);
    }

    public Boolean removeRoom(String id) {
        return datastore.remove(findRoom(id));
    }

    private Room findRoom(String id) {
        for (Room room : datastore) {
            if(room.getId().equals(id)) return new Room(room); // use deep copy to protect the integrity of the data stored in our arrayList
        }
        return null; // TODO: custom Exception
    }
}

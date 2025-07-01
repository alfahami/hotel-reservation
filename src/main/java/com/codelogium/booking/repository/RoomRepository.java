package com.codelogium.booking.repository;

import java.util.ArrayList;

import com.codelogium.booking.entity.Room;

public class RoomRepository {
    
    private ArrayList<Room> datastore = new ArrayList<>();

    public Boolean createRoom(Room room) {
        return this.datastore.add(room);
    }

    public Room retrieveRoom(String id) {
        return findRoomById(id);
    }

    // Update the room based on its room number
    public Room updateRoom(int roomNumber, Room updatedRoom) {
        Room room = findRoomByNumber(roomNumber);
       return  datastore.set(datastore.indexOf(room), updatedRoom); 
    }

    public Boolean removeRoom(String id) {
        return datastore.remove(findRoomById(id));
    }

    private Room findRoomById(String id) {
        for (Room room : datastore) {
            if(room.getId().equals(id)) return new Room(room); // use deep copy to protect the integrity of the data stored in our arrayList
        }
        return null; // TODO: custom Exception
    }

    public Room findRoomByNumber(int roomNumber) {
        for (Room room : datastore) {
            if(room.getRoomNumber() == roomNumber) return new Room(room);
        }

        return null; // TODO: custom exception
    }
}

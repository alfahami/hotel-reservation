package com.codelogium.booking.repository;

import java.util.ArrayList;

import com.codelogium.booking.entity.User;

public class UserRepository {
    
    private ArrayList<User> datastore = new ArrayList<>();

    public Boolean createUser(User user) {
        return this.datastore.add(user);
    }

    public User retrieveUser(String id) {

    }

    private User findUser(String id) {
        for (User user : datastore) {
            if(user.getId().equals(id)) return new User(user);
        }

        return null; // TODO: custom Exception
    }
}

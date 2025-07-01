package com.codelogium.booking.repository;

import java.util.ArrayList;

import com.codelogium.booking.entity.User;

public class UserRepository {
    
    private ArrayList<User> datastore = new ArrayList<>();

    public Boolean createUser(User user) {
        return this.datastore.add(user);
    }

    public User retrieveUser(int id) {
        return new User(findUser(id));
    }

    private User findUser(int id) {
        for (User user : datastore) {
            if(user.getId() == id) return new User(user);
        }

        return null; // TODO: custom Exception
    }
}

package com.codelogium.booking.repository;

import java.util.ArrayList;
import java.util.List;

import com.codelogium.booking.entity.User;

public class UserRepository {

    private ArrayList<User> datastore = new ArrayList<>();

    public Boolean createUser(User user) {
        return this.datastore.add(user);
    }

    public User retrieveUser(int id) {
        return new User(findUser(id));
    }

    public User updateUser(int index, User updatedUser) {
        return this.datastore.set(index, updatedUser);
    }

    public List<User> findAllUser() {
        // we could have made a deep copy of the arry but since it will only be used for
        // printing it's fine
        return datastore;
    }

    public int getObjectIndex(int id) {
        for (int i = 0; i < this.datastore.size(); i++) {
            if (datastore.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    private User findUser(int id) {
        for (User user : datastore) {
            if (user.getId() == id)
                return new User(user);
        }

        return null; // TODO: custom Exception
    }
}

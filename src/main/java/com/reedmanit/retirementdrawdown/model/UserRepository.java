package com.reedmanit.retirementdrawdown.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserRepository {

    private static List<User> users;

    public UserRepository() {
        setUp();
    }

    public void setUp() {
        users = new ArrayList<User>();

        User paul = new User("paul", "pulsar", Role.USER);
        users.add(paul);
        User jenny = new User("jenny", "pulsar", Role.USER);
        users.add(jenny);
    }

    public static User findUser(String username) {
        Iterator<User> it = users.iterator();
        boolean found = false;
        User user = null;
        while (it.hasNext()) {
            user = it.next();
            if (user.getUsername().equals(username)) {
                found = true;
                break;
            }
        }
        System.out.println("found " + found);
        if (found) {
            return user;
        } else {
            return null;
        }
    }

    public List<User> getUsers() {
        return users;
    }

}

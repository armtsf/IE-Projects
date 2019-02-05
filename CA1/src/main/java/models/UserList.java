package models;

import java.util.ArrayList;

public class UserList {
    private static ArrayList<User> users = new ArrayList<>();

    public static void add(User user) {
        users.add(user);
    }
}

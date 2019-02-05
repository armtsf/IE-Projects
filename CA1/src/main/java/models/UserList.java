package models;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserList {
    private static ArrayList<User> users = new ArrayList<>();

    public static void add(User user) {
        users.add(user);
    }

    public static User get(final String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}

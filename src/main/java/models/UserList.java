package models;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserList {
    private static ArrayList<User> users = new ArrayList<>();

    public static void add(User user) throws InvalidObjectException {
        for (User u: users) {
            if (u.getId().equals(user.getId())) {
                throw new InvalidObjectException("user with the same id exists");
            }
        }
        users.add(user);
    }

    public static User get(final String username) {
        return users.stream().filter(user -> user.getId().equals(username)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}

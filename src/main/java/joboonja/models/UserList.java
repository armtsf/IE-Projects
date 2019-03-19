package joboonja.models;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public static User get(final String userId) {
        return users.stream().filter(user -> user.getId().equals(userId)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public static ArrayList<User> getAllExcept(User user) {
        return users.stream().filter(u -> !u.getId().equals(user.getId())).collect(Collectors.toCollection(ArrayList::new));
    }
}

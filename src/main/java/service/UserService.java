package service;

import models.User;
import models.UserList;

import java.util.ArrayList;

public class UserService {

    public static User getUser(String userId) {
        User requestedUser = UserList.get(userId);
        return requestedUser;
    }

    public static ArrayList<User> getUsersList(User user) {
        ArrayList<User> userList = UserList.getAllExcept(user);
        return userList;
    }

}

package service;

import models.User;
import models.UserList;

public class UserService {

    public static User getUser(String userId) {
        User requestedUser = UserList.get(userId);
        return requestedUser;
    }

}

package handlers;

import base.Session;
import com.sun.net.httpserver.HttpExchange;
import models.User;
import models.UserList;
import views.UserDetailsView;

import java.util.NoSuchElementException;

public class UserDetailsHandler extends ServiceHandler {
    @Override
    public HttpResponse handleRequest(HttpExchange t) {
        String userId = Session.get("userId");
        if (userId == null) {
            return new HttpResponse(401, "Unauthorized");
        }
        User user;
        try {
            user = UserList.get(userId);
        } catch (NoSuchElementException e) {
            return new HttpResponse(401, "Unauthorized");
        }

        String requestedUserId = getUriVars().get("id");
        try {
            User requestedUser = UserList.get(requestedUserId);
            HttpResponse response = new HttpResponse(200);
            String rendered = UserDetailsView.render(requestedUser);
            response.setBody(rendered);
            return response;
        }
        catch (NoSuchElementException e) {
            return new HttpResponse(404, "Not Found");
        }
    }
}

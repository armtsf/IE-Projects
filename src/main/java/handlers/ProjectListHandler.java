package handlers;

import base.Session;
import com.sun.net.httpserver.HttpExchange;
import models.*;
import views.ProjectListView;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProjectListHandler extends ServiceHandler {
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

        ArrayList<Project> result = new ArrayList<>();
        for (Project project: ProjectList.all()) {
            if (user.isEligibleFor(project)) {
                result.add(project);
            }
        }

        HttpResponse response = new HttpResponse(200);
        String rendered = ProjectListView.render(result);
        response.setBody(rendered);
        return response;
    }
}

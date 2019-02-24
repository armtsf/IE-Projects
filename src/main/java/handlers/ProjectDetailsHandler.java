package handlers;

import base.Session;
import com.sun.net.httpserver.HttpExchange;
import models.Project;
import models.ProjectList;
import models.User;
import models.UserList;
import views.ProjectDetailsView;

import java.util.NoSuchElementException;

public class ProjectDetailsHandler extends ServiceHandler {
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

        String projectId = getUriVars().get("id");
        try {
            Project project = ProjectList.get(projectId);
            if (user.isEligibleFor(project)) {
                HttpResponse response = new HttpResponse(200);
                String rendered = ProjectDetailsView.render(project);
                response.setBody(rendered);
                return response;
            }
            else {
                return new HttpResponse(403, "Forbidden");
            }
        }
        catch (NoSuchElementException e) {
            return  new HttpResponse(404, "Not Found");
        }
    }
}

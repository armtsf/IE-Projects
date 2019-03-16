package controller;

import models.Project;
import models.User;
import service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/project/*")
public class ProjectDetailsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] parts = req.getPathInfo().split("/");
        if (parts.length == 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String projectId = parts[1];
        User user = (User) req.getAttribute("user");
        try {
            Project requestedProject = ProjectService.getProjectDetails(user, projectId);
            req.setAttribute("project", requestedProject);
            req.getRequestDispatcher("/project.jsp").forward(req, resp);
        }
        catch (IllegalAccessException e) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

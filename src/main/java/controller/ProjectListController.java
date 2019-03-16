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
import java.util.ArrayList;

@WebServlet("/project")
public class ProjectListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        ArrayList<Project> projectList = ProjectService.getProjectList(user);
        req.setAttribute("projectList", projectList);
        req.getRequestDispatcher("projectList.jsp").forward(req, resp);
    }
}

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
import java.io.InvalidObjectException;

@WebServlet("/project/bid")
public class BidController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        String projectId = req.getParameter("id");
        long bidAmount = Long.parseLong(req.getParameter("bidAmount"));
        try {
            ProjectService.addBid(user, projectId, bidAmount);
            req.setAttribute("msg", "Bid added successfully.");
        }
        catch (InvalidObjectException e) {
            req.setAttribute("msg", "Bid is not valid.");
        }
        req.setAttribute("projectList", ProjectService.getProjectList(user));
        req.getRequestDispatcher("/projectList.jsp").forward(req, resp);
    }
}

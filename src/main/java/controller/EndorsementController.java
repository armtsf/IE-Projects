package controller;

import models.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/user/endorse")
public class EndorsementController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        String endorsee = req.getParameter("id");
        String skillName = req.getParameter("skill");
        try {
            UserService.endorse(user.getId(), endorsee, skillName);
            User requestedUser = UserService.getUser(endorsee);
            req.setAttribute("user", requestedUser);
            req.setAttribute("skills", UserService.getSkills(requestedUser, user));
            req.getRequestDispatcher("/user-guest.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (NoSuchElementException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

package controller;

import models.SkillNameList;
import models.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet("/user/*")
public class UserDetailsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] parts = req.getPathInfo().split("/");
        System.out.println(parts.length);
        if (parts.length == 0) {
            resp.setStatus(400);
            return;
        }
        String userId = parts[1];
        User currUser = (User) req.getAttribute("user");
        try {
            User requestedUser = UserService.getUser(userId);
            req.setAttribute("user", requestedUser);
            if (!currUser.getId().equals(userId)) {
                req.setAttribute("skills", UserService.getSkills(requestedUser, currUser));
                req.getRequestDispatcher("/user-guest.jsp").forward(req, resp);
            }
            else {
                req.setAttribute("availableSkills", SkillNameList.all());
                req.getRequestDispatcher("/user-logged-in.jsp").forward(req, resp);
            }
        }
        catch (NoSuchElementException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

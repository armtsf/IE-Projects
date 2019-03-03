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

@WebServlet("/user/add")
public class AddSkillController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        String skill = req.getParameter("skill");
        try {
            UserService.addSkill(user, skill);
            req.setAttribute("user", user);
        }
        catch (NoSuchElementException e) {
            req.setAttribute("msg", "Could not add skill.");
        }
        catch (IllegalArgumentException e) {
            req.setAttribute("msg", "Skill already exists.");
        }
        req.setAttribute("availableSkills", SkillNameList.all());
        req.getRequestDispatcher("/user-logged-in.jsp").forward(req, resp);
    }
}

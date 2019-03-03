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

@WebServlet("/user/delete")
public class DeleteSkillController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        String skill = req.getParameter("skill");
        UserService.deleteSkill(user, skill);
        req.setAttribute("user", user);
        req.setAttribute("availableSkills", SkillNameList.all());
        req.getRequestDispatcher("/user-logged-in.jsp").forward(req, resp);
    }
}

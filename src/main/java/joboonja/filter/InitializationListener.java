package joboonja.filter;

import joboonja.data.mappers.UserMapper;
import joboonja.utils.Session;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import joboonja.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitializationListener {
    private final String PROJECTS_ENDPOINT = "http://142.93.134.194:8000/joboonja/project";
    private final String SKILLS_ENDPOINT = "http://142.93.134.194:8000/joboonja/skill";

    private Logger logger = LoggerFactory.getLogger(InitializationListener.class);

    @EventListener(ApplicationReadyEvent.class)
    public void contextInitialized() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpResponse<String> jsonResponse = Unirest.get(SKILLS_ENDPOINT).asString();
            ArrayList<SkillName> skillNames = mapper.readValue(jsonResponse.getBody(), new TypeReference<List<SkillName>>(){});
            for (SkillName skillName: skillNames) {
                SkillNameList.add(skillName);
            }

            jsonResponse = Unirest.get(PROJECTS_ENDPOINT).asString();
            ArrayList<Project> projects = mapper.readValue(jsonResponse.getBody(), new TypeReference<List<Project>>(){});
            for (Project project: projects) {
                for (ProjectSkill skill: project.getSkills()) {
                    skill.setSkillName(SkillNameList.get(skill.getSkillName().getName()));
                    skill.setProject(project);
                }
                ProjectList.add(project);
            }
        } catch (UnirestException | IOException e) {
            e.printStackTrace();
        }

        User user1 = new User();
        user1.setId("1");
        user1.setFirstName("علی");
        user1.setLastName("شریف‌زاده");
        user1.setJobTitle("برنامه‌نویس وب");
        user1.setBio("روی سنگ قبرم بنویسید: خدا بیامرز می‌خواست خیلی کارا بکنه ولی پول نداشت");
        user1.setProfilePictureURL("http://localhost:8000/mine.jpg");
        ArrayList<UserSkill> skills = new ArrayList<>();
        skills.add(new UserSkill(SkillNameList.get("HTML"), 5));
        skills.add(new UserSkill(SkillNameList.get("Javascript"), 4));
        skills.add(new UserSkill(SkillNameList.get("C++"), 2));
        skills.add(new UserSkill(SkillNameList.get("Java"), 3));
        user1.setSkills(skills);
        try {
            UserList.add(user1);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
        Session.put("userId", user1.getId());

        User user2 = new User();
        user2.setId("2");
        user2.setFirstName("علی");
        user2.setLastName("احمدی");
        user2.setJobTitle("برنامه‌نویس وب");
        user2.setBio("Yo");
        user2.setProfilePictureURL("http://localhost:8000/mine.jpg");
        ArrayList<UserSkill> skills1 = new ArrayList<>();
        skills1.add(new UserSkill(SkillNameList.get("HTML"), 4));
        skills1.add(new UserSkill(SkillNameList.get("Javascript"), 2));
        skills1.add(new UserSkill(SkillNameList.get("C++"), 8));
        skills1.add(new UserSkill(SkillNameList.get("Java"), 1));
        user2.setSkills(skills1);
        try {
            UserList.add(user2);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }

        try {
            UserMapper userMapper = new UserMapper();
            if (userMapper.get(user1.getId()) == null) {
                userMapper.insert(user1);
            }
            if (userMapper.get(user2.getId()) == null) {
                userMapper.insert(user2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getSQLState());
        }

        logger.info("Initialization Done");
    }
}

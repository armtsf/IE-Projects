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

    private ProjectRepository projectRepository;
    private SkillNameRepository skillNameRepository;
    private UserRepository userRepository;

    public InitializationListener() throws SQLException {
        this.projectRepository = new ProjectRepository();
        this.skillNameRepository = new SkillNameRepository();
        this.userRepository = new UserRepository();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void contextInitialized() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpResponse<String> jsonResponse = Unirest.get(SKILLS_ENDPOINT).asString();
            ArrayList<SkillName> skillNames = mapper.readValue(jsonResponse.getBody(), new TypeReference<List<SkillName>>(){});
            for (SkillName skillName: skillNames) {
                try {
                    skillNameRepository.add(skillName);
                } catch (InvalidObjectException ignored) {
                }
            }

            jsonResponse = Unirest.get(PROJECTS_ENDPOINT).asString();
            ArrayList<Project> projects = mapper.readValue(jsonResponse.getBody(), new TypeReference<List<Project>>(){});
            for (Project project: projects) {
                try {
                    projectRepository.add(project);
                    for (ProjectSkill skill : project.getSkills()) {
                        skill.setSkillName(skillNameRepository.get(skill.getSkillName().getName()));
                        skill.setProject(project);
                        projectRepository.addSkill(skill);
                    }
                } catch (InvalidObjectException ignored) {
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnirestException | IOException | SQLException e) {
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
        try {
            skills.add(new UserSkill(skillNameRepository.get("HTML"), 5));
            skills.add(new UserSkill(skillNameRepository.get("Javascript"), 4));
            skills.add(new UserSkill(skillNameRepository.get("C++"), 2));
            skills.add(new UserSkill(skillNameRepository.get("Java"), 3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            try {
                userRepository.add(user1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (InvalidObjectException ignored) {
        }
        for (UserSkill skill: skills) {
            try {
//                logger.info(skill.getSkillName().getName());
                skill.setUser(user1);
                userRepository.addSkill(skill);
            } catch (SQLException ignored) {
            }
        }
        Session.put("userId", user1.getId());

        User user2 = new User();
        user2.setId("2");
        user2.setFirstName("علی");
        user2.setLastName("احمدی");
        user2.setJobTitle("برنامه‌نویس وب");
        user2.setBio("Yo");
        user2.setProfilePictureURL("http://localhost:8000/mine.jpg");
        ArrayList<UserSkill> skills2 = new ArrayList<>();
        try {
            skills2.add(new UserSkill(skillNameRepository.get("HTML"), 4));
            skills2.add(new UserSkill(skillNameRepository.get("Javascript"), 2));
            skills2.add(new UserSkill(skillNameRepository.get("C++"), 8));
            skills2.add(new UserSkill(skillNameRepository.get("Java"), 1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user2.setSkills(skills2);
        try {
            try {
                userRepository.add(user2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (InvalidObjectException ignored) {
        }
        for (UserSkill skill: skills2) {
            try {
                skill.setUser(user2);
                userRepository.addSkill(skill);
            } catch (SQLException ignored) {
            }
        }

        logger.info("Initialization Done");
    }
}

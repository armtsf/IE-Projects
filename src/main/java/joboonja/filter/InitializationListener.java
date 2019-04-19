package joboonja.filter;

import joboonja.utils.Session;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import joboonja.models.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitializationListener {
    private final String PROJECTS_ENDPOINT = "http://142.93.134.194:8000/joboonja/project";
    private final String SKILLS_ENDPOINT = "http://142.93.134.194:8000/joboonja/skill";

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
                for (Skill skill: project.getSkills()) {
                    skill.setSkillName(SkillNameList.get(skill.getSkillName().getName()));
                }
                ProjectList.add(project);
            }
        } catch (UnirestException | IOException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setId("1");
        user.setFirstName("علی");
        user.setLastName("شریف‌زاده");
        user.setJobTitle("برنامه‌نویس وب");
        user.setBio("روی سنگ قبرم بنویسید: خدا بیامرز می‌خواست خیلی کارا بکنه ولی پول نداشت");
        user.setProfilePictureURL("http://localhost:8000/mine.jpg");
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(new Skill(SkillNameList.get("HTML"), 5));
        skills.add(new Skill(SkillNameList.get("Javascript"), 4));
        skills.add(new Skill(SkillNameList.get("C++"), 2));
        skills.add(new Skill(SkillNameList.get("Java"), 3));
        user.setSkills(skills);
        try {
            UserList.add(user);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
        Session.put("userId", user.getId());

        User user1 = new User();
        user1.setId("2");
        user1.setFirstName("علی");
        user1.setLastName("احمدی");
        user1.setJobTitle("برنامه‌نویس وب");
        user1.setBio("Yo");
        user1.setProfilePictureURL("http://localhost:8000/mine.jpg");
        ArrayList<Skill> skills1 = new ArrayList<>();
        skills1.add(new Skill(SkillNameList.get("HTML"), 4));
        skills1.add(new Skill(SkillNameList.get("Javascript"), 2));
        skills1.add(new Skill(SkillNameList.get("C++"), 8));
        skills1.add(new Skill(SkillNameList.get("Java"), 1));
        user1.setSkills(skills1);
        try {
            UserList.add(user1);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }

        System.out.println("Initialization Done.");
    }
}

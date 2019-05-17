package joboonja.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import joboonja.models.*;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class FetchData {
    static void fetchSkillNames() {
        String SKILLS_ENDPOINT = "http://142.93.134.194:8000/joboonja/skill";

        ObjectMapper mapper = new ObjectMapper();
        try {
            SkillNameRepository skillNameRepository = new SkillNameRepository();
            HttpResponse<String> jsonResponse = Unirest.get(SKILLS_ENDPOINT).asString();
            ArrayList<SkillName> skillNames = mapper.readValue(jsonResponse.getBody(), new TypeReference<List<SkillName>>(){});
            for (SkillName skillName: skillNames) {
                try {
                    skillNameRepository.add(skillName);
                } catch (InvalidObjectException ignored) {
                }
            }
        } catch (UnirestException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    static void fetchProjects() {
        String PROJECTS_ENDPOINT = "http://142.93.134.194:8000/joboonja/project";

        ObjectMapper mapper = new ObjectMapper();
        try {
            ProjectRepository projectRepository = new ProjectRepository();
            SkillNameRepository skillNameRepository = new SkillNameRepository();

            HttpResponse<String> jsonResponse = Unirest.get(PROJECTS_ENDPOINT).asString();
            ArrayList<Project> projects = mapper.readValue(jsonResponse.getBody(), new TypeReference<List<Project>>(){});
            for (Project project : projects) {
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
    }
}

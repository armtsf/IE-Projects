package joboonja.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import joboonja.data.mappers.ProjectMapper;
import joboonja.models.Project;
import joboonja.models.ProjectList;
import joboonja.models.ProjectSkill;
import joboonja.models.SkillNameList;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateScheduler {

    private final String PROJECTS_ENDPOINT = "http://142.93.134.194:8000/joboonja/project";
    private ProjectList projectList;
    private SkillNameList skillNameList;

    public UpdateScheduler() throws SQLException {
        this.projectList = new ProjectList();
        this.skillNameList = new SkillNameList();
    }

    @Scheduled(fixedDelay = 300000, initialDelay = 10000)
    public void updateProjects() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ProjectMapper projectMapper = new ProjectMapper();
            HttpResponse<String> jsonResponse = Unirest.get(PROJECTS_ENDPOINT).asString();
            ArrayList<Project> projects = mapper.readValue(jsonResponse.getBody(), new TypeReference<List<Project>>() {
            });
            for (Project project : projects) {
                for (ProjectSkill skill : project.getSkills()) {
                    skill.setSkillName(skillNameList.get(skill.getSkillName().getName()));
                    skill.setProject(project);
                }
                if (projectList.get(project.getId()) == null)
                    projectList.add(project);
            }
        } catch (UnirestException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}
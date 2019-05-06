package joboonja.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import joboonja.models.Project;
import joboonja.models.ProjectRepository;
import joboonja.models.ProjectSkill;
import joboonja.models.SkillNameRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateScheduler {

    private final String PROJECTS_ENDPOINT = "http://142.93.134.194:8000/joboonja/project";
    private ProjectRepository projectRepository;
    private SkillNameRepository skillNameRepository;

    public UpdateScheduler() throws SQLException {
        this.projectRepository = new ProjectRepository();
        this.skillNameRepository = new SkillNameRepository();
    }

    @Scheduled(fixedDelay = 300000, initialDelay = 10000)
    public void updateProjects() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpResponse<String> jsonResponse = Unirest.get(PROJECTS_ENDPOINT).asString();
            ArrayList<Project> projects = mapper.readValue(jsonResponse.getBody(), new TypeReference<List<Project>>() {
            });
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
        } catch (UnirestException | IOException e) {
            e.printStackTrace();
        }
    }

}
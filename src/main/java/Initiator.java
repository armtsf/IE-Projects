import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Initiator {
    public static final String PROJECTS_ENDPOINT = "http://142.93.134.194:8000/joboonja/project";
    public static final String SKILLS_ENDPOINT = "http://142.93.134.194:8000/joboonja/skill";

    public static void init() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpResponse<String> jsonResponse = Unirest.get(PROJECTS_ENDPOINT).asString();
            ArrayList<Project> projects = mapper.readValue(jsonResponse.getBody(), new TypeReference<List<Project>>(){});
            for (Project project: projects) {
                System.out.println(project.getTitle());
                ProjectList.add(project);
            }

            jsonResponse = Unirest.get(SKILLS_ENDPOINT).asString();
            ArrayList<SkillName> skillNames = mapper.readValue(jsonResponse.getBody(), new TypeReference<List<SkillName>>(){});
            for (SkillName skillName: skillNames) {
                System.out.println(skillName.getName());
                SkillNameList.add(skillName);
            }
        } catch (UnirestException | IOException e) {
            e.printStackTrace();
        }
    }
}

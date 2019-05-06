package joboonja.models;

import joboonja.data.mappers.ProjectMapper;
import joboonja.data.mappers.ProjectSkillMapper;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProjectRepository {
    private ProjectMapper projectMapper;
    private ProjectSkillMapper projectSkillMapper;

    public ProjectRepository() throws SQLException {
        this.projectMapper = new ProjectMapper();
        this.projectSkillMapper = new ProjectSkillMapper();
    }

    public void add(Project project) throws InvalidObjectException, SQLException {
        if (projectMapper.get(project.getId()) != null) {
            throw new InvalidObjectException("project with the same id exists");
        }
        else {
            projectMapper.insert(project);
        }
    }

    public void addSkill(ProjectSkill projectSkill) throws SQLException {
        projectSkillMapper.insert(projectSkill);
    }

    public Project get(final String id) throws SQLException {
        Project project = projectMapper.get(id);
        if (project == null)
            throw new NoSuchElementException();
        else
            return project;
    }

    //TODO
    public ArrayList<Project> all(int page) throws SQLException {
        return projectMapper.all(page);
    }

    public ArrayList<Project> searchProjects(String project, int page) throws SQLException {
        return projectMapper.search(project, page);
    }
}

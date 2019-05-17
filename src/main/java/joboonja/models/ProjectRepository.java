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

    public ArrayList<Project> filterUnfinishedReachedDeadline(long now) throws SQLException {
        return projectMapper.filterUnfinishedReachedDeadline(now);
    }

    public int updateWinner(Project project) throws SQLException {
        return projectMapper.updateWinner(project);
    }

    public int updateFinished(Project project) throws SQLException {
        return projectMapper.updateFinished(project);
    }

    public ArrayList<Project> all(int start, int offset) throws SQLException {
        return projectMapper.all(start, offset);
    }

    public ArrayList<Project> searchProjects(String query, int start, int offset) throws SQLException {
        return projectMapper.search(query, start, offset);
    }
}

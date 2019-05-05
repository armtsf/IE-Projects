package joboonja.models;

import joboonja.data.mappers.ProjectMapper;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProjectList {
    private ProjectMapper projectMapper;

    public ProjectList() throws SQLException {
        this.projectMapper = new ProjectMapper();
    }

    public void add(Project project) throws InvalidObjectException, SQLException {
        if (projectMapper.get(project.getId()) != null) {
            throw new InvalidObjectException("project with the same id exists");
        }
        else {
            projectMapper.insert(project);
        }
    }

    public Project get(final String id) throws SQLException {
        Project project = projectMapper.get(id);
        if (project == null)
            throw new NoSuchElementException();
        else
            return project;
    }

    //TODO
    public ArrayList<Project> all() {
        return null;
    }
}

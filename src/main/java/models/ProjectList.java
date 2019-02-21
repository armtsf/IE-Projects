package models;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProjectList {
    private static ArrayList<Project> projects = new ArrayList<>();

    public static void add(Project project) throws InvalidObjectException {
        for (Project p : projects) {
            if (p.getId().equals(project.getId())) {
                throw new InvalidObjectException("project with the same id exists");
            }
        }
        projects.add(project);
    }

    public static Project get(final String title) {
        return projects.stream().filter(project -> project.getId().equals(title)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}

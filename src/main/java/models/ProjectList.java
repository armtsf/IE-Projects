package models;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProjectList {
    private static ArrayList<Project> projects = new ArrayList<>();

    public static void add(Project project) throws InvalidObjectException {
        for (Project p : projects) {
            if (p.getTitle().equals(project.getTitle())) {
                throw new InvalidObjectException("project with the name title exists");
            }
        }
        projects.add(project);
    }

    public static Project get(final String title) {
        return projects.stream().filter(project -> project.getTitle().equals(title)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}

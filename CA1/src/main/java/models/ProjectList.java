package models;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProjectList {
    private static ArrayList<Project> projects = new ArrayList<>();

    public static void add(Project project) {
        projects.add(project);
    }

    public static Project get(final String title) {
        return projects.stream().filter(project -> project.getTitle().equals(title)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}

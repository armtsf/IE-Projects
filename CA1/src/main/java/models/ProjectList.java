package models;

import java.util.ArrayList;

public class ProjectList {
    private static ArrayList<Project> projects = new ArrayList<>();

    public static void add(Project project) {
        projects.add(project);
    }
}

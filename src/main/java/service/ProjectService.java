package service;

import models.*;

import java.io.InvalidObjectException;
import java.util.ArrayList;

public class ProjectService {

    public static ArrayList<Project> getProjectList(User user) {
        ArrayList<Project> result = new ArrayList<>();
        for (Project project: ProjectList.all()) {
            if (user.isEligibleFor(project)) {
                result.add(project);
            }
        }
        return result;
    }

    public static Project getProjectDetails(User user, String projectId) throws IllegalAccessException {
        Project project = ProjectList.get(projectId);
        if (user.isEligibleFor(project))
            return project;
        else
            throw new IllegalAccessException();
    }

    public static void addBid(User user, String projectId, long bidAmount) throws InvalidObjectException {
        Bid bid = new Bid(user.getId(), projectId, bidAmount);
        BidList.add(bid);
    }

}

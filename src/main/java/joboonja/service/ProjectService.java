package joboonja.service;

import joboonja.models.*;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.ArrayList;

@Service
public class ProjectService {

    public ArrayList<Project> getProjectList(User user) {
        ArrayList<Project> result = new ArrayList<>();
        for (Project project: ProjectList.all()) {
            if (user.isEligibleFor(project)) {
                result.add(project);
            }
        }
        return result;
    }

    public Project getProjectDetails(User user, String projectId) throws IllegalAccessException {
        Project project = ProjectList.get(projectId);
        if (user.isEligibleFor(project))
            return project;
        else
            throw new IllegalAccessException();
    }

    public void addBid(User user, String projectId, long bidAmount) throws InvalidObjectException {
        Bid bid = new Bid(user.getId(), projectId, bidAmount);
        BidList.add(bid);
    }

}

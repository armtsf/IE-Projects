package joboonja.service;

import joboonja.DTO.BidDTO;
import joboonja.models.*;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.ArrayList;

@Service
public class ProjectService {

    public ArrayList<Project> getProjectsList(User user) {
        ArrayList<Project> result = new ArrayList<>();
        for (Project project: ProjectList.all()) {
            if (user.isEligibleFor(project)) {
                result.add(project);
            }
        }
        return result;
    }

    public Project getProject(User user, String projectId) throws IllegalAccessException {
        Project project = ProjectList.get(projectId);
        if (user.isEligibleFor(project))
            return project;
        else
            throw new IllegalAccessException();
    }

    public void addBid(User user, String projectId, long bidAmount) throws InvalidObjectException, IllegalAccessException {
        Project project = getProject(user, projectId);
        Bid bid = new Bid(user, project, bidAmount);
        BidList.add(bid);
    }

    public BidDTO getBid(User user, String projectId) throws IllegalAccessException {
        Project project = getProject(user, projectId);
        Bid bid = BidList.get(project, user);
        return new BidDTO(bid.getBidAmount());
    }
}

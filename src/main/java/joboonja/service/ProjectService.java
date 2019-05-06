package joboonja.service;

import joboonja.DTO.BidDTO;
import joboonja.models.*;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private BidRepository bidRepository;

    public ProjectService() throws SQLException {
        this.projectRepository = new ProjectRepository();
        this.bidRepository = new BidRepository();
    }

    public ArrayList<Project> getProjectsList(User user, int start, int offset) throws SQLException {
//        for (Project project: projectRepository.all(page)) {
//            if (user.isEligibleFor(project)) {
//                result.add(project);
//            }
//        }
        return new ArrayList<>(projectRepository.all(start, offset));
    }

    public Project getProject(User user, String projectId) throws IllegalAccessException, SQLException {
        Project project = projectRepository.get(projectId);
        if (user.isEligibleFor(project))
            return project;
        throw new IllegalAccessException();
    }

    public void addBid(User user, String projectId, long bidAmount) throws InvalidObjectException, IllegalAccessException, SQLException {
        Project project = getProject(user, projectId);
        Bid bid = new Bid(user, project, bidAmount);
        bidRepository.add(bid);
    }

    public BidDTO getBid(User user, String projectId) throws IllegalAccessException, SQLException {
        Project project = getProject(user, projectId);
        Bid bid = bidRepository.get(project, user);
        return new BidDTO(bid.getBidAmount());
    }

    public ArrayList<Project> getSearchResult(String project, int start, int offset) throws SQLException {
        return projectRepository.searchProjects(project, start, offset);
    }
}

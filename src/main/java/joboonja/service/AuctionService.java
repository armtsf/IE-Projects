package joboonja.service;

import joboonja.models.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
class AuctionService {

    private ProjectRepository projectRepository;
    private UserRepository userList;
    private BidRepository bidRepository;

    public AuctionService() throws SQLException {
        this.projectRepository = new ProjectRepository();
        this.userList = new UserRepository();
    }

    public User finish(String projectName) throws SQLException {
        Project project = projectRepository.get(projectName);
        ArrayList<ProjectSkill> jobSkills = project.getSkills();
        long jobOffer = project.getBudget();

        ArrayList<Bid> bids = bidRepository.get(project);

        long maxScore = 0;
        User winner = null;

        for (Bid bid : bids) {
            User user = userList.get(bid.getUser().getId());
            long score = calculate(user, bid.getBidAmount(), jobSkills, jobOffer);
            if (score > maxScore) {
                maxScore = score;
                winner = user;
            }
        }

        return winner;
    }

    public static long calculate(User user, long userOffer, ArrayList<ProjectSkill> jobSkills, long jobOffer) {
        long sum = 0;
        for (ProjectSkill skill : jobSkills) {
            try {
                UserSkill userSkill = user.getSkill(skill.getSkillName());
                sum += 10000 * Math.pow((userSkill.getPoints() - skill.getPoints()), 2);
            }
            catch (NoSuchElementException e) {
                return -1;
            }
        }
        sum += jobOffer - userOffer;
        return sum;
    }

}

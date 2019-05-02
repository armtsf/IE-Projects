package joboonja.service;

import joboonja.models.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class AuctionService {

    static User finish(String projectName) {
        Project project = ProjectList.get(projectName);
        ArrayList<ProjectSkill> jobSkills = project.getSkills();
        long jobOffer = project.getBudget();

        ArrayList<Bid> bids = BidList.get(project);

        long maxScore = 0;
        User winner = null;

        for (Bid bid : bids) {
            User user = UserList.get(bid.getUser().getId());
            long score = calculate(user, bid.getBidAmount(), jobSkills, jobOffer);
            if (score > maxScore) {
                maxScore = score;
                winner = user;
            }
        }

        return winner;
    }

    private static long calculate(User user, long userOffer, ArrayList<ProjectSkill> jobSkills, long jobOffer) {
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

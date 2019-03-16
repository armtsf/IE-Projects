package service;

import models.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

class AuctionService {

    static User finish(String projectName) {
        Project project = ProjectList.get(projectName);
        ArrayList<Skill> jobSkills = project.getSkills();
        long jobOffer = project.getBudget();

        ArrayList<Bid> bids = BidList.get(projectName);

        long maxScore = 0;
        User winner = null;

        for (Bid bid : bids) {
            User user = UserList.get(bid.getBiddingUser());
            long score = calculate(user, bid.getBidAmount(), jobSkills, jobOffer);
            if (score > maxScore) {
                maxScore = score;
                winner = user;
            }
        }

        return winner;
    }

    private static long calculate(User user, long userOffer, ArrayList<Skill> jobSkills, long jobOffer) {
        long sum = 0;
        for (Skill skill : jobSkills) {
            try {
                Skill userSkill = user.getSkill(skill.getSkillName());
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

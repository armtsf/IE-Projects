package joboonja.filter;

import joboonja.models.Project;
import joboonja.models.ProjectRepository;
import joboonja.models.User;
import joboonja.service.AuctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class AuctionScheduler {
    private Logger logger = LoggerFactory.getLogger(AuctionScheduler.class);

    private ProjectRepository projectRepository;
    private AuctionService auctionService;

    public AuctionScheduler() throws SQLException {
        projectRepository = new ProjectRepository();
        auctionService = new AuctionService();
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 10000)
    public void runAuctions() throws SQLException {
        logger.info("runActions");

        long now = System.currentTimeMillis();
        logger.info(String.valueOf(now));
        ArrayList<Project> projects = projectRepository.filterUnfinishedReachedDeadline(now);
        for (Project project: projects) {
            logger.info(project.getId() + " " + project.getDeadline());

            User winner = auctionService.finish(project);
            if (winner != null) {
                project.setWinner(winner);
                projectRepository.updateWinner(project);
            }
            project.setFinished(true);
            projectRepository.updateFinished(project);
        }
    }
}

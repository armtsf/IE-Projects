package joboonja.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateScheduler {

    private Logger logger = LoggerFactory.getLogger(UpdateScheduler.class);

    public UpdateScheduler() {}

    @Scheduled(fixedDelay = 300000, initialDelay = 10000)
    public void updateProjects() {
        logger.info("updateProjects running");
        FetchData.fetchProjects();
    }

}
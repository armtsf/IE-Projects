package joboonja.filter;

import joboonja.DTO.UserSignUpDTO;
import joboonja.models.*;
import joboonja.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class InitializationListener {
    private Logger logger = LoggerFactory.getLogger(InitializationListener.class);

    private SkillNameRepository skillNameRepository;
    private UserRepository userRepository;
    private AuthenticationService authService;

    public InitializationListener() throws SQLException {
        this.skillNameRepository = new SkillNameRepository();
        this.userRepository = new UserRepository();
        this.authService = new AuthenticationService();
    }

    private void createDummyUser(UserSignUpDTO dto, ArrayList<UserSkill> skills) {
        logger.info(dto.getUsername());
        try {
            authService.signUp(dto);
        } catch (Exception ignored) {}
        try {
            User user = userRepository.getByUsername(dto.getUsername());
            for (UserSkill skill : skills) {
                skill.setUser(user);
                userRepository.addSkill(skill);
                logger.info(skill.getSkillName().getName());
            }
        } catch (Exception ignored) {}
    }

    @EventListener(ApplicationReadyEvent.class)
    public void contextInitialized() {
        FetchData.fetchSkillNames();
        FetchData.fetchProjects();

        UserSignUpDTO user1 = new UserSignUpDTO();
        user1.setUsername("user1");
        user1.setPassword("123");
        user1.setFirstName("علی");
        user1.setLastName("شریف‌زاده");
        user1.setJobTitle("برنامه‌نویس وب");
        user1.setBio("روی سنگ قبرم بنویسید: خدا بیامرز می‌خواست خیلی کارا بکنه ولی پول نداشت");
        user1.setProfilePictureURL("http://localhost:8000/mine.jpg");
        ArrayList<UserSkill> skills1 = new ArrayList<>();
        try {
            skills1.add(new UserSkill(skillNameRepository.get("HTML"), 5));
            skills1.add(new UserSkill(skillNameRepository.get("Javascript"), 4));
            skills1.add(new UserSkill(skillNameRepository.get("C++"), 2));
            skills1.add(new UserSkill(skillNameRepository.get("Java"), 3));
        } catch (SQLException ignored) {}
        createDummyUser(user1, skills1);

        UserSignUpDTO user2 = new UserSignUpDTO();
        user2.setUsername("user2");
        user2.setPassword("123");
        user2.setFirstName("علی");
        user2.setLastName("احمدی");
        user2.setJobTitle("برنامه‌نویس وب");
        user2.setBio("Yo");
        user2.setProfilePictureURL("http://localhost:8000/mine.jpg");
        ArrayList<UserSkill> skills2 = new ArrayList<>();
        try {
            skills2.add(new UserSkill(skillNameRepository.get("HTML"), 4));
            skills2.add(new UserSkill(skillNameRepository.get("Javascript"), 2));
            skills2.add(new UserSkill(skillNameRepository.get("C++"), 8));
            skills2.add(new UserSkill(skillNameRepository.get("Java"), 1));
        } catch (SQLException ignored) {}
        createDummyUser(user2, skills2);

        UserSignUpDTO user3 = new UserSignUpDTO();
        user3.setUsername("user3");
        user3.setPassword("123");
        user3.setFirstName("سلطان");
        user3.setLastName("سلطانی");
        user3.setJobTitle("Full-Stack");
        user3.setBio("بیا بیا");
        user3.setProfilePictureURL("http://localhost:8000/mine.jpg");
        ArrayList<UserSkill> skills3 = new ArrayList<>();
        try {
            for (SkillName skillName: skillNameRepository.all()) {
                skills3.add(new UserSkill(skillName, 20));
            }
        } catch (Exception ignored) {}
        createDummyUser(user3, skills3);

        logger.info("Initialization Done");
    }
}

package views;

import models.User;

public class UserDetailsView {
    public static String render(User user) {
        StringBuilder responseBuilder = new StringBuilder("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>User</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <ul>\n" +
                "        <li>id: " + user.getId() + "</li>\n" +
                "        <li>first name: " + user.getFirstName() + "</li>\n" +
                "        <li>last name: " + user.getLastName() + "</li>\n" +
                "        <li>jobTitle: " + user.getJobTitle() + "</li>\n" +
                "        <li>bio: " + user.getBio() + "</li>\n" +
                "    </ul>\n" +
                "</body>\n" +
                "</html>");
        return responseBuilder.toString();
    }
}

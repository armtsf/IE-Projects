package views;

import models.Project;

public class ProjectDetailsView {
    public static String render(Project project) {
        StringBuilder responseBuilder = new StringBuilder("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>Project</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<ul>\n" +
                "<li>id: " + project.getId() + "</li>\n" +
                "<li>title: " + project.getTitle() + "</li>\n" +
                "<li>description: " + project.getDescription() + "</li>\n" +
                "<li>imageUrl: <img src=\"" + project.getImageURL() + "\" style=\"width: 50px; height: 50px;\"></li>\n" +
                "<li>budget: " + project.getBudget() + "</li>\n" +
                "</ul>\n" +
                "</body>\n" +
                "</html>");
        return  responseBuilder.toString();
    }
}
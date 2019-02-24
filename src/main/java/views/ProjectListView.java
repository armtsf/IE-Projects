package views;

import models.Project;

import java.util.ArrayList;

public class ProjectListView {
    public static String render(ArrayList<Project> projects) {
        StringBuilder responseBuilder = new StringBuilder(
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Projects</title>\n" +
                "    <style>\n" +
                "        table {\n" +
                "            text-align: center;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "        td {\n" +
                "            min-width: 300px;\n" +
                "            margin: 5px 5px 5px 5px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n");
        responseBuilder.append(
                "<body>\n" +
                "    <table>\n");
        responseBuilder.append(
                "        <tr>\n" +
                "            <th>id</th>\n" +
                "            <th>title</th>\n" +
                "            <th>budget</th>\n" +
                "        </tr>");
        for (Project project: projects) {
            responseBuilder.append(
                    "        <tr>\n" +
                    "            <td>" + project.getId() + "</td>\n" +
                    "            <td>" + project.getTitle() + "</td>\n" +
                    "            <td>" + project.getBudget() + "</td>\n" +
                    "        </tr>\n");
        }
        responseBuilder.append(
                "    </table>\n" +
                "</body>\n" +
                "</html>");
        return responseBuilder.toString();
    }
}

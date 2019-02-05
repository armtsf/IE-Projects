import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import models.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isFinished = false;
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        while (!isFinished) {
            Pair<String, String> commandParts = getCommandParts();
            String commandName = commandParts.getKey();
            String commandData = commandParts.getValue();

            switch (commandName) {
                case "register":
                    System.out.println(commandData);
                    User user = mapper.readValue(commandData, User.class);
                    UserList.add(user);
                    break;
                case "addProject":
                    System.out.println(commandData);
                    Project project = mapper.readValue(commandData, Project.class);
                    ProjectList.add(project);
                    break;
                case "bid":
                    System.out.println(commandData);
                    Bid bid = mapper.readValue(commandData, Bid.class);
                    BidList.add(bid);
                    break;
                case "auction":
                    System.out.println(commandData);
                    isFinished = true;
                    JsonNode jsonNode = mapper.readTree(commandData);
                    String title = jsonNode.get("projectTitle").asText();
                    System.out.println(Auction.finish(title).getUsername());
                    break;
            }
        }
    }

    private static Pair<String, String> getCommandParts() {
        String command = scanner.nextLine();
        int spaceIndex = command.indexOf(" ");
        return new Pair<>(command.substring(0, spaceIndex), command.substring(spaceIndex));
    }
}

package models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BidList {
    private static ArrayList<Bid> bids = new ArrayList<>();

    public static void add(Bid bid) {
        try {
            User user = UserList.get(bid.getBiddingUser());
            bids.add(bid);
        }
        catch (Exception e) {
            System.out.println("Invalid username.");
        }
    }

    public static ArrayList<Bid> get(String projectName) {
        return bids.stream().filter(bid -> bid.getProjectTitle().equals(projectName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

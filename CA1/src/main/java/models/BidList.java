package models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BidList {
    private static ArrayList<Bid> bids = new ArrayList<>();

    public static void add(Bid bid) {
        if (bid.isValid()) {
            bids.add(bid);
        }
    }

    public static ArrayList<Bid> get(String projectName) {
        return bids.stream().filter(bid -> bid.getProjectTitle().equals(projectName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

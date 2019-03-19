package joboonja.models;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BidList {
    private static ArrayList<Bid> bids = new ArrayList<>();

    public static void add(Bid bid) throws InvalidObjectException {
        if (bid.isValid()) {
            ArrayList<Bid> bidList = BidList.get(bid.getProjectTitle());
            for (Bid oldBid: bidList) {
                if (oldBid.getBiddingUser().equals(bid.getBiddingUser())) {
                    throw new InvalidObjectException("duplicate bid for this project from same user");
                }
            }
            bids.add(bid);
        } else {
            throw new InvalidObjectException("invalid bid");
        }
    }

    public static ArrayList<Bid> get(String projectName) {
        return bids.stream().filter(bid -> bid.getProjectTitle().equals(projectName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

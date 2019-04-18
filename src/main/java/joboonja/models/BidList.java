package joboonja.models;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BidList {
    private static ArrayList<Bid> bids = new ArrayList<>();

    public static void add(Bid bid) throws InvalidObjectException {
        if (bid.isValid()) {
            ArrayList<Bid> bidList = BidList.get(bid.getProject());
            for (Bid oldBid: bidList) {
                if (oldBid.getUser().equals(bid.getUser())) {
                    throw new InvalidObjectException("duplicate bid for this project from same user");
                }
            }
            bids.add(bid);
        } else {
            throw new InvalidObjectException("invalid bid");
        }
    }

    public static ArrayList<Bid> get(Project project) {
        return bids.stream().filter(bid -> bid.getProject().equals(project))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Bid get(Project project, User user) {
        for (Bid bid: bids) {
            if (bid.getProject().equals(project) && bid.getUser().equals(user)) {
                return bid;
            }
        }
        throw new NoSuchElementException("");
    }
}

package joboonja.models;

import joboonja.data.mappers.BidMapper;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BidRepository {
    private BidMapper bidMapper;

    public BidRepository() throws SQLException {
        bidMapper = new BidMapper();
    }

    public void add(Bid bid) throws InvalidObjectException, SQLException {
        if (bid.isValid()) {
            ArrayList<Bid> bidList = this.get(bid.getProject());
            for (Bid oldBid: bidList) {
                if (oldBid.getUser().equals(bid.getUser())) {
                    throw new InvalidObjectException("duplicate bid for this project from same user");
                }
            }
            bidMapper.insert(bid);
        } else {
            throw new InvalidObjectException("invalid bid");
        }
    }

    public ArrayList<Bid> get(Project project) throws SQLException {
        return bidMapper.filter(project);
    }

    public Bid get(Project project, User user) throws SQLException {
        Bid bid = bidMapper.get(user, project);
        if (bid == null) {
            throw new NoSuchElementException("");
        }
        return bid;
    }
}

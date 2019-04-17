package joboonja.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BidRequest {
    private long bidAmount;

    public BidRequest() {}

    public BidRequest(long bidAmount) {
        this.bidAmount = bidAmount;
    }

    public void setBidAmount(long bidAmount) {
        this.bidAmount = bidAmount;
    }

    public long getBidAmount() {
        return bidAmount;
    }
}

package joboonja.DTO;

public class BidDTO {
    private long bidAmount;

    public BidDTO() {}

    public BidDTO(long bidAmount) {
        this.bidAmount = bidAmount;
    }

    public void setBidAmount(long bidAmount) {
        this.bidAmount = bidAmount;
    }

    public long getBidAmount() {
        return bidAmount;
    }
}

package dto;

/**
 * contains the stock information shown in the user stock list
 */
public class UserStock {
    private int stockId;
    private String stockName;
    private int numOfShare;

    public UserStock(int stockId, String stockName, int numOfShare, double averagePurchasePrice) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.numOfShare = numOfShare;
        this.averagePurchasePrice = averagePurchasePrice;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    private double averagePurchasePrice;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getNumOfShare() {
        return numOfShare;
    }

    public void setNumOfShare(int numOfShare) {
        this.numOfShare = numOfShare;
    }

    public double getAveragePurchasePrice() {
        return averagePurchasePrice;
    }

    public void setAveragePurchasePrice(double averagePurchasePrice) {
        this.averagePurchasePrice = averagePurchasePrice;
    }

    public UserStock() {
    }

    public UserStock(String stockName, int numOfShare, double averagePurchasePrice) {
        this.stockName = stockName;
        this.numOfShare = numOfShare;
        this.averagePurchasePrice = averagePurchasePrice;
    }
}

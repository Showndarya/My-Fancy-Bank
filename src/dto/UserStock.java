package dto;

/**
 * contains the stock information shown in the user stock list
 */
public class UserStock {
    private String stockName;
    private int numOfShare;
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

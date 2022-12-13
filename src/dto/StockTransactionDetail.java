package dto;

public class StockTransactionDetail {
    private String stockName;

    private String stockTag;

    public String getStockTag() {
        return stockTag;
    }

    public void setStockTag(String stockTag) {
        this.stockTag = stockTag;
    }


    private double dealPrice;
    private String transactionType;
    private int numOfShare;

    public String getStockName() {
        return stockName;
    }

    public double getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(double dealPrice) {
        this.dealPrice = dealPrice;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public StockTransactionDetail(String stockName, String stockTag, double dealPrice, String transactionType, int numOfShare) {
        this.stockName = stockName;
        this.stockTag = stockTag;
        this.dealPrice = dealPrice;
        this.transactionType = transactionType;
        this.numOfShare = numOfShare;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getNumOfShare() {
        return numOfShare;
    }

    public void setNumOfShare(int numOfShare) {
        this.numOfShare = numOfShare;
    }

    public StockTransactionDetail() {
    }

}

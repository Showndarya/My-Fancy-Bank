package Models.Transaction;

import Utilities.SimpleDate;

public class Stock extends Transaction  {
    private final int SELL = 0;
    private final int BUY = 1;
    private int clientId;
    private int stockId;
    private int numberOfShare;
    private int price;

    public int getSELL() {
        return SELL;
    }

    public int getBUY() {
        return BUY;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getNumberOfShare() {
        return numberOfShare;
    }

    public void setNumberOfShare(int numberOfShare) {
        this.numberOfShare = numberOfShare;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public Stock() {
    }
}

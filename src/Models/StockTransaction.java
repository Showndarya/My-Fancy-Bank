package Models;

import Utilities.SimpleDate;

public class StockTransaction {
    private final int SELL = 0;
    private final int BUY = 1;
    private int id;
    private int clientId;
    private int stockId;
    private int numberOfShare;
    private int transactionType;
    private int price;

    public int getSELL() {
        return SELL;
    }

    public int getBUY() {
        return BUY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public SimpleDate getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(SimpleDate simpleDate) {
        this.simpleDate = simpleDate;
    }

    public StockTransaction() {
    }

    private SimpleDate simpleDate;
}

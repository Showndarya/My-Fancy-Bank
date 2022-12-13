package Models;

public class OpenInterest {
    private int id;
    private int clientId;
    private int stockId;

    private double purchasePrice;
    private int numOfShare;

    public OpenInterest() {
    }

    public OpenInterest(int id, int clientId, int stockId, double purchasePrice, int numOfShare) {
        this.id = id;
        this.clientId = clientId;
        this.stockId = stockId;
        this.purchasePrice = purchasePrice;
        this.numOfShare = numOfShare;
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

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getNumOfShare() {
        return numOfShare;
    }

    public void setNumOfShare(int numOfShare) {
        this.numOfShare = numOfShare;
    }

    public OpenInterest(int id) {
        this.id = id;
    }


}

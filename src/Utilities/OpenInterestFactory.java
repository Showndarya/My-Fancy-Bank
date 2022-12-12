package Utilities;

import Models.OpenInterest;

public class OpenInterestFactory {
    public static OpenInterest getOpenInterest(int id, int clientId, int stockId, double purchasePrice, int numOfShare) {

        return new OpenInterest(id, clientId, stockId, purchasePrice, numOfShare);
    }
}

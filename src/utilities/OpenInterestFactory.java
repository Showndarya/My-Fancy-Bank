package utilities;

import models.transaction.OpenInterest;

/**
 * used to generate openinterest
 */
public class OpenInterestFactory {
    public static OpenInterest getOpenInterest(int id, int clientId, int stockId, double purchasePrice, int numOfShare) {

        return new OpenInterest(id, clientId, stockId, purchasePrice, numOfShare);
    }
}

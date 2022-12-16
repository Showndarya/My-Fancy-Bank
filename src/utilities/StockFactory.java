package utilities;

import models.transaction.Stock;

/**
 * used to genearte stock
 */
public class StockFactory {
    public static Stock getStock(int id, String name, String tag, double price) {
        return new Stock(id, name, tag, price);
    }
}

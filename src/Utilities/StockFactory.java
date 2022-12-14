package Utilities;

import Models.Stock;

public class StockFactory {
    public static Stock getStock(int id, String name, String tag, double price) {
        return new Stock(id, name, tag, price);
    }
}

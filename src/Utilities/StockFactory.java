package Utilities;

import Models.Stock;

public class StockFactory {
    public static Stock getStock(String name, String tag, double price){
        return new Stock(name, tag, price);
    }
}

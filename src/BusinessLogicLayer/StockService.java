package BusinessLogicLayer;

import Models.Stock;
import dto.StockList;

import java.util.List;

public interface StockService {
    public StockList getAllStock();

    /**
     * buy the stock for this client and stock with this tag
     * @param clientId
     * @param
     */
    public boolean buyStock(int clientId, int stockId, int amount);

    /**
     * sell stock for this client and sell stock of this tag
     * @param clientId
     * @param
     */
    public boolean sellStock(int clientId, int stockId, int amount);
}

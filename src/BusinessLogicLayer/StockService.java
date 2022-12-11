package BusinessLogicLayer;

import dto.TableList;

public interface StockService {
    public TableList getAllStock();

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

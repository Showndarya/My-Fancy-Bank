package business_logic_layer.interfaces;

import models.transaction.Stock;
import dto.TableList;

/**
 * interface for stock service
 */
public interface StockService {
    public TableList getAllStock();

    /**
     * buy the stock for this client and stock with this tag
     *
     * @param clientId
     * @param
     */
    public boolean buyStock(int clientId, int stockId, int amount, double stockPrice);

    /**
     * sell stock for this client and sell stock of this tag
     *
     * @param clientId
     * @param
     */
    public boolean sellStock(int clientId, int stockId, int amount, double stockPrice);

    /**
     * add a stock to the stock list
     *
     * @param name
     * @param tag
     * @param price
     * @return
     */
    public boolean addStock(String name, String tag, double price);

    /**
     * get a stock by tag
     *
     * @param tag
     * @return
     */
    public Stock getStockByTag(String tag);

    public boolean updateStockPriceByTag(String tag, double price);

    public boolean deleteStockByTag(String tag);

    public TableList getUserStockDataByUserId(int userId);
}

package business_logic_layer.impl;

import business_logic_layer.interfaces.StockTransactionService;
import data_access_layer.interfaces.StockTransactionDao;
import data_access_layer.impl.StockTransactionDaoImpl;
import dto.StockTransactionDetail;
import dto.TableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StockTransactionServiceImpl implements StockTransactionService {
    private StockTransactionDao stockTransactionDao;

    public StockTransactionServiceImpl() {
        stockTransactionDao = new StockTransactionDaoImpl();

    }

    /**
     * add transaction in the table
     *
     * @param clientId
     * @param stockId
     * @param price
     * @param transType
     * @param numOfShare
     * @return
     */
    @Override
    public void addTransaction(Connection connection, int clientId, int stockId, double price, boolean transType, int numOfShare) throws SQLException {
        int i = stockTransactionDao.addTransaction(connection, clientId, stockId, price, transType, numOfShare);

    }

    /**
     * get all the transaction details on specific date
     *
     * @param clientId
     * @param simpleDate
     * @return
     */
    @Override
    public TableList getTransactionDetails(int clientId, String simpleDate) {
        try {
            List<StockTransactionDetail> transactionDetail = stockTransactionDao.getTransactionDetail(clientId, simpleDate);
            TableList tableList = new TableList();
            Object[] columnName = new Object[]{"Name", "Tag", "Price", "Transaction Type", "Number of share"};
            Object[][] rowData = new Object[transactionDetail.size()][];
            int index = 0;
            for (StockTransactionDetail i : transactionDetail) {
                rowData[index++] = new Object[]{i.getStockName(), i.getStockTag(), i.getDealPrice(), i.getTransactionType(), i.getNumOfShare()};
            }
            tableList.setColumnsName(columnName);
            tableList.setRowData(rowData);
            return tableList;
        } catch (SQLException e) {
            System.out.println("Get transaction detail failed");
        }
        return null;
    }

    /**
     * get all the transaction details
     *
     * @param clientId
     * @return
     */
    @Override
    public TableList getTransactionDetails(int clientId) {
        try {
            List<StockTransactionDetail> transactionDetail = stockTransactionDao.getAllTransactions(clientId);
            TableList tableList = new TableList();
            Object[] columnName = new Object[]{"Name", "Tag", "Price", "Transaction Type", "Number of share"};
            Object[][] rowData = new Object[transactionDetail.size()][];
            int index = 0;
            for (StockTransactionDetail i : transactionDetail) {
                rowData[index++] = new Object[]{i.getStockName(), i.getStockTag(), i.getDealPrice(), i.getTransactionType(), i.getNumOfShare()};
            }
            tableList.setColumnsName(columnName);
            tableList.setRowData(rowData);
            return tableList;
        } catch (SQLException e) {
            System.out.println("Get transaction detail failed");
        }
        return null;
    }

}

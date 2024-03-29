package business_logic_layer.impl;

import business_logic_layer.interfaces.*;
import enums.AccountType;
import utilities.BaseDao;
import data_access_layer.interfaces.StockDao;
import data_access_layer.impl.StockDaoImpl;
import models.transaction.OpenInterest;
import models.transaction.Stock;
import dto.TableList;
import dto.UserStock;
import utilities.FancyBank;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * class with service of stock trading
 */
public class StockServiceImpl implements StockService {

    private static final int MINIMUM_SAVINGS_MONEY = 2500;

    private StockDao stockDao;
    private OpenInterestService openInterestService;
    private SecurityService securityService;
    private AccountService accountService;
    private AccountOperationService accountOperationService;

    private StockTransactionService stockTransactionService;

    public StockServiceImpl() {
        stockDao = new StockDaoImpl();
        openInterestService = new OpenInterestServiceImpl();
        securityService = new SecurityServiceImpl();
        stockTransactionService = new StockTransactionServiceImpl();
        accountService = new AccountServiceImpl();
        accountOperationService = new AccountOperationServiceImpl();
    }

    @Override
    public TableList getAllStock() {
        List<Stock> list;
        try {
            list = stockDao.getAllStocks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableList stockList = new TableList();
        stockList.setColumnsName(new Object[]{"Name", "Tag", "Price"});
        Object[][] rowData = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            Stock stock = list.get(i);
            rowData[i] = new Object[]{stock.getName(), stock.getTag(), stock.getPrice()};
        }
        stockList.setRowData(rowData);
        return stockList;
    }

    /**
     * buy the stock for this client and stock with this tag for this amount
     *
     * @param clientId
     * @param
     */
    @Override
    public boolean buyStock(int clientId, int stockId, int numberOfShare, double stockPrice) {
        int accountId = accountService.getAccountId(FancyBank.getInstance().getUserId(), AccountType.Savings);
        double moneyInSavings = 0;
        try {
            moneyInSavings = accountOperationService.getBalance(accountId, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (moneyInSavings < MINIMUM_SAVINGS_MONEY) {
            return false;
        }
        // wait to do add the current date
        Connection connection = BaseDao.getConnection();
        System.out.println("Stock boughout at price: " + stockPrice);
        try {
            connection.setAutoCommit(false);
            //calculate new money
            double customerMoney = securityService.getCustomerMoney(clientId);
            System.out.println(customerMoney + " is customer money");
            if (customerMoney < stockPrice * numberOfShare) {
                return false;
            }
            //add stock to open intereset
            openInterestService.addToOpenInterest(connection, clientId, stockId, stockPrice, numberOfShare);


            customerMoney -= stockPrice * numberOfShare;


            //modify money in security account
            securityService.modifyMoneyInSecurityAccount(connection, clientId, customerMoney);

            //add transaction to transaction list
            stockTransactionService.addTransaction(connection, clientId, stockId, stockPrice, false, numberOfShare);

            connection.commit();

        } catch (SQLException e) {
            try {
                e.printStackTrace();
                System.out.println("Error occur. Try to rollback");
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed");
            }
        } finally {
            BaseDao.close(connection, null, null);
        }
        return true;


    }

    /**
     * sell stock for this client and sell stock of this tag for this amount
     *
     * @param clientId
     * @param numOfShare is the the amount want to sold
     */
    @Override
    public boolean sellStock(int clientId, int stockId, int numOfShare, double stockPrice) {
        int accountId = accountService.getAccountId(FancyBank.getInstance().getUserId(), AccountType.Savings);
        double moneyInSavings = 0;
        try {
            moneyInSavings = accountOperationService.getBalance(accountId, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (moneyInSavings < MINIMUM_SAVINGS_MONEY) {
            return false;
        }
        //check if has these num of share if not return false, check if has these stock
        int openInterestNum = openInterestService.getOpenInterestNum(clientId, stockId);
        System.out.println("You sell stock at price: " + stockPrice);
        boolean flag = true;
        if (openInterestNum < numOfShare) {
            return false;
        }


        Connection connection = BaseDao.getConnection();
        //add money
        try {
            double customerMoney = securityService.getCustomerMoney(clientId);
            double totalPrice = stockPrice * numOfShare;
            customerMoney += totalPrice;
            connection.setAutoCommit(false);
            securityService.modifyMoneyInSecurityAccount(connection, clientId, customerMoney);
            //add transaction
            stockTransactionService.addTransaction(connection, clientId, stockId, stockPrice, true, numOfShare);

            //modify open interest
            List<OpenInterest> list = openInterestService.getOpenInterestOrderedByPurchasePrice(connection, clientId, stockId);
            for (OpenInterest i : list) {
                if (numOfShare <= 0) break;
                if (i.getNumOfShare() <= numOfShare) {
                    numOfShare -= i.getNumOfShare();
                    openInterestService.deleteOpenInterest(connection, i.getId(), i.getClientId(), i.getStockId());
                } else {
                    int restShares = i.getNumOfShare() - numOfShare;
                    numOfShare = 0;
                    openInterestService.updateOpenInterest(connection, i.getId(), i.getClientId(), i.getStockId(), restShares);
                }
            }
            connection.commit();

        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
            try {
                System.out.println("Transaction failed start to rollback.");
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Roll back failed");
            }
        }

        return flag;
    }

    /**
     * add a stock to the stock list
     *
     * @param name
     * @param tag
     * @param price
     * @return
     */
    @Override
    public boolean addStock(String name, String tag, double price) {
        Connection connection = BaseDao.getConnection();
        boolean flag = true;
        try {
            connection.setAutoCommit(false);


            stockDao.addStock(connection, name, tag, price);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            System.out.println("Insert into stock fialed rollback");
            try {
                connection.rollback();
                System.out.println("Rollback success.");
            } catch (SQLException ex) {
                System.out.println("rollback failed");
            }
        } finally {
            BaseDao.close(connection, null, null);
        }
        return flag;
    }

    /**
     * get a stock by tag
     *
     * @param tag
     * @return
     */
    @Override
    public Stock getStockByTag(String tag) {
        Stock stock = null;
        try {
            stock = stockDao.getStockByTag(tag);
        } catch (SQLException e) {
            System.out.println("get stock failed");
        }
        return stock;
    }

    @Override
    public boolean updateStockPriceByTag(String tag, double price) {
        boolean flag = true;
        Connection connection = BaseDao.getConnection();
        try {
            stockDao.updateStock(connection, tag, price);
        } catch (SQLException e) {
            flag = false;

        } finally {
            BaseDao.close(connection, null, null);
        }
        return flag;
    }

    @Override
    public boolean deleteStockByTag(String tag) {
        Connection connection = BaseDao.getConnection();
        boolean flag = true;
        try {
            stockDao.deleteStockByTag(connection, tag);
        } catch (SQLException e) {
            flag = false;
            System.out.println("delete failed");
        }
        return flag;


    }

    @Override
    public TableList getUserStockDataByUserId(int userId) {
        Connection connection = BaseDao.getConnection();
        TableList tableList = new TableList();
        try {
            List<UserStock> userStocks = stockDao.getUserStock(connection, userId);
            Object[][] rowData = new Object[userStocks.size()][];
            int rowIndex = 0;
            for (UserStock i : userStocks) {
                rowData[rowIndex++] = new Object[]{i.getStockId(), i.getStockName(), i.getAveragePurchasePrice(), i.getNumOfShare()};
            }
            tableList.setRowData(rowData);
            tableList.setColumnsName(new Object[]{"Stock ID", "Stock Tag", "Average Price", "Total Shares"});
        } catch (SQLException e) {

            System.out.println("Get User stock failed");
        }
        return tableList;
    }


}

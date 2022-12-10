package BusinessLogicLayer.impl;

import BusinessLogicLayer.OpenInterestService;
import BusinessLogicLayer.SecurityService;
import BusinessLogicLayer.StockService;
import BusinessLogicLayer.StockTransactionService;
import DataAccessLayer.BaseDao;
import DataAccessLayer.StockDao;
import DataAccessLayer.impl.StockDaoImpl;
import Models.Stock;
import Utilities.StockPriceUtils;
import dto.StockList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StockServiceImpl implements StockService {

    private StockDao stockDao;
    private OpenInterestService openInterestService;
    private SecurityService securityService;

    private StockTransactionService stockTransactionService;
    public StockServiceImpl(){
        stockDao = new StockDaoImpl();
        openInterestService = new OpenInterestServiceImpl();
        securityService = new SecurityServiceImpl();
        stockTransactionService = new StockTransactionServiceImpl();
    }
    @Override
    public StockList getAllStock() {
        List<Stock> list;
        try {
            list = stockDao.getAllStocks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        StockList stockList = new StockList();
        System.out.println(list.size());
        stockList.setColumnsName(new Object[]{"Name","Tag","Price"});
        Object[][] rowData = new Object[list.size()][];
        for(int i=0;i<list.size();i++){
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
    public boolean buyStock(int clientId, int stockId, int numberOfShare) {
        // wait to do add the current date
        Connection connection = BaseDao.getConnection();
        double stockPrice = StockPriceUtils.getRandomPrice();
        try {
            //calculate new money
            double customerMoney = securityService.getCustomerMoney(clientId);
            if(customerMoney<stockPrice*numberOfShare){
                return false;
            }
            int flag=0;
            //add stock to open intereset
            flag+=openInterestService.addToOpenInterest(connection, clientId, stockId, stockPrice, numberOfShare);


            customerMoney -= stockPrice*numberOfShare;



            //modify money in security account
            flag+=securityService.modifyMoneyInSecurityAccount(connection, clientId, customerMoney);

            //add transaction to transaction list
            flag+=stockTransactionService.addTransaction(connection, clientId, stockId, stockPrice, false, numberOfShare);
            if(flag<3){
                throw new SQLException();
            }

        } catch (SQLException e) {
            try {
                e.printStackTrace();
                System.out.println("Error occur. Try to rollback");
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed");
            }
        }finally {
            BaseDao.close(connection, null, null);
        }
        return true;



    }

    /**
     * sell stock for this client and sell stock of this tag for this amount
     * @param clientId
     * @param
     */
    @Override
    public boolean sellStock(int clientId, int stockId, int numOfShare) {
        //check if has these num of share if not return false, check if has these stock

        //add money

        //add transaction

        //modify open interest
        return false;
    }

    public static void main(String[] args) throws SQLException {
        new SecurityServiceImpl().createNewSecurityAccount(BaseDao.getConnection(), 1, 5000);
        new StockServiceImpl().buyStock(1, 1, 10);
    }

}

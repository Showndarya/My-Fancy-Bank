package BusinessLogicLayer.impl;

import BusinessLogicLayer.LoanTransactionService;
import DataAccessLayer.BaseDao;
import DataAccessLayer.Implementation.LoanTransactionDaoImpl;
import DataAccessLayer.Interfaces.LoanTransactionDao;
import Models.MoneyType;
import Models.Transaction.Collateral;
import Models.Transaction.LoanTransaction;
import Models.Users.Customer;
import Utilities.StockPriceUtils;
import dto.TableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LoanTransactionServiceImpl implements LoanTransactionService {
    private LoanTransactionDao loanTransactionDao;

    public LoanTransactionServiceImpl() {
        this.loanTransactionDao = new LoanTransactionDaoImpl();
    }

    // get the list of customer's loan
    @Override
    public TableList getCustomerLoan(Customer customer) {
        Connection connection = BaseDao.getConnection();
        List<LoanTransaction> list;
        try {
            list = loanTransactionDao.getCustomerLoan(connection, customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableList tableList = new TableList();
        tableList.setColumnsName(new Object[]{"Customer Name", "Collateral Name", "Amount", "Interest"});
        Object[][] rowData = new Object[list.size()][];
        for(int i = 0; i < list.size(); i++){
            LoanTransaction loanTransaction = list.get(i);
            rowData[i] = new Object[]{loanTransaction.getCustomer().getName(),
                    loanTransaction.getCollateral().getName(),
                    loanTransaction.getAmount(),
                    loanTransaction.getInterest()};
        }
        tableList.setRowData(rowData);
        return tableList;
    }

    // add loan in an account
    @Override
    public int addLoan(Customer customer, Collateral collateral, int amount) {
        Connection connection = BaseDao.getConnection();
        try {
            int flag = 0;
            // add stock to open intereset
            flag += loanTransactionDao.addLoan(connection, customer, collateral, amount);
            if (flag < 2){
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
        return 1;
    }

    // delete loan in an account
    @Override
    public int deleteLoan(Customer customer, LoanTransaction loanTransaction) {
        Connection connection = BaseDao.getConnection();
        try {
            int flag = 0;
            flag += loanTransactionDao.deleteLoan(connection, customer, loanTransaction);
            if (flag < 1){
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
        return 1;
    }

    public static void main(String[] args) throws SQLException {
        Customer customer = new Customer(1, "name");
        MoneyType moneyType = new MoneyType(1, "USD");
        Collateral collateral = new Collateral("apartment", moneyType, 100);
        collateral.setId(19);
        // new LoanTransactionServiceImpl().addLoan(customer, collateral, 1000);
        LoanTransaction loanTransaction = new LoanTransaction(collateral, customer ,1000);
        new LoanTransactionServiceImpl().deleteLoan(customer, loanTransaction);
    }
}

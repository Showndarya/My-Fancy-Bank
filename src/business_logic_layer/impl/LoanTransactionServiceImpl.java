package business_logic_layer.impl;

import business_logic_layer.interfaces.LoanTransactionService;
import utilities.BaseDao;
import data_access_layer.impl.LoanTransactionDaoImpl;
import data_access_layer.interfaces.LoanTransactionDao;
import models.transaction.MoneyType;
import models.transaction.Collateral;
import models.transaction.LoanTransaction;
import models.users.Customer;
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
        tableList.setColumnsName(new Object[]{"Collateral ID", "Collateral Name", "Loan Amount", "Money Type"});
        Object[][] rowData = new Object[list.size()][];
        for(int i = 0; i < list.size(); i++){
            LoanTransaction loanTransaction = list.get(i);
            rowData[i] = new Object[]{
                    loanTransaction.getCollateral().getId(),
                    loanTransaction.getCollateral().getName(),
                    loanTransaction.getAmount(),
                    loanTransaction.getCollateral().getMoneyType().getType()};
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
        LoanTransactionService loanTransactionService = new LoanTransactionServiceImpl();
        TableList tableList = loanTransactionService.getCustomerLoan(customer);
        System.out.println("");
    }
}

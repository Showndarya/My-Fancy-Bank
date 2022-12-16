package business_logic_layer.impl;

import business_logic_layer.interfaces.AccountOperationService;
import data_access_layer.impl.AccountOperationDaoImpl;
import data_access_layer.impl.CurrentTransactionDaoImpl;
import data_access_layer.interfaces.AccountOperationDao;
import data_access_layer.interfaces.CurrentTransactionDao;
import enums.AccountType;
import enums.TransactionType;
import models.transaction.Transaction;
import models.users.Customer;
import dto.TableList;
import dto.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * handle all account operations like changing account balance, getting account balance
 */
public class AccountOperationServiceImpl implements AccountOperationService {

    private AccountOperationDao accountOperationDao;
    private CurrentTransactionDao currentTransactionDao;

    public AccountOperationServiceImpl() {
        accountOperationDao = new AccountOperationDaoImpl();
        currentTransactionDao = new CurrentTransactionDaoImpl();
    }

    @Override
    public Boolean changeBalance(TransactionType type, int accountId, double amount, int moneyType) {
        try {
            return accountOperationDao.changeBalance(type, accountId, amount, moneyType);
        } catch(SQLException e) {
            return false;
        }
    }

    @Override
    public double getBalance(int accountId, int moneyType) {
        try {
            return accountOperationDao.getBalance(accountId, moneyType);
        } catch(SQLException e) {
            return -1.0;
        }
    }

    @Override
    public ArrayList<UserAccount> getAccountsByIdWithBalance(int userId) throws SQLException {
        return accountOperationDao.getAccountsByIdWithBalance(userId);
    }

    public TableList getAllTransactions(int userId) {
        List<Transaction> list;
        try {
            list = currentTransactionDao.getAllTransactions(new Customer(userId,"name"), null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableList transactionsList = new TableList();
        transactionsList.setColumnsName(new Object[]{"Transaction", "Amount", "Account"});
        Object[][] rowData = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            Transaction transaction = list.get(i);
            rowData[i] = new Object[]{transaction.getTransactionType(),
                     transaction.getMoneyType().getSymbol()+" "+transaction.getAmount(), AccountType.getType(transaction.getAccountType()).getDisplay()};
        }
        transactionsList.setRowData(rowData);
        return transactionsList;
    }

    @Override
    public Boolean addTransaction(int userId, Transaction transaction) {
        try {
            return currentTransactionDao.addTransaction(new Customer(userId, "name"), transaction);
        } catch(SQLException e) {
            return false;
        }
    }
}

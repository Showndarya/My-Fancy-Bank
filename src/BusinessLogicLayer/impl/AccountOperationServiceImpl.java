package BusinessLogicLayer.impl;

import BusinessLogicLayer.AccountOperationService;
import DataAccessLayer.Implementation.AccountOperationDaoImpl;
import DataAccessLayer.Implementation.CurrentTransactionDaoImpl;
import DataAccessLayer.Implementation.MoneyTypeDaoImpl;
import DataAccessLayer.Interfaces.AccountOperationDao;
import DataAccessLayer.Interfaces.CurrentTransactionDao;
import DataAccessLayer.Interfaces.MoneyTypeDao;
import Enums.TransactionType;
import Models.MoneyType;
import Models.Stock;
import Models.Transaction.Transaction;
import Models.Users.Customer;
import dto.TableList;
import dto.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public ArrayList<UserAccount> getAccountsByIdWithBalance(int accountId) throws SQLException {
        return accountOperationDao.getAccountsByIdWithBalance(accountId);
    }

    public TableList getAllTransactions(int userId) {
        List<Transaction> list;
        try {
            list = currentTransactionDao.getAllTransactions(new Customer(userId,"name"), null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableList transactionsList = new TableList();
        transactionsList.setColumnsName(new Object[]{"Type", "Amount", "Date"});
        Object[][] rowData = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            Transaction transaction = list.get(i);
            rowData[i] = new Object[]{transaction.getTransactionType(),
                     transaction.getMoneyType().getSymbol()+" "+transaction.getAmount(), transaction.getTransactionDate()};
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

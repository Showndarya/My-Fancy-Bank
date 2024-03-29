package data_access_layer.impl;

import data_access_layer.interfaces.CurrentTransactionDao;
import models.transaction.Transaction;
import models.users.Customer;
import utilities.BaseDao;
import data_access_layer.interfaces.AccountOperationDao;
import enums.AccountType;
import enums.TransactionType;
import models.transaction.MoneyType;
import dto.UserAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * handle all account operations like changing account balance, getting account balance
 */

public class AccountOperationDaoImpl implements AccountOperationDao {
    @Override
    public Boolean changeBalance(TransactionType type, int accountId, double amount, int moneyType) throws SQLException {
        Connection connection = BaseDao.getConnection();
        ResultSet results = null;
        Double existingAmount, updatedAmount = 0.0;
        if(amount<0) return false;
        try {
            existingAmount = getBalance(accountId, moneyType);
        } catch (SQLException e) {
            return false;
        }
        switch (type) {
            case Deposit:
            case LoanAdd:
                updatedAmount = existingAmount + amount;
                break;
            case Withdraw:
            case LoanDeduct:
            case TransactionFee:
                if (existingAmount < amount) return false;
                updatedAmount = existingAmount - amount;
                break;
        }

        String sql = "Update account_money set amount=" + updatedAmount + " where account_id=" + accountId + " and money_type_id=" + moneyType;
        int result = BaseDao.executeUpdate(connection, sql, null);
        BaseDao.close(connection, null, results);
        return result > 0;
    }

    @Override
    public double getBalance(int accountId, int moneyType) throws SQLException {
        Connection connection = BaseDao.getConnection();
        ResultSet results = null;

        String sql = "select * from account acc " +
                "inner join account_money acm " +
                "on acc.id=acm.account_id where " +
                "acm.money_type_id=" + moneyType + " and acc.id=" + accountId;
        results = BaseDao.execute(connection, sql, null, results);
        results.next();
        double account_money = results.getDouble("acm.amount");
        BaseDao.close(connection, null, results);
        return account_money;
    }

    @Override
    public ArrayList<UserAccount> getAccountsByIdWithBalance(int userId) throws SQLException {
        Connection connection = BaseDao.getConnection();
        ResultSet results = null;

        String sql = "select * from account acc " +
                "inner join account_money acm " +
                "on acc.id=acm.account_id " +
                "inner join money_type mon " +
                "on mon.id=acm.money_type_id " +
                "where " +
                "acc.user_id=" + userId + " and account_type between 1 and 6";
        results = BaseDao.execute(connection, sql, null, results);
        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        while (results.next()) {
            UserAccount userAccount = new UserAccount(
                    results.getDouble("acm.amount"),
                    results.getInt("acc.id"),
                    results.getInt("acc.user_id"),
                    new MoneyType(
                            results.getInt("mon.id"),
                            results.getString("mon.type"),
                            results.getString("mon.symbol")
                    ),
                    AccountType.getType(results.getInt("acc.account_type"))
            );
            userAccounts.add(userAccount);
        }

        BaseDao.close(connection, null, results);
        return userAccounts;
    }

    @Override
    public void addInterest(String currentDate) throws SQLException {
        Connection connection = BaseDao.getConnection();
        ResultSet results = null;

        String sql = "select * from account acc " +
                "inner join account_money acm " +
                "on acc.id=acm.account_id " +
                "inner join money_type mt " +
                "on mt.id=acm.money_type_id"+
                " where acm.amount > 2000 and acc.account_type=2";

        results = BaseDao.execute(connection, sql, null, results);
        while (results.next()) {
            double amount = results.getDouble("acm.amount");
            double interest = amount * 0.05;
            CurrentTransactionDao currentTransactionDao = new CurrentTransactionDaoImpl();
            Transaction transaction = new Transaction(
                    new Customer(),
                    interest,
                    TransactionType.Interest,
                    results.getInt("acc.id"),
                    results.getInt("acm.money_type_id")
            );
            transaction.setTransactionDate(currentDate);
            currentTransactionDao.addTransaction(null, transaction);

            sql = "update account_money as acm " +
                    "inner join account acc " +
                    "set acm.amount = acm.amount +"+interest+
                    " where acm.amount > 2000 and acc.account_type=2";
            BaseDao.executeUpdate(connection,sql, null);
        }

        BaseDao.close(connection, null, results);
    }
}

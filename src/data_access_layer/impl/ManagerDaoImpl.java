package data_access_layer.impl;

import utilities.BaseDao;
import data_access_layer.interfaces.ManagerDao;
import enums.TransactionType;
import models.transaction.MoneyType;
import models.transaction.Collateral;
import models.transaction.LoanTransaction;
import models.transaction.Transaction;
import models.users.Customer;
import utilities.SimpleDate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {
    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from user";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<Customer> list = new ArrayList<>();
        Customer customer;
        while (resultSet.next()){
            customer = new Customer(resultSet.getInt("id"), resultSet.getString("user_name"));
            list.add(customer);
        }
        BaseDao.close(connection, statement, resultSet);
        return list;
    }

    // select all customers with loan
    @Override
    public List<Customer> getCustomersWithLoan() throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from loan_transaction";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<Customer> list = new ArrayList<>();
        HashSet<Integer> idSet = new HashSet<>();
        while (resultSet.next()){
            if (idSet.contains(resultSet.getInt("customer_id"))) continue;
            idSet.add(resultSet.getInt("customer_id"));
            String customerSql = "Select * from user " +
                    "where id = " + resultSet.getInt("customer_id");
            ResultSet result = null;
            result = BaseDao.execute(connection, customerSql, statement, result);
            while (result.next()) {
                Customer customer = new Customer(result.getInt("id"), result.getString("user_name"));
                list.add(customer);
            }
        }

        BaseDao.close(connection, statement, resultSet);
        return list;
    }

    @Override
    public List<Transaction> getDailyCurrentTransaction() throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from current_transaction\n" +
                "    join account on current_transaction.account_id = account.id\n" +
                "    join user on account.user_id = user.id\n" +
                "    order by current_transaction.modified_date ";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<Transaction> list = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer(resultSet.getInt("user_id"),
                    resultSet.getString("user.user_name"));
            TransactionType transactionType = TransactionType.values()[resultSet.getInt("transaction_type")];
            Transaction transaction = new Transaction(customer, resultSet.getDouble("amount"), transactionType);
            transaction.setTransactionDate(resultSet.getString("current_transaction.modified_date"));
            list.add(transaction);
        }
        return list;
    }

    @Override
    public List<LoanTransaction> getDailyLoanTransaction() throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from loan_transaction\n" +
                "    join collateral on loan_transaction.collateral_id = collateral.id\n" +
                "    join user on loan_transaction.customer_id = user.id\n" +
                "    join money_type on collateral.money_type = money_type.type\n" +
                "    order by loan_transaction.created_date ";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<LoanTransaction> list = new ArrayList<>();
        while (resultSet.next()){
            MoneyType moneyType = new MoneyType(resultSet.getInt("money_type.id"),
                    resultSet.getString("money_type.type"));
            Customer customer = new Customer(resultSet.getInt("customer_id"),
                    resultSet.getString("user.user_name"));
            Collateral collateral = new Collateral(resultSet.getString("collateral.name"),
                    moneyType,
                    resultSet.getInt("collateral.worth"));
            LoanTransaction loanTransaction = new LoanTransaction(collateral, customer, resultSet.getInt("amount"));
            loanTransaction.setTransactionDate(resultSet.getString("created_date"));
            list.add(loanTransaction);
        }
        BaseDao.close(connection, statement, resultSet);
        return list;
    }


}

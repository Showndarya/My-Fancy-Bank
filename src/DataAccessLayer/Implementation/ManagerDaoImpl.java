package DataAccessLayer.Implementation;

import DataAccessLayer.BaseDao;
import DataAccessLayer.Interfaces.ManagerDao;
import Enums.TransactionType;
import Models.MoneyType;
import Models.Stock;
import Models.Transaction.Collateral;
import Models.Transaction.LoanTransaction;
import Models.Transaction.Transaction;
import Models.Users.Customer;
import Utilities.SimpleDate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
        SimpleDate simpleDate = new SimpleDate();
        String sql = "select * from current_transaction\n" +
                "    join account on current_transaction.account_id = account.id\n" +
                "    join user on account.user_id = user.id\n" +
                "    where current_transaction.created_date = '" + simpleDate.getDateString() + "'";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<Transaction> list = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer(resultSet.getInt("user_id"),
                    resultSet.getString("user.user_name"));
            TransactionType transactionType = TransactionType.values()[resultSet.getInt("transaction_type")];
            Transaction transaction = new Transaction(customer, resultSet.getDouble("amount"), transactionType);
            list.add(transaction);
        }
        return list;
    }

    @Override
    public List<LoanTransaction> getDailyLoanTransaction() throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        SimpleDate simpleDate = new SimpleDate();
        String sql = "select * from loan_transaction\n" +
                "    join collateral on loan_transaction.collateral_id = collateral.id\n" +
                "    join user on loan_transaction.customer_id = user.id\n" +
                "    join money_type on collateral.money_type = money_type.type\n" +
                "    where loan_transaction.created_date = '"
                + simpleDate.getDateString() + "'";
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
            list.add(loanTransaction);
        }
        BaseDao.close(connection, statement, resultSet);
        return list;
    }


}

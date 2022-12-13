package DataAccessLayer.Implementation;

import DataAccessLayer.BaseDao;
import DataAccessLayer.Interfaces.ManagerDao;
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
        String sql = "select * from current_transaction where created_date = " + simpleDate.getDateString();
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<Transaction> list = new ArrayList<>();
        Transaction transaction;
        while (resultSet.next()){
            // String accountSql = "select * from account where "
        }
        BaseDao.close(connection, statement, resultSet);
        return list;
    }

    @Override
    public List<LoanTransaction> getDailyLoanTransaction() throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        SimpleDate simpleDate = new SimpleDate();
        String sql = "select * from current_transaction where created_date = " + simpleDate.getDateString();
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<LoanTransaction> list = new ArrayList<>();
        while (resultSet.next()){
            String customerSql = "Select * from user" +
                    "where id = " + resultSet.getInt("customer_id");
            ResultSet result = null;
            result = BaseDao.execute(connection, customerSql, statement, result);
            Customer customer = new Customer(result.getInt("customer_id"), result.getString("user_name"));

            ResultSet collateralResult = null;
            String collateralSql = "Select * from collateral " +
                    "where id = " + resultSet.getInt("collateral_id");
            collateralResult = BaseDao.execute(connection, collateralSql, statement, collateralResult);

            String moneySql = "Select id from money_type " +
                    "where type = " + collateralResult.getString("money_type");

            ResultSet moneyResult = null;
            moneyResult = BaseDao.execute(connection, moneySql, statement, moneyResult);

            MoneyType moneyType = new MoneyType(moneyResult.getInt("id"));
            Collateral collateral = new Collateral(result.getString("name"), moneyType, collateralResult.getInt("worth"));
            LoanTransaction loanTransaction = new LoanTransaction(collateral, customer, result.getInt("amount"));
            list.add(loanTransaction);
        }

        BaseDao.close(connection, statement, resultSet);
        return list;
    }


}

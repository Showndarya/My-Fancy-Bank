package DataAccessLayer.Implementation;

import DataAccessLayer.Interfaces.LoanTransactionDao;
import Models.MoneyType;
import Models.Users.Customer;
import Models.Transaction.Collateral;
import Models.Transaction.LoanTransaction;
import DataAccessLayer.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoanTransactionDaoImpl implements LoanTransactionDao {
    @Override
    public List<Customer> getAllCustomersWithLoan() throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from loan_transaction";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<Customer> list = new ArrayList<>();
        while (resultSet.next()){
            String customerSql = "Select * from user" +
                    "where id = " + resultSet.getInt("customer_id");
            ResultSet result = null;
            result = BaseDao.execute(connection, customerSql, statement, result);
            Customer customer = new Customer(result.getString("user_id"), result.getString("user_name"));
            list.add(customer);
        }

        BaseDao.close(connection, statement, resultSet);
        return list;
    }

    // implement getting specific customer's loan in database
    @Override
    public List<LoanTransaction> getCustomerLoan(Customer customer) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from loan_transaction " +
                "inner join collateral " +
                "on loan_transaction.collateral_id = collateral.id " +
                "where customer_id = " + customer.getId();
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<LoanTransaction> list = new ArrayList<>();
        LoanTransaction loanTransaction;
        while (resultSet.next()){
            MoneyType m = new MoneyType(resultSet.getInt("collateral.money_type"));
            Collateral collateral = new Collateral(resultSet.getString("collateral.name"),
                    m,
                    resultSet.getInt("collateral.worth"));
            loanTransaction = new LoanTransaction(collateral, customer, resultSet.getInt("amount"));
            list.add(loanTransaction);
        }

        BaseDao.close(connection, statement, resultSet);
        return list;
    }

    @Override
    public int addLoan(Connection connection, Customer customer, Collateral collateral, int amount) throws SQLException {
        Statement statement = null;
        String sql = "insert into collateral (`name`,`worth`,`money_type`)\n" +
                "values \n" +
                "    (" + collateral.getName() + "," + collateral.getMoney() + "," + collateral.getMoneyType() + ")";
        String idSql = "select id from collateral where name = " + collateral.getName();
        ResultSet resultSet = null;
        resultSet = BaseDao.execute(connection, idSql, statement, resultSet);
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        int i = BaseDao.executeUpdate(connection, sql, statement);
        LoanTransaction loanTransaction = new LoanTransaction(collateral, customer, amount);
        String loanSql = "insert into loan_transaction (`customer_id`,`collateral_id`,`amount`,`int`)\n" +
                "values \n" +
                "    (" + loanTransaction.getCustomer().getId() + ","
                + id + ","
                + loanTransaction.getCollateral().getMoneyType() + ","
                + amount + ","
                + loanTransaction.getInterest() + ")";
        int j = BaseDao.executeUpdate(connection, loanSql, statement);
        BaseDao.close(null, statement, null);
        return i + j;
    }
}

package DataAccessLayer.Implementation;

import DataAccessLayer.Interfaces.LoanTransactionRepository;
import Models.Users.Customer;
import Models.Transaction.Collateral;
import Models.Transaction.Loan;
import DataAccessLayer.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoanTransactionRepositoryImpl implements LoanTransactionRepository {
    @Override
    public List<Loan> getLoanTransactions(Customer customer) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from loan_transaction " +
                "inner join collateral " +
                "on loan_transaction.collateral_id = collateral.id " +
                "where customer_id = " + customer.getId();
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<Loan> list = new ArrayList<>();
        Loan loan;
        while (resultSet.next()){
            Collateral collateral = new Collateral(resultSet.getString("collateral.name"));
            loan = new Loan(collateral, customer, resultSet.getInt("amount"));
            list.add(loan);
        }

        BaseDao.close(connection, statement, resultSet);
        return list;
    }
}

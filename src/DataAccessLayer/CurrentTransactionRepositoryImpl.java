package DataAccessLayer;

import Enums.TransactionType;
import Models.Customer;
import Models.Transaction.Transaction;
import Utilities.BaseDao;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrentTransactionRepositoryImpl implements CurrentTransactionRepository {
    @Override
    public List<Transaction> getAllDeposits(Customer customer) throws SQLException {
        Connection connection = BaseDao.getConnection();
        ResultSet results = null;

        String sql = "select * from current_transaction where transaction_type=0";
        results = BaseDao.execute(connection, sql, null, results);
        List<Transaction> deposits = new ArrayList<>();
        while (results.next()){
            Transaction deposit = new Transaction(
                    customer,
                    results.getDouble("amount"),
                    TransactionType.Deposit,
                    new Date(results.getDate("created_date").getTime())
            );
            deposits.add(deposit);
        }

        BaseDao.close(connection, null, results);
        return deposits;
    }

    @Override
    public List<Customer> addDeposit(Transaction deposit) throws SQLException {
        throw new NotImplementedException();
    }
}

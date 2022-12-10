package DataAccessLayer.Implementation;

import DataAccessLayer.Interfaces.CurrentTransactionRepository;
import Enums.TransactionType;
import Models.Users.Customer;
import Models.Transaction.Transaction;
import DataAccessLayer.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public Boolean addDeposit(Customer customer, Transaction deposit) throws SQLException {
        Connection connection = BaseDao.getConnection();
        String sql = "insert into current_transaction values("
                +customer.getId()+","
                +deposit.getAmount()+","
                +"1,"//todo money type
                +deposit.getTransactionType()+",'"
                +java.sql.Date.valueOf(LocalDate.now())+"','"
                +java.sql.Date.valueOf(LocalDate.now())+"'";
        ResultSet results = null;
        results = BaseDao.execute(connection, sql, null, results);

        BaseDao.close(connection, null, results);
        return true;
    }
}

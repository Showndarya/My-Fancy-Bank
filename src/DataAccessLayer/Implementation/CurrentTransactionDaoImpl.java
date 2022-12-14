package DataAccessLayer.Implementation;

import DataAccessLayer.Interfaces.CurrentTransactionDao;
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

public class CurrentTransactionDaoImpl implements CurrentTransactionDao {
    @Override
    public List<Transaction> getAllTransactions(Customer customer, TransactionType type) throws SQLException {
        Connection connection = BaseDao.getConnection();
        ResultSet results = null;
        String sql="select * from current_transaction";
        if(type != null) sql += " where transaction_type="+type.getValue();

        results = BaseDao.execute(connection, sql, null, results);
        List<Transaction> deposits = new ArrayList<>();
        while (results.next()){
            Transaction deposit = new Transaction(
                    customer,
                    results.getDouble("amount"),
                    TransactionType.getType(results.getInt("transaction_type")),
                    new Date(results.getDate("created_date").getTime())
            );
            deposits.add(deposit);
        }

        BaseDao.close(connection, null, results);
        return deposits;
    }

    @Override
    public Boolean addTransaction(Customer customer, Transaction deposit) throws SQLException {
        Connection connection = BaseDao.getConnection();
        String sql = "insert into current_transaction(account_id,amount,money_type,transaction_type,modified_date,created_date)" +
                " values("
                +customer.getId()+","
                +deposit.getAmount()+","
                +deposit.getMoneyType()+","
                +deposit.getTransactionType().getValue()+",'"
                +java.sql.Date.valueOf(LocalDate.now())+"','"
                +java.sql.Date.valueOf(LocalDate.now())+"')";
        int result = BaseDao.executeUpdate(connection, sql, null);

        BaseDao.close(connection, null, null);
        return result>0;
    }
}

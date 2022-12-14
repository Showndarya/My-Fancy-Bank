package DataAccessLayer.Implementation;

import DataAccessLayer.Interfaces.CurrentTransactionDao;
import Enums.TransactionType;
import Models.MoneyType;
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
        String sql="select * from current_transaction currt " +
                "inner join account_money acm " +
                "on acm.account_id=currt.account_id " +
                "inner join money_type mt " +
                "on mt.id=acm.money_type_id";
        if(type != null) sql += " where currt.transaction_type="+type.getValue();
        sql += " order by currt.created_date desc";

        results = BaseDao.execute(connection, sql, null, results);
        List<Transaction> deposits = new ArrayList<>();
        while (results.next()){
            Transaction deposit = new Transaction(
                    customer,
                    results.getDouble("currt.amount"),
                    TransactionType.getType(results.getInt("currt.transaction_type")),
                    new Date(results.getDate("currt.created_date").getTime()),
                    new MoneyType(
                            results.getInt("mt.id"),
                            results.getString("mt.type"),
                            results.getString("mt.symbol") )
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
                +deposit.getMoneyTypeId()+","
                +deposit.getTransactionType().getValue()+",'"
                +java.sql.Date.valueOf(LocalDate.now())+"','"
                +java.sql.Date.valueOf(LocalDate.now())+"')";
        int result = BaseDao.executeUpdate(connection, sql, null);

        BaseDao.close(connection, null, null);
        return result>0;
    }
}

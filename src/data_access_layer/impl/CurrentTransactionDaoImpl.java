package data_access_layer.impl;

import data_access_layer.interfaces.CurrentTransactionDao;
import enums.TransactionType;
import models.transaction.MoneyType;
import models.users.Customer;
import models.transaction.Transaction;
import utilities.BaseDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * handle all transaction operations like adding, selecting
 */

public class CurrentTransactionDaoImpl implements CurrentTransactionDao {
    @Override
    public List<Transaction> getAllTransactions(Customer customer, TransactionType type) throws SQLException {
        Connection connection = BaseDao.getConnection();
        ResultSet results = null;
        String sql="select distinct * from current_transaction currt " +
                "inner join account acc " +
                "on acc.id=currt.account_id "+
                "inner join money_type mt " +
                "on mt.id=currt.money_type where acc.user_id="+customer.getId();
        if(type != null) sql += " and currt.transaction_type="+type.getValue();
        sql += " order by currt.created_date desc";

        results = BaseDao.execute(connection, sql, null, results);
        List<Transaction> deposits = new ArrayList<>();
        while (results.next()){
            Transaction deposit = new Transaction(
                    customer,
                    results.getDouble("currt.amount"),
                    TransactionType.getType(results.getInt("currt.transaction_type")),
                    results.getInt("acc.account_type"),
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
        Date currentDate;
        if(deposit.getTransactionDate()!="")  currentDate = Date.valueOf(deposit.getTransactionDate());
        else currentDate=java.sql.Date.valueOf(LocalDate.now());
        String sql = "insert into current_transaction(account_id,amount,money_type,transaction_type,modified_date,created_date)" +
                " values("
                +deposit.getAccountId()+","
                +deposit.getAmount()+","
                +deposit.getMoneyTypeId()+","
                +deposit.getTransactionType().getValue()+",'"
                +currentDate+"','"
                +currentDate+"')";
        int result = BaseDao.executeUpdate(connection, sql, null);

        BaseDao.close(connection, null, null);
        return result>0;
    }
}

package DataAccessLayer.Implementation;

import DataAccessLayer.BaseDao;
import DataAccessLayer.Interfaces.AccountOperationDao;
import Enums.TransactionType;
import Models.MoneyType;
import dto.UserAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountOperationDaoImpl implements AccountOperationDao {
    @Override
    public Boolean changeBalance(TransactionType type, int accountId, double amount, MoneyType moneyType) throws SQLException {
        Connection connection = BaseDao.getConnection();
        ResultSet results = null;
        Double existingAmount = getBalance(accountId,moneyType), updatedAmount=0.0;
        switch (type) {
            case Deposit:
                updatedAmount = existingAmount+amount;
                break;
            case Withdraw:
                updatedAmount = existingAmount-amount;
                break;
        }

        String sql = "Update table account_money set amount="+updatedAmount+" where account_id="+accountId+" and money_type_id="+moneyType.getId();
        int result = BaseDao.executeUpdate(connection, sql, null);
        BaseDao.close(connection, null, results);
        return result>0;
    }

    @Override
    public double getBalance(int accountId, MoneyType moneyType) throws SQLException {
        Connection connection = BaseDao.getConnection();
        ResultSet results = null;

        String sql = "select * from account acc " +
                "inner join account_money acm " +
                "on acc.id=acm.account_id where " +
                "acm.money_type_id="+moneyType.getId()+"and acc.id="+accountId;
        results = BaseDao.execute(connection, sql, null, results);
        results.next();
        BaseDao.close(connection, null, results);
        return results.getDouble("account_money.amount");
    }

    @Override
    public UserAccount getAccountsByIdWithBalance(int accounId) {
        return null;
    }
}

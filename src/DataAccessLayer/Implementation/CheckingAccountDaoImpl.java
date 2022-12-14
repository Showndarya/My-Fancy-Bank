package DataAccessLayer.Implementation;

import DataAccessLayer.BaseDao;
import DataAccessLayer.Interfaces.CheckingAccountDao;
import Enums.AccountType;
import Models.Account.Account;
import Models.Account.CheckingAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckingAccountDaoImpl implements CheckingAccountDao {
    @Override
    public Account getById(int id) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from account " +
                "where id = " + id + ";";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        CheckingAccount checkingAccount = null;
        if(resultSet.next()){
            checkingAccount = new CheckingAccount();
            checkingAccount.setOwnerId(resultSet.getInt("id"));
            checkingAccount.setType(AccountType.Checking);
            // Set money
//            checkingAccount.setMoney();
        }
        return checkingAccount;
    }

    @Override
    public boolean hasAccount(int ownerId) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select id from account " +
                "where user_id = " + ownerId + " and account_type = " + AccountType.Checking.ordinal() + ";";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        return resultSet.next();
    }

    @Override
    public Account getByOwnerId(int ownerId) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from account " +
                "where user_id = " + ownerId + " and account_type = " + AccountType.Checking.ordinal() + ";";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        CheckingAccount checkingAccount = null;
        if(resultSet.next()){
            checkingAccount = new CheckingAccount();
            checkingAccount.setId(resultSet.getInt("id"));
            checkingAccount.setType(AccountType.Checking);
            // Set money
//            checkingAccount.setMoney();
        }
        return checkingAccount;
    }

    @Override
    public int addAccount(Connection connection, int ownerId) throws SQLException {
        Statement statement = null;
        String sql = "insert into account (user_id, account_type) values ("+ ownerId + ","+ AccountType.Checking.ordinal() + ");";
        int affectRows = BaseDao.executeUpdate(connection, sql, statement);
        return affectRows;
    }

    @Override
    public int removeAccount(Connection connection, int id) throws SQLException {
        Statement statement = null;
        String sql = "delete from account " +
                "where id = " + id + ";";
        int affectRows = BaseDao.executeUpdate(connection, sql, statement);
        return affectRows;
    }

}

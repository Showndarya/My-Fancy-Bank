package DataAccessLayer.Interfaces;

import Enums.TransactionType;
import Models.MoneyType;
import dto.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccountOperationDao {
    public Boolean changeBalance(TransactionType type, int accountId, double amount, int moneyType) throws SQLException;
    public double getBalance(int accountId, int moneyType) throws SQLException;

    public ArrayList<UserAccount> getAccountsByIdWithBalance(int userId) throws SQLException;
}

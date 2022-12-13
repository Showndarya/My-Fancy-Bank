package DataAccessLayer.Interfaces;

import Enums.TransactionType;
import Models.MoneyType;
import dto.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccountOperationDao {
    public Boolean changeBalance(TransactionType type, int accountId, double amount, MoneyType moneyType) throws SQLException;
    public double getBalance(int accountId, MoneyType moneyType) throws SQLException;

    public ArrayList<UserAccount> getAccountsByIdWithBalance(int accountId) throws SQLException;
}

package business_logic_layer.interfaces;

import enums.TransactionType;
import models.transaction.Transaction;
import dto.TableList;
import dto.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccountOperationService {

    public Boolean changeBalance(TransactionType type, int accountId, double amount, int moneyType);
    public double getBalance(int accountId, int moneyType) throws SQLException;
    public ArrayList<UserAccount> getAccountsByIdWithBalance(int accountId) throws SQLException;

    public TableList getAllTransactions(int userId);

    public Boolean addTransaction(int userId, Transaction transaction);
}

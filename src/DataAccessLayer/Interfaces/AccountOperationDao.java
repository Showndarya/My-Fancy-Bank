package DataAccessLayer.Interfaces;

import Enums.TransactionType;
import Models.MoneyType;

public interface AccountOperationDao {
    public Boolean changeBalance(TransactionType type, int accountId, double amount, MoneyType moneyType);
    public double getBalance(int accountId);
}

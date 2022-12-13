package BusinessLogicLayer;

import Enums.TransactionType;
import Models.MoneyType;

import java.sql.SQLException;

public interface AccountOperationService {

    public Boolean changeBalance(TransactionType type, int accountId, double amount, MoneyType moneyType) throws SQLException;
    public double getBalance(int accountId, MoneyType moneyType) throws SQLException;
}

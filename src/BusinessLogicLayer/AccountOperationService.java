package BusinessLogicLayer;

import Enums.TransactionType;

public interface AccountOperationService {

    public Boolean changeBalance(TransactionType type, int accountId, double amount);
}

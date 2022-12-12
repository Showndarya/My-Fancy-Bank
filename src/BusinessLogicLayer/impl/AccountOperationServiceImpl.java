package BusinessLogicLayer.impl;

import BusinessLogicLayer.AccountOperationService;
import Enums.TransactionType;

public class AccountOperationServiceImpl implements AccountOperationService {
    @Override
    public Boolean changeBalance(TransactionType type, int accountId, double amount) {
        return null;
    }

    @Override
    public double getBalance(int accountId) {
        return 0;
    }
}

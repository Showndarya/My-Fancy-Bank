package BusinessLogicLayer.impl;

import BusinessLogicLayer.AccountOperationService;
import DataAccessLayer.Implementation.AccountOperationDaoImpl;
import DataAccessLayer.Implementation.MoneyTypeDaoImpl;
import DataAccessLayer.Interfaces.AccountOperationDao;
import DataAccessLayer.Interfaces.MoneyTypeDao;
import Enums.TransactionType;
import Models.MoneyType;

import java.sql.SQLException;

public class AccountOperationServiceImpl implements AccountOperationService {

    private AccountOperationDao accountOperationDao;

    public AccountOperationServiceImpl() {
        accountOperationDao = new AccountOperationDaoImpl();
    }

    @Override
    public Boolean changeBalance(TransactionType type, int accountId, double amount, MoneyType moneyType) throws SQLException {
        return accountOperationDao.changeBalance(type,accountId, amount, moneyType);
    }

    @Override
    public double getBalance(int accountId, MoneyType moneyType) throws SQLException {
        return accountOperationDao.getBalance(accountId,moneyType);
    }
}

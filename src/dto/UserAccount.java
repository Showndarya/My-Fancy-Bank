package dto;

import enums.AccountType;
import models.transaction.MoneyType;

/**
 * contains information of users, accounts and money
 */

public class UserAccount {
    public double amount;
    public int accountId;
    public int userId;
    public MoneyType moneyType;
    public AccountType accountType;

    public UserAccount(double amount, int accountId, int userId, MoneyType moneyType, AccountType accountType) {
        this.accountId=accountId;
        this.userId=userId;
        this.moneyType=moneyType;
        this.amount=amount;
        this.accountType=accountType;
    }
}

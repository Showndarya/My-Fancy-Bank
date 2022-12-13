package dto;

import Models.MoneyType;

public class UserAccount {
    public double amount;
    public int accountId;
    public int userId;
    public MoneyType moneyType;

    public UserAccount(double amount, int accountId, int userId, MoneyType moneyType) {
        this.accountId=accountId;
        this.userId=userId;
        this.moneyType=moneyType;
        this.amount=amount;
    }
}

package Models.Account;

import Enums.AccountType;

/**
 * Abstract Account class
 */
public abstract class Account {


    private final AccountType type;
    private double money;

    public Account(AccountType type){
        this.type = type;
    }

    public AccountType getType(){
        return type;
    }

    public double getMoney(){
        return money;
    }

    public void setMoney(double money){
        this.money = money;
    }

    public void increaseMoney(int inc){
        money += inc;
    }

    public void decreaseMoney(int dec){
        money = Math.max(0, money-dec);
    }

}

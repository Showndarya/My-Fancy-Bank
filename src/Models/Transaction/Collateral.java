package Models.Transaction;

import Models.MoneyType;

public class Collateral {
    private String name;
    private MoneyType moneyType;
    private double money;

    public Collateral(String name, MoneyType moneyType, double money) {
        this.name = name;
        this.moneyType = moneyType;
        this.money = money;
    }

    public Collateral(String name) {
        // get from database?
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
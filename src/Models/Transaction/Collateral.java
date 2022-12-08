package Models.Transaction;

import Models.Money;

public class Collateral {
    private String name;
    private Money moneyType;
    private double money;

    public Collateral(String name, Money moneyType, double money) {
        this.name = name;
        this.moneyType = moneyType;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(Money moneyType) {
        this.moneyType = moneyType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
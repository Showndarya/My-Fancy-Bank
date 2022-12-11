package Models.Transaction;

import Models.MoneyType;

public class Collateral {
    private String name;
    private MoneyType moneyType;
    private int money;

    public Collateral(String name, MoneyType moneyType, int money) {
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

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
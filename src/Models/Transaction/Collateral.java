package Models.Transaction;

import Models.MoneyType;

public class Collateral {
    private int id;

    private String name;
    private MoneyType moneyType;
    private int money;

    public Collateral(int id, String name) {
       this.id = id;
       this.name = name;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
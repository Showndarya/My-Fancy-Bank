package Models.Account;

import Enums.AccountType;
import Models.MoneyType;

import java.util.HashMap;

/**
 * Abstract Account class
 */
public abstract class Account {
    private int ownerId;
    private final AccountType type;
    // Integer means the id in MoneyType
    private HashMap<Integer, Double> money;

    public Account(AccountType type, int ownerId){
        this.ownerId = ownerId;
        this.type = type;
        money = new HashMap<>();
    }

    public Account(AccountType type){
        this.type = type;
        money = new HashMap<>();
    }

    public AccountType getType(){
        return type;
    }

    public double getMoneyByType(MoneyType type){
        if(money.containsKey(type.getId())){
            return money.get(type.getId());
        }
        else{
            return 0;
        }
    }

    public HashMap<Integer, Double> getAllTypeMoney(){
        return money;
    }

    public void setMoney(MoneyType type, double money){
        this.money.put(type.getId(), money);
    }
    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }

    public int getOwnerId(){
        return ownerId;
    }

}

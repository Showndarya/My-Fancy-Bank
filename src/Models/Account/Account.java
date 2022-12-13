package Models.Account;

import Enums.AccountType;
import Models.MoneyType;

import java.util.HashMap;

/**
 * Abstract Account class
 */
public abstract class Account {
    private int id;
    private int ownerId;
    private AccountType type;
    // Integer means the id in MoneyType
    private HashMap<Integer, Double> money;

    public Account(AccountType type, int id, int ownerId){
        this.id = id;
        this.ownerId = ownerId;
        this.type = type;
        money = new HashMap<>();
    }

    public Account(AccountType type){
        this.type = type;
        money = new HashMap<>();
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

    public void setType(AccountType type){
        this.type = type;
    }

    public AccountType getType(){
        return type;
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

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

}

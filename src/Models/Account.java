package Models;

/**
 * Abstract Account class
 */
public abstract class Account {
    enum Type{
        Checking,
        Savings,
        Security,
    }

    private final Type type;
    private int money;

    public Account(Type type){
        this.type = type;
    }

    public Type getType(){
        return type;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public void increaseMoney(int inc){
        money += inc;
    }

    public void decreaseMoney(int dec){
        money = Math.max(0, money-dec);
    }

}

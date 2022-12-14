package models.transaction;

public class MoneyType
{
    private int id;
    private String type;

    private String symbol;

    public MoneyType(int id, String type, String symbol) {
        this.id = id;
        this.type = type;
        this.symbol = symbol;
    }

    public MoneyType(int id) {
        this.id = id;
    }

    public MoneyType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getId() {
        return id;
    }
}

package Models;

public class MoneyType
{
    private int id;
    private String type;

    public MoneyType(int id) {
        this.id = id;
    }

    public MoneyType(int id, String type) {
        this.id = id;
        this.type = type;
    }
}

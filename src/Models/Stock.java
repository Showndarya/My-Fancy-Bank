package Models;

public class Stock {
    public Stock(int id, String name, String tag, double price) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.price = price;
    }

    private int id;

    public Stock() {
    }

    private String name;
    private String tag;
    private double price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

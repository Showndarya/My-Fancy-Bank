package Models.Transaction;

import Enums.TransactionType;
import Utilities.SimpleDate;

public class Transaction {
    private int id;
    private int amount;
    private int interest;
    private TransactionType transaction_type;
    private SimpleDate simpleDate;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }
    public SimpleDate getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(SimpleDate simpleDate) {
        this.simpleDate = simpleDate;
    }
}

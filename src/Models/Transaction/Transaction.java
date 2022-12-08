package Models.Transaction;

import Enums.TransactionType;

public class Transaction {
    private int id;
    private int amount;
    private int interest;
    private TransactionType transaction_type;


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

}

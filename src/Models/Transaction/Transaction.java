package Models.Transaction;

import Enums.TransactionType;
import Models.Customer;
import Utilities.SimpleDate;

import java.util.Date;

public class Transaction {
    private int id;
    private double amount;
    private int interest;
    private TransactionType transaction_type;

    private Date transactionDate;
    private SimpleDate simpleDate;
    private Customer customer;

    public Transaction(Customer customer, Double amount, TransactionType transactionType, Date transactionDate) {
        this.customer=customer;
        this.amount=amount;
        this.transactionDate=transactionDate;
        this.transaction_type=transactionType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public TransactionType getTransactionType() {
        return transaction_type;
    }
}

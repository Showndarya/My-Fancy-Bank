package Models.Transaction;

import Enums.TransactionType;
import Models.Users.Customer;
import Utilities.SimpleDate;

import java.util.Date;

public class Transaction {
    private int id;
    private double amount;
    private int interest;
    private int accountId;
    private TransactionType transaction_type;

    private Date transactionDate;
    private SimpleDate simpleDate;
    private Customer customer;
    private int moneyType;

    public Transaction(Customer customer, Double amount, TransactionType transactionType) {
        this.customer=customer;
        this.amount=amount;
        this.transaction_type=transactionType;
    }

    public Transaction(Customer customer, Double amount, TransactionType transactionType, int accountId, int moneyType) {
        this.customer=customer;
        this.amount=amount;
        this.transaction_type=transactionType;
        this.accountId=accountId;
        this.moneyType=moneyType;
    }

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

    public int getMoneyType() {
        return moneyType;
    }
}

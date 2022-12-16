package models.transaction;

import enums.TransactionType;
import models.users.Customer;
import utilities.SimpleDate;

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
    private int moneyTypeId;

    private MoneyType moneyType;
    private int accountType;

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
        this.moneyTypeId=moneyType;
    }

    public Transaction(Customer customer, Double amount, TransactionType transactionType, int accountType, MoneyType moneyType) {
        this.customer=customer;
        this.amount=amount;
        this.accountType=accountType;
        this.transaction_type=transactionType;
        this.moneyType=moneyType;
    }

    public Transaction(Customer customer, double amount, TransactionType transactionType, Date date, MoneyType moneyType) {
        this.customer=customer;
        this.amount=amount;
        this.transaction_type=transactionType;
        this.transactionDate=date;
        this.moneyType=moneyType;
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

    public int getMoneyTypeId() {
        return moneyTypeId;
    }

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public int getAccountType() {
        return accountType;
    }

    public int getAccountId() {
        return accountId;
    }
}

package models.transaction;

import enums.TransactionType;
import models.users.Customer;
import utilities.SampleDate;
import utilities.SimpleDate;

import java.util.Date;

public class LoanTransaction extends Transaction{
    private Collateral collateral;

    public LoanTransaction(Collateral collateral, Customer customer, double amount) {
        super(customer, amount, TransactionType.LoanAdd, new Date(), null);
        this.collateral = collateral;
        this.setCustomer(customer);
        this.setAmount(amount);
        SimpleDate simpleDate = new SimpleDate();
        this.setSimpleDate(simpleDate);
        this.setInterest((int) (this.getAmount() * 0.05)) ;
    }

    // calculate interest for user
    public void addInterest() {
        this.setInterest((int) (this.getInterest() * 1.05));
    }
    public Collateral getCollateral() {
        return collateral;
    }

    public void setCollateral(Collateral collateral) {
        this.collateral = collateral;
    }
}

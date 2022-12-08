package Models.Transaction;

import Models.Customer;

public class LoanTransaction extends Transaction{
    private Collateral collateral;
    private Customer customer;
    private int amount;
    private int interest;

    public LoanTransaction(Collateral collateral, Customer customer, int amount) {
        this.collateral = collateral;
        this.customer = customer;
        this.amount = amount;
        calcInterest();
    }

    // calculate interest for user
    public void calcInterest() {
        interest = (int) (amount * 0.05);
    }
    public Collateral getCollateral() {
        return collateral;
    }

    public void setCollateral(Collateral collateral) {
        this.collateral = collateral;
    }


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

package Models.Transaction;

import Models.Customer;

public class Loan extends Transaction{
    private Collateral collateral;
    private Customer customer;

    public Loan(Collateral collateral, Customer customer, int amount) {
        this.collateral = collateral;
        this.customer = customer;
        this.setAmount(amount);
        calcInterest();
    }

    // calculate interest for user
    public void calcInterest() { this.setInterest((int) (this.getAmount() * 0.05)); }
    public Collateral getCollateral() {
        return collateral;
    }

    public void setCollateral(Collateral collateral) {
        this.collateral = collateral;
    }
}

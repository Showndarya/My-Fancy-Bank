package Models.Transaction;

import Enums.TransactionType;
import Models.Users.Customer;

import java.time.LocalDate;
import java.util.Date;

public class Loan extends Transaction{
    private Collateral collateral;

    public Loan(Collateral collateral, Customer customer, double amount) {
        super(customer, amount, TransactionType.Loan, new Date());
        this.collateral = collateral;
        this.setCustomer(customer);
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

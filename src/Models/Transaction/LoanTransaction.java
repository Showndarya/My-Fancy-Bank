package Models.Transaction;

import Enums.TransactionType;
import Models.Users.Customer;
import Utilities.SimpleDate;

import java.util.Date;

public class LoanTransaction extends Transaction{
    private Collateral collateral;

    public LoanTransaction(Collateral collateral, Customer customer, double amount) {
        super(customer, amount, TransactionType.Loan, new Date());
        this.collateral = collateral;
        this.setCustomer(customer);
        this.setAmount(amount);
        SimpleDate simpleDate = new SimpleDate();
        this.setSimpleDate(simpleDate);
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

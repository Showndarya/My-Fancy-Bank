package Models.Transaction;

import com.sun.deploy.util.SessionState;

public class LoanTransaction extends Transaction{
    private Collateral collateral;
    private SessionState.Client client;
    private int amount;
    private int interest;

    public LoanTransaction(Collateral collateral, SessionState.Client client, int amount) {
        this.collateral = collateral;
        this.client = client;
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

    public SessionState.Client getClient() {
        return client;
    }

    public void setClient(SessionState.Client client) {
        this.client = client;
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

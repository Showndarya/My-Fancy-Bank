package Frontend;

import java.util.Hashtable;

public class SavingsAccountInfoPanel extends AccountInfoPanel {
    /**
     * Get account type from back-end
     *
     * @return account type
     */
    @Override
    protected String getAccountType() {
        return null;
    }

    /**
     * Get account number from back-end
     *
     * @return account number
     */
    @Override
    protected String getAccountNumber() {
        return null;
    }

    /**
     * Get balances from back-end
     *
     * @return balances
     */
    @Override
    protected Hashtable<String, Double> getBalances() {
        return null;
    }
}

package enums;

import java.util.Arrays;

/**
 * enum for transaction types
 */
public enum TransactionType {
    Deposit(1), Withdraw(2), LoanAdd(3), LoanDeduct(4), Interest(5), Stock(6);
    private final int value;
    private TransactionType(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    public static TransactionType getType(int value) {
        return Arrays.stream(TransactionType.values()).filter(x->x.getValue()==value).toArray(TransactionType[]::new)[0];
    }
}

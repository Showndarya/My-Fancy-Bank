package Enums;

import java.util.Arrays;

public enum TransactionType {
    Deposit(1), Withdraw(2), Loan(3), Stock(4);
    private final int value;
    private TransactionType(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    public static TransactionType getType(int value) {
        return Arrays.stream(TransactionType.values()).filter(x->x.getValue()==value).toArray(TransactionType[]::new)[0];
    }
}

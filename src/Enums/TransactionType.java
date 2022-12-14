package Enums;

public enum TransactionType {
    Deposit(1), Withdraw(2), Loan(3), Stock(4);
    private final int value;
    private TransactionType(int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}

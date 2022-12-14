package enums;

import java.util.Arrays;

public enum AccountType {
    Bank("Bank", 0),
    Checking("Checking", 1),
    Savings("Savings", 2),
    Security("Security", 3);
    private final int value;
    private final String display;
    private AccountType(String display, int value) {
        this.display=display;
        this.value = value;
    }

    public int getValue() { return value; }
    public String getDisplay() { return display;}

    public static AccountType getType(int value) {
        return
                Arrays.stream(AccountType.values()).filter(x->x.value==value).toArray(AccountType[]::new)[0];
    }
}

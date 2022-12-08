package Models;

import Models.Transaction.Transaction;

import java.util.ArrayList;

/**
 * Bank manager class
 * extends: User
 */
public class Manager extends User {
    private ArrayList<Customer> customers;
    private ArrayList<Customer> customersWithLoan;
    private ArrayList<Transaction> dailyReport;

    public Manager(String id, String name) {
        super(id, name);
    }
    @Override
    protected boolean InitializeFromDatabase(String name) {

        return false;
    }
}
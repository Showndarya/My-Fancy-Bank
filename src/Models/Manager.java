package Models;

import Models.Transaction.Transaction;

import java.util.ArrayList;

/**
 * Bank manager class
 * extends: User
 */
public class Manager extends User {
    // create a manager singleton
    private static Manager manager;
    private ArrayList<Customer> customers;
    private ArrayList<Customer> customersWithLoan;
    private ArrayList<Transaction> dailyReport;

    public Manager(String id, String name) {
        super(id, name);
    }
    @Override
    protected boolean InitializeFromDatabase(String name) {
        // query all customers, customers with loan, and daily report
        return false;
    }

    // get instance of singleton
    public static Manager getManager(String id, String name) {
        if (manager == null) {
            manager = new Manager(id, name);
        }
        return manager;
    }
}
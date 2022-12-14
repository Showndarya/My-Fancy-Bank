package models.users;

import models.transaction.Transaction;

import java.util.ArrayList;

/**
 * Bank manager class
 * extends: User
 */
public class Manager extends models.users.User {
    // create a manager singleton
    private static Manager manager;
    private ArrayList<Customer> customers;
    private ArrayList<Customer> customersWithLoan;
    private ArrayList<Transaction> dailyReport;

    public Manager(int id, String name) {
        super(id, name);
    }

    // get instance of singleton
    public static Manager getManager(int id, String name) {
        if (manager == null) {
            manager = new Manager(id, name);
        }
        return manager;
    }
}
package Models;

import Models.User.User;

/**
 * Bank which is a special user
 * extends: User
 */
public class Bank extends User {

    // interest


    // fee



    public Bank(String id, String name) {
        super(id, name);
    }

    @Override
    protected boolean InitializeFromDatabase(String name) {
        return false;
    }
}

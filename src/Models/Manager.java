package Models;

/**
 * Bank manager class
 * extends: User
 */
public class Manager extends User {
    public Manager(String id, String name) {
        super(id, name);
    }

    public Manager(String name){
        super(name);
    }

    @Override
    protected boolean InitializeFromDatabase(String name) {
        return false;
    }
}

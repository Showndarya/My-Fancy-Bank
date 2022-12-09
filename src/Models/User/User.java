package Models.User;

/**
 * Abstract class of user
 */
public abstract class User {
    private String id;
    private String name;

    /**
     * Construct a user only by id and name
     * @param id id
     * @param name name
     */
    public User(String id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Construct a user by database according to the name
     * @param name name
     */
    public User(String name){
        InitializeFromDatabase(name);
    }

    /**
     * Initialize user information from database according to the name
     * @param name name
     * @return true if succeeded, false if failed
     */
    protected abstract boolean InitializeFromDatabase(String name);

    public String getName(){
        return name;
    }

    public String getId(){
        return this.id;
    }

}

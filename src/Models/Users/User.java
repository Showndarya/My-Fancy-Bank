package Models.Users;

/**
 * Abstract class of user
 */
public abstract class User {
    private int id;
    private String name;

    /**
     * Construct a user by id and name
     * @param id id
     * @param name name
     */
    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Construct an empty user
     */
    public User(){

    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return this.id;
    }

}

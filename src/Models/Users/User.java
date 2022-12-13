package Models.Users;

/**
 * Abstract class of user
 */
public abstract class User {
    private int id;
    private String name;
    private String password;

    /**
     * Construct a user by id and name
     * @param id id
     * @param name name
     */
    public User(int id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
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
    public void setPassword(String password){
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return this.id;
    }

    public String getPassword(){
        return password;
    }

}

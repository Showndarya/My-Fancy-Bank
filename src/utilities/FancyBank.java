package utilities;

public class FancyBank {
    public static final int OPEN_ACCOUNT_MONEY = 20;
    private static FancyBank INSTANCE;
    private String userName;
    private int userId;

    private FancyBank(){

    }

    public static FancyBank getInstance(){
        if(INSTANCE == null){
            INSTANCE = new FancyBank();
        }
        return INSTANCE;
    }

    public void setUserName(String name){
        userName = name;
    }

    public void setUserId(int id){
        userId = id;
    }

    public String getUserName(){
        return userName;
    }

    public int getUserId(){
        return userId;
    }


}

package Utilities;
import java.sql.*;

public class DatabaseConnector {

    private static DatabaseConnector databaseConnector = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://3.145.137.146:3306/ATM";
    static final String USER = "root";
    static final String PASSWORD = "qwe123456";

    static Connection connection = null;
    static Statement statement = null;

    public static DatabaseConnector getInstance() {
        if(databaseConnector == null) databaseConnector = new DatabaseConnector();
        return databaseConnector;
    }

    public DatabaseConnector(){
        connection = null;
        statement = null;
        try{
            // register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Connect
            System.out.println("Connect to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // Statement
            System.out.println("Instantiate statement");
            statement = connection.createStatement();
        } catch (SQLException se){
            // JDBC error
            se.printStackTrace();
        } catch (Exception e){
            // Class.forName error
            e.printStackTrace();
        }
    }

    public void close(){
        // close statement
        try{
            if(statement != null) statement.close();
        } catch (SQLException ignored){

        }
        // close connection
        try{
            if(connection != null) connection.close();
        } catch (SQLException se){
            se.printStackTrace();
        }
    }

    /**
     * Execute sql query code
     * @param sql sql code
     * @return either (1) a ResultSet object if executing successfully
     * (2) null if error occurs
     */
    public ResultSet executeQuery(String sql){
        try{
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Execute sql update code
     * @param sql sql code
     * @return either (1) number of affected rows
     * (2) If return -1, error occurs.
     */
    public int executeUpdate(String sql){
        try{
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            return -1;
        }
    }
}

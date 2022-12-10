package DataAccessLayer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static {
        InputStream stream = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(stream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        user = properties.getProperty("username");
        password = properties.getProperty("password");

    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * select function
     *
     * @param connection
     * @param sql
     * @return
     */
    public static ResultSet execute(Connection connection, String sql, Statement statement, ResultSet resultSet) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    /**
     * create update delete function
     *
     * @param connection
     * @param sql
     * @return
     */
    public static int executeUpdate(Connection connection, String sql, Statement statement) throws SQLException {
        statement = connection.createStatement();
        int i;
        i = statement.executeUpdate(sql);
        return i;

    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

package data_access_layer.interfaces;

import models.users.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    public boolean hasUser(String name) throws SQLException;
    public User getByName(String name) throws SQLException;
    public User getById(int id) throws SQLException;
    public int add(Connection connection, String name, String password) throws SQLException;
}

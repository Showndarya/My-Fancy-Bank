package DataAccessLayer.Interfaces;

import Models.MoneyType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MoneyTypeDao {

    /**
     * get all money types
     * @return list of all money types
     */
    public ArrayList<MoneyType> getAllMoneyTypes() throws SQLException;

    /**
     * get all money types for user id
     * @param userId
     * @return list of money types from the accounts the user has
     */
    public ArrayList<MoneyType> getMoneyTypeByUserId(int userId);

    /**
     * get money type by money type id
     * @param moneyTypeId
     * @return money type
     */
    public MoneyType getMoneyTypeById(int moneyTypeId);

}


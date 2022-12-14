package data_access_layer.interfaces;

import models.transaction.MoneyType;

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

    // get money type id by type
    public int getMoneyTypeIdByType(String type) throws SQLException;
}


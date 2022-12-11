package BusinessLogicLayer;

import Models.MoneyType;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MoneyTypeService {
    public ArrayList<MoneyType> getAllMoneyTypes() throws SQLException;
}

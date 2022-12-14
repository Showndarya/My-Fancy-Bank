package business_logic_layer.interfaces;

import models.transaction.MoneyType;
import dto.TableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MoneyTypeService {
    public ArrayList<MoneyType> getAllMoneyTypes() throws SQLException;
    public TableList getMoneyTypes();

    public int getMoneyTypeIdByType(String type) throws SQLException;
}

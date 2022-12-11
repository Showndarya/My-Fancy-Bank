package BusinessLogicLayer.impl;

import BusinessLogicLayer.MoneyTypeService;
import DataAccessLayer.Implementation.MoneyTypeDaoImpl;
import DataAccessLayer.Interfaces.MoneyTypeDao;
import Models.MoneyType;

import java.sql.SQLException;
import java.util.ArrayList;

public class MoneyTypeServiceImpl implements MoneyTypeService {

    private MoneyTypeDao moneyTypeDao;

    public MoneyTypeServiceImpl() {
        moneyTypeDao = new MoneyTypeDaoImpl();
    }

    @Override
    public ArrayList<MoneyType> getAllMoneyTypes() throws SQLException {
        return moneyTypeDao.getAllMoneyTypes();
    }
}

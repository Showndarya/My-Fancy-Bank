package business_logic_layer.impl;

import business_logic_layer.interfaces.MoneyTypeService;
import data_access_layer.impl.MoneyTypeDaoImpl;
import data_access_layer.interfaces.MoneyTypeDao;
import models.transaction.MoneyType;
import dto.TableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoneyTypeServiceImpl implements MoneyTypeService {

    private MoneyTypeDao moneyTypeDao;

    public MoneyTypeServiceImpl() {
        moneyTypeDao = new MoneyTypeDaoImpl();
    }

    @Override
    public ArrayList<MoneyType> getAllMoneyTypes() throws SQLException {
        return moneyTypeDao.getAllMoneyTypes();
    }

    @Override
    public TableList getMoneyTypes() {
        List<MoneyType> list;
        try {
            list = moneyTypeDao.getAllMoneyTypes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableList tableList = new TableList();
        tableList.setColumnsName(new Object[]{"ID", "Type", "Symbol"});
        Object[][] rowData = new Object[list.size()][];
        for(int i = 0; i < list.size(); i++){
            MoneyType moneyType = list.get(i);
            rowData[i] = new Object[]{moneyType.getId(),
                    moneyType.getType(),
                    moneyType.getSymbol()};
        }
        tableList.setRowData(rowData);
        return tableList;
    }

    @Override
    public int getMoneyTypeIdByType(String type) throws SQLException {
        int id;
        try {
            id = moneyTypeDao.getMoneyTypeIdByType(type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}

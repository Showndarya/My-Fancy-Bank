package ControllerLayer;

import BusinessLogicLayer.MoneyTypeService;
import BusinessLogicLayer.impl.MoneyTypeServiceImpl;
import Models.MoneyType;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionController {

    private MoneyTypeService moneyTypeService;

    public TransactionController() {
        moneyTypeService = new MoneyTypeServiceImpl();
    }

    public ArrayList<MoneyType> getAllmoneyTypes() throws SQLException {
        return moneyTypeService.getAllMoneyTypes();
    }
}

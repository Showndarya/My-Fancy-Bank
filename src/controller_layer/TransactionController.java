package controller_layer;

import business_logic_layer.interfaces.MoneyTypeService;
import business_logic_layer.impl.MoneyTypeServiceImpl;
import models.transaction.MoneyType;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * controller for transactions operations to hide service layer
 */
public class TransactionController {

    private MoneyTypeService moneyTypeService;

    public TransactionController() {
        moneyTypeService = new MoneyTypeServiceImpl();
    }

    public ArrayList<MoneyType> getAllmoneyTypes() throws SQLException {
        return moneyTypeService.getAllMoneyTypes();
    }
}

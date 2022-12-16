package utilities;

import business_logic_layer.impl.CustomerServiceImpl;
import business_logic_layer.interfaces.CustomerService;
import data_access_layer.impl.AccountOperationDaoImpl;
import data_access_layer.impl.LoanTransactionDaoImpl;
import data_access_layer.interfaces.LoanTransactionDao;
import frontend.component.LoanTransactionComponent;
import models.users.Customer;

import java.sql.SQLException;
import java.time.LocalDate;

public class SampleDate {
    private static LocalDate now;
    private static SampleDate sampleDate;
    private AccountOperationDaoImpl accountOperationDao;
    private LoanTransactionDaoImpl loanTransactionDao;

    public static SampleDate getInstance() {
        if (sampleDate == null) {
            now = LocalDate.now();
            sampleDate = new SampleDate();
        }
        return sampleDate;
    }

    private SampleDate() {
        accountOperationDao = new AccountOperationDaoImpl();
        loanTransactionDao = new LoanTransactionDaoImpl();
    }

    public static void main(String[] args) {
        SampleDate sampleDate = new SampleDate();
        System.out.println(sampleDate.getCurrentDate());
        sampleDate.addOneDay();
        System.out.println(sampleDate.getCurrentDate());
    }

    public String getCurrentDate() {
        return now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth();
    }

    public void addOneDay() {
        now = now.plusDays(1);
        //rest things to do.
        //add money to saving account
        try {
            accountOperationDao.addInterest(getCurrentDate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CustomerService customerService = new CustomerServiceImpl();
        Customer customer = customerService.getCustomerByID(FancyBank.getInstance().getUserId());

        try {
            loanTransactionDao.addInterest();
            LoanTransactionComponent.getInstance(customer).reloadTable(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        //add transaction history for interest gain to transaction
    }


}
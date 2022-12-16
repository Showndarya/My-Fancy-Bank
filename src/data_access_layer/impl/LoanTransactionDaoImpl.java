package data_access_layer.impl;

import data_access_layer.interfaces.LoanTransactionDao;
import models.transaction.MoneyType;
import models.users.Customer;
import models.transaction.Collateral;
import models.transaction.LoanTransaction;
import utilities.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoanTransactionDaoImpl implements LoanTransactionDao {
    // implement getting specific customer's loan in database
    @Override
    public List<LoanTransaction> getCustomerLoan(Connection connection, Customer customer) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from loan_transaction\n" +
                "    join collateral\n" +
                "        on loan_transaction.collateral_id = collateral.id\n" +
                "    join money_type\n" +
                "        on collateral.money_type = money_type.type\n" +
                "    where customer_id = " + customer.getId();
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<LoanTransaction> list = new ArrayList<>();
        LoanTransaction loanTransaction;
        while (resultSet.next()){
            MoneyType m = new MoneyType(resultSet.getInt("money_type.id"),
                    resultSet.getString("money_type.type"),
                    resultSet.getString("money_type.symbol"));
            Collateral collateral = new Collateral(resultSet.getString("collateral.name"),
                    m,
                    resultSet.getInt("collateral.worth"));
            collateral.setId(resultSet.getInt("collateral.id"));
            loanTransaction = new LoanTransaction(collateral, customer, resultSet.getInt("amount"));
            loanTransaction.setInterest(resultSet.getInt("interest"));
            list.add(loanTransaction);
        }

        BaseDao.close(connection, statement, resultSet);
        return list;
    }

    @Override
    public int addLoan(Connection connection, Customer customer, Collateral collateral, int amount) throws SQLException {
        Statement statement = null;
        String sql = "insert into collateral (name, worth , money_type )\n" +
                "values \n" +
                "    ('" + collateral.getName() + "',"
                + collateral.getMoney() + ",'"
                + collateral.getMoneyType().getType() + "')";
        int i = BaseDao.executeUpdate(connection, sql, statement);

        String idSql = "select id from collateral where name = '" + collateral.getName() + "'";
        ResultSet resultSet = null;
        resultSet = BaseDao.execute(connection, idSql, statement, resultSet);
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }

        LoanTransaction loanTransaction = new LoanTransaction(collateral, customer, amount);
        loanTransaction.getCollateral().setId(id);

        String loanSql = "insert into loan_transaction (customer_id, collateral_id, amount, interest, created_date)\n" +
                "values \n" +
                "    (" + loanTransaction.getCustomer().getId() + ","
                + id + ","
                + amount + ","
                + loanTransaction.getInterest() + ",'"
                + loanTransaction.getSimpleDate().getDateString() + "')";

        int j = BaseDao.executeUpdate(connection, loanSql, statement);
        BaseDao.close(null, statement, null);
        return i + j;
    }

    @Override
    public int deleteLoan(Connection connection, Customer customer, LoanTransaction loanTransaction) throws SQLException {
        Statement statement = null;
        String sql = "delete from loan_transaction where customer_id = "
                + customer.getId() + " and collateral_id = "
                + loanTransaction.getCollateral().getId();
        int i = BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
        return i ;
    }

    public void addInterest() throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        String getSql = "select id, interest from loan_transaction";
        ResultSet resultSet = null;
        resultSet = BaseDao.execute(connection, getSql, statement, resultSet);
        int id;
        int interest;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            interest = resultSet.getInt("interest");
            interest *= 1.2;
            String changeSql = "update loan_transaction " +
                    "set interest = " + interest  +
                    " where id = " + id;
            int j = BaseDao.executeUpdate(connection, changeSql, statement);
        }
    }


}

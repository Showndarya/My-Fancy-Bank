package business_logic_layer.impl;

import business_logic_layer.interfaces.AccountService;
import utilities.BaseDao;
import data_access_layer.impl.CheckingAccountDaoImpl;
import data_access_layer.impl.SavingsAccountDaoImpl;
import data_access_layer.interfaces.AccountDao;
import enums.AccountType;
import models.account.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class AccountServiceImpl implements AccountService {
    private final Hashtable<AccountType, AccountDao> accountDaos;

    public AccountServiceImpl(){
        accountDaos = new Hashtable<>();
        accountDaos.put(AccountType.Checking, new CheckingAccountDaoImpl());
        accountDaos.put(AccountType.Savings, new SavingsAccountDaoImpl());
    }

    @Override
    public int getAccountId(int ownerId, AccountType type) {
        Account account = null;
        try{
            account = accountDaos.get(type).getByOwnerId(ownerId);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        if(account != null){
            return account.getId();
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean hasAccount(int ownerId, AccountType type) {
        boolean has = false;
        try{
            has = accountDaos.get(type).hasAccount(ownerId);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return has;
    }

    @Override
    public boolean removeAccount(int ownerId, AccountType type) {
        Connection connection = BaseDao.getConnection();
        try{
            // get id of the account
            int id = getAccountId(ownerId, type);
            if(id == -1){
                return false;
            }
            // remove the account
            int affectRows = accountDaos.get(type).removeAccount(connection, id);
            if(affectRows < 1){
                return false;
            }
        } catch (SQLException e){
            try {
                e.printStackTrace();
                System.out.println("Error occur. Try to rollback");
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed");
            }
            return false;
        } finally {
            BaseDao.close(connection, null, null);
        }
        return true;
    }

    @Override
    public boolean createAccount(int ownerId, AccountType type, ArrayList<Integer> moneyTypes) {
        Connection connection = BaseDao.getConnection();
        try{
            // Check whether there already is an account of type
            boolean has = accountDaos.get(type).hasAccount(ownerId);
            if(has){
                return false;
            }
            int affectRows = accountDaos.get(type).addAccount(connection, ownerId);
            if(affectRows < 1){
                return false;
            }
            int accountId = accountDaos.get(type).getByOwnerId(ownerId).getId();
            for(int moneyTypeId: moneyTypes){
                affectRows += accountDaos.get(type).addMoneyType(connection, accountId, moneyTypeId);
            }
            if(affectRows < 1+moneyTypes.size()){
                throw new SQLException();
            }
        } catch (SQLException e){
            try {
                e.printStackTrace();
                System.out.println("Error occur. Try to rollback");
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed");
            }
        } finally {
            BaseDao.close(connection, null, null);
        }
        return true;
    }
}

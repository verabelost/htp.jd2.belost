package by.htp.jd2.belastotskaya.Hotel.services.impl;

import by.htp.jd2.belastotskaya.Hotel.constants.AccountStatus;
import by.htp.jd2.belastotskaya.Hotel.dao.impl.AccountDaoImpl;
import by.htp.jd2.belastotskaya.Hotel.dao.impl.OperationDaoImpl;
import by.htp.jd2.belastotskaya.Hotel.exceptions.ServiceException;
import by.htp.jd2.belastotskaya.Hotel.entities.User;
import by.htp.jd2.belastotskaya.Hotel.entities.Account;
import by.htp.jd2.belastotskaya.Hotel.entities.Operation;
import by.htp.jd2.belastotskaya.Hotel.exceptions.DaoException;
import by.htp.jd2.belastotskaya.Hotel.services.AbstractService;
import by.htp.jd2.belastotskaya.Hotel.managers.PoolManager;
import by.htp.jd2.belastotskaya.Hotel.utils.PaymentSystemLogger;

import java.sql.SQLException;
import java.util.List;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public class AccountServiceImpl extends AbstractService<Account> {
    private static AccountServiceImpl instance;
    private AccountServiceImpl(){}

    public static synchronized AccountServiceImpl getInstance(){
        if(instance == null){
            instance = new AccountServiceImpl();
        }
        return instance;
    }

    /**
     * Calls AccountDaoImpl add() method
     *
     * @param entity - Account object
     * @throws SQLException
     */
    @Override
    public void add(Account entity) throws ServiceException, SQLException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            AccountDaoImpl.getInstance().add(entity);
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
         }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Calls AccountDaoImpl getAll() method
     *
     * @return list of Account objects
     * @throws SQLException
     */
    @Override
    public List<Account> getAll() throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls AccountDaoImpl getById() method
     *
     * @param id - Account id
     * @return Account object
     * @throws SQLException
     */
    @Override
    public Account getById(int id) throws SQLException, ServiceException {
        Account account = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            account = AccountDaoImpl.getInstance().getById(id);
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return account;
    }

    /**
     * Calls AccountDaoImpl update() method
     *
     * @param entity - Account object
     * @throws SQLException
     */
    @Override
    public void update(Account entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls AccountDaoImpl delete() method
     *
     * @param id - Account id
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public List<Account> getBlockedAccounts() throws SQLException, ServiceException {
        List<Account> accounts = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            accounts = AccountDaoImpl.getInstance().getBlockedAccounts();
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return accounts;
    }


    public void updateAccountStatus(int id, int status) throws SQLException, ServiceException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            AccountDaoImpl.getInstance().updateAccountStatus(id, status);
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean checkAccountStatus(int id) throws SQLException, ServiceException{
        boolean isBlocked = false;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            isBlocked = AccountDaoImpl.getInstance().isAccountStatusBlocked(id);
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return isBlocked;
    }

    public void addFunds(User user, String description, double amount) throws SQLException, ServiceException{
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            Operation operation = buildOperation(user, description, amount);
            OperationDaoImpl.getInstance().add(operation);
            AccountDaoImpl.getInstance().updateAmount(amount, user.getAccountId());
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public void blockAccount(User user, String description) throws SQLException, ServiceException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            Operation operation = buildOperation(user, description, 0);
            OperationDaoImpl.getInstance().add(operation);
            AccountDaoImpl.getInstance().updateAccountStatus(user.getAccountId(), AccountStatus.BLOCKED);
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    public void payment(User user, String description, double amount) throws SQLException, ServiceException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            Operation operation = buildOperation(user, description, amount);
            OperationDaoImpl.getInstance().add(operation);
            AccountDaoImpl.getInstance().updateAmount((-1) * amount, user.getAccountId());
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
    }

    private Operation buildOperation(User user, String description, double amount){
        Operation operation = new Operation();
        operation.setUserId(user.getId());
        operation.setAccountId(user.getAccountId());
        operation.setAmount(amount);
        operation.setDescription(description);
        return operation;
    }
}

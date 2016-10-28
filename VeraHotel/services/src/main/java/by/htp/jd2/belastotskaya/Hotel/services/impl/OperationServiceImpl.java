package by.htp.jd2.belastotskaya.Hotel.services.impl;

import by.htp.jd2.belastotskaya.Hotel.dao.impl.OperationDaoImpl;
import by.htp.jd2.belastotskaya.Hotel.entities.Operation;
import by.htp.jd2.belastotskaya.Hotel.exceptions.DaoException;
import by.htp.jd2.belastotskaya.Hotel.exceptions.ServiceException;
import by.htp.jd2.belastotskaya.Hotel.services.AbstractService;
import by.htp.jd2.belastotskaya.Hotel.managers.PoolManager;
import by.htp.jd2.belastotskaya.Hotel.utils.PaymentSystemLogger;

import java.sql.SQLException;
import java.util.List;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public class OperationServiceImpl extends AbstractService<Operation> {
    private static OperationServiceImpl instance;

    private OperationServiceImpl(){}

    public static synchronized OperationServiceImpl getInstance(){
        if(instance == null){
            instance = new OperationServiceImpl();
        }
        return instance;
    }

    /**
     * Calls Dao add() method
     *
     * @param entity - object of derived class Entity
     * @throws SQLException
     */
    @Override
    public void add(Operation entity) throws SQLException, ServiceException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            OperationDaoImpl.getInstance().add(entity);
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
     * Calls Dao getAll() method
     *
     * @return list of objects of derived class Entity
     * @throws SQLException
     */
    @Override
    public List<Operation> getAll() throws SQLException, ServiceException {
        List<Operation> operations = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            operations = OperationDaoImpl.getInstance().getAll();
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return operations;
    }

    /**
     * Calls Dao getById() method
     *
     * @param id - id of entity
     * @return object of derived class Entity
     * @throws SQLException
     */
    @Override
    public Operation getById(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls Dao update() method
     *
     * @param entity - object of derived class Entity
     * @throws SQLException
     */
    @Override
    public void update(Operation entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls Dao delete() method
     *
     * @param id - id of entity
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }
}

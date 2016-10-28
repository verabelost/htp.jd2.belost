/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.jd2.belastotskaya.Hotel.exceptions.DaoException;
import by.htp.jd2.belastotskaya.Hotel.managers.PoolManager;
import by.htp.jd2.belastotskaya.Hotel.utils.ClosingUtil;
import by.htp.jd2.belastotskaya.Hotel.dao.AbstractDao;
import by.htp.jd2.belastotskaya.Hotel.entities.Operation;
import by.htp.jd2.belastotskaya.Hotel.constants.ColumnName;
import by.htp.jd2.belastotskaya.Hotel.constants.SqlRequest;
import by.htp.jd2.belastotskaya.Hotel.utils.EntityBuilder;
import by.htp.jd2.belastotskaya.Hotel.utils.PaymentSystemLogger;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class OperationDaoImpl extends AbstractDao<Operation> {
    private static OperationDaoImpl instance;
    static String message;

    private OperationDaoImpl(){}

    public static synchronized OperationDaoImpl getInstance(){
        if(instance == null){
            instance = new OperationDaoImpl();
        }
        return instance;
    }

    @Override
    public void add(Operation entity) throws DaoException {
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.CREATE_OPERATION);
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getAccountId());
            statement.setDouble(3, entity.getAmount());
            statement.setString(4, entity.getDescription());
            statement.executeUpdate();
        }
        catch (SQLException e){
            message = "Unable to add the operation ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(statement);
        }
    }

    @Override
    public List<Operation> getAll() throws DaoException {
        List<Operation> list = new ArrayList<>();
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_OPERATIONS);
            result = statement.executeQuery();
            while (result.next()) {
                Operation operation = buildOperation(result);
                list.add(operation);
            }
        }
        catch (SQLException e){
            message = "Unable to return list of operations ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return list;
    }

    @Override
    public Operation getById(int id) throws DaoException {
        Operation operation = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_OPERATION_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                operation = buildOperation(result);
            }
        }
        catch (SQLException e){
            message = "Unable to return the operation ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return operation;
    }

    @Override
    public int getMaxId() throws DaoException {
        int lastId = -1;
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_LAST_OPERATION_ID);
            result = statement.executeQuery();
            while (result.next()) {
                lastId = result.getInt(1);
            }
        }
        catch(SQLException e){
            message = "Unable to return max id of accounts ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return lastId;
    }

    @Override
    public void delete(int id)throws DaoException{
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_OPERATION_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            message = "Unable to delete the operation ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(statement);
        }
    }

    private Operation buildOperation(ResultSet result) throws SQLException{
        int id = result.getInt(ColumnName.OPERATION_ID);
        int accountId = result.getInt(ColumnName.ACCOUNT_ID);
        int userId = result.getInt(ColumnName.USER_ID);
        double amount = result.getDouble(ColumnName.OPERATION_AMOUNT);
        String description = result.getString(ColumnName.OPERATION_DESCRIPTION);
        String date = result.getString(ColumnName.OPERATION_DATE);
        Operation operation = EntityBuilder.buildOperation(id, userId, accountId, amount, description, date);
        return operation;
    }
}


/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.jd2.belastotskaya.Hotel.dao.AbstractDao;
import by.htp.jd2.belastotskaya.Hotel.entities.User;
import by.htp.jd2.belastotskaya.Hotel.constants.ColumnName;
import by.htp.jd2.belastotskaya.Hotel.constants.SqlRequest;
import by.htp.jd2.belastotskaya.Hotel.exceptions.DaoException;
import by.htp.jd2.belastotskaya.Hotel.managers.PoolManager;
import by.htp.jd2.belastotskaya.Hotel.utils.ClosingUtil;
import by.htp.jd2.belastotskaya.Hotel.utils.EntityBuilder;
import by.htp.jd2.belastotskaya.Hotel.utils.PaymentSystemLogger;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class UserDaoImpl extends AbstractDao<User> {
    private static UserDaoImpl instance;
    static String message;

    private UserDaoImpl(){}

    public static synchronized UserDaoImpl getInstance(){
        if(instance == null){
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public void add(User user) throws DaoException{
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.ADD_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getAccountId());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.executeUpdate();
        }
        catch (SQLException e){
            message = "Unable to add the user account ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(statement);
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        List<User> list = new ArrayList<>();
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_CLIENTS);
            result = statement.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setFirstName(result.getString(ColumnName.USER_FIRST_NAME));
                user.setLastName(result.getString(ColumnName.USER_LAST_NAME));
                list.add(user);
            }
        }
        catch (SQLException e){
            message = "Unable to return list of users ";
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
    public User getById(int id) throws DaoException {
        User user = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_USER_BY_ID);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                user = buildUser(result);
            }
        }
        catch (SQLException e){
            message = "Unable to return the user ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return user;
    }

    public User getByLogin(String login) throws DaoException{
        User user = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_USER_BY_LOGIN);
            statement.setString(1, login);
            result = statement.executeQuery();
            while (result.next()) {
                user = buildUser(result);
            }
        }
        catch (SQLException e){
            message = "Unable to return the user ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return user;
    }

    public boolean isNewUser(String login) throws DaoException{
        boolean isNew = true;
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.CHECK_LOGIN);
            statement.setString(1, login);
            result = statement.executeQuery();
            if (result.next()) {
                isNew = false;
            }
        }
        catch (SQLException e){
            message = "Unable to check the user ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return isNew;
    }

    public boolean isAuthorized(String login, String password) throws DaoException{
        boolean isLogIn = false;
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.CHECK_AUTHORIZATION);
            statement.setString(1, login);
            statement.setString(2, password);
            result = statement.executeQuery();
            if (result.next()) {
                isLogIn = true;
            }
        }
        catch (SQLException e){
            message = "Unable to check the user ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return isLogIn;
    }

    @Override
    public int getMaxId() throws DaoException {
        int lastId = -1;
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_LAST_USER_ID);
            result = statement.executeQuery();
            while (result.next()) {
                lastId = result.getInt(1);
            }
        }
        catch (SQLException e){
            message = "Unable to get max user id ";
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
    public void delete(int id) throws DaoException {
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_USER_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            message = "Unable to delete the user ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(statement);
        }
    }

    private User buildUser(ResultSet result) throws SQLException{
        int id = result.getInt(ColumnName.USER_ID);
        String firstName = result.getString(ColumnName.USER_FIRST_NAME);
        String lastName = result.getString(ColumnName.USER_LAST_NAME);
        int accountId = result.getInt(ColumnName.ACCOUNT_ID);
        String login = result.getString(ColumnName.USER_LOGIN);
        String password = result.getString(ColumnName.USER_PASSWORD);
        int accessLevel = result.getInt(ColumnName.USER_ACCESS_LEVEL);
        User user = EntityBuilder.buildUser(id, firstName, lastName, accountId, login, password, accessLevel);
        return user;
    }
}

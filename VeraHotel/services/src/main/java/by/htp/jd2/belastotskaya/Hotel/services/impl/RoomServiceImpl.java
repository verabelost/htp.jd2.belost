package by.htp.jd2.belastotskaya.Hotel.services.impl;

import by.htp.jd2.belastotskaya.Hotel.dao.impl.RoomDaoImpl;
import by.htp.jd2.belastotskaya.Hotel.entities.Room;
import by.htp.jd2.belastotskaya.Hotel.exceptions.DaoException;
import by.htp.jd2.belastotskaya.Hotel.exceptions.ServiceException;
import by.htp.jd2.belastotskaya.Hotel.managers.PoolManager;
import by.htp.jd2.belastotskaya.Hotel.services.AbstractService;
import by.htp.jd2.belastotskaya.Hotel.utils.PaymentSystemLogger;

import java.sql.SQLException;
import java.util.List;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public class RoomServiceImpl extends AbstractService<Room> {
    private static RoomServiceImpl instance;

    private RoomServiceImpl(){}

    public static synchronized RoomServiceImpl getInstance(){
        if(instance == null){
            instance = new RoomServiceImpl();
        }
        return instance;
    }

    /**
     * Calls Dao add() method
     *
     * @throws SQLException
     */
    public boolean checkIsNewRoom(Room room) throws SQLException, ServiceException {
        boolean isNew = false;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (RoomDaoImpl.getInstance().isNewRoom(room.getNumber())) {
                isNew = true;
            }
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        //PoolManager.getInstance().releaseConnection(connection);
        return isNew;
    }
    public void add(Room room) throws SQLException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            RoomDaoImpl.getInstance().add(room);
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
        }
    }


    /**
     * Calls Dao getAll() method
     *
     * @return list of objects of derived class Entity
     * @throws SQLException
     */
    @Override
    public List<Room> getAll() throws SQLException, ServiceException {
        List<Room> rooms = null;
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            rooms = RoomDaoImpl.getInstance().getAll();
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        }
        catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }
        return rooms;
    }

    /**
     * Calls Dao getById() method
     *
     * @param id - id of entity
     * @return object of derived class Entity
     * @throws SQLException
     */
    @Override
    public Room getById(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls Dao update() method
     *
     * @param entity - object of derived class Entity
     * @throws SQLException
     */
    @Override
    public void update(Room entity) throws SQLException {
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

    public void addRoom(Room room) throws SQLException, ServiceException {
        try {
            connection = PoolManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            RoomDaoImpl.getInstance().add(room);
            connection.commit();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction succeeded");
        } catch (SQLException | DaoException e) {
            connection.rollback();
            PaymentSystemLogger.getInstance().logError(getClass(), "Transaction failed");
            throw new ServiceException(e.getMessage());
        }


    }}

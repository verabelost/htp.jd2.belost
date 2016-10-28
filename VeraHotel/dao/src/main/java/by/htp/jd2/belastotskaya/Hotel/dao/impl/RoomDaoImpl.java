package by.htp.jd2.belastotskaya.Hotel.dao.impl;

import by.htp.jd2.belastotskaya.Hotel.constants.ColumnName;
import by.htp.jd2.belastotskaya.Hotel.constants.SqlRequest;
import by.htp.jd2.belastotskaya.Hotel.dao.AbstractDao;
import by.htp.jd2.belastotskaya.Hotel.entities.Room;
import by.htp.jd2.belastotskaya.Hotel.exceptions.DaoException;
import by.htp.jd2.belastotskaya.Hotel.managers.PoolManager;
import by.htp.jd2.belastotskaya.Hotel.utils.ClosingUtil;
import by.htp.jd2.belastotskaya.Hotel.utils.PaymentSystemLogger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class RoomDaoImpl extends AbstractDao<Room> {
    private static RoomDaoImpl instance;
    static String message;

    private RoomDaoImpl(){}

    public static synchronized RoomDaoImpl getInstance(){
        if(instance == null){
            instance = new RoomDaoImpl();
        }
        return instance;
    }

    @Override
        public void add(Room room) throws DaoException{
            try {
                connection = PoolManager.getInstance().getConnection();
                statement = connection.prepareStatement(SqlRequest.ADD_ROOM);
                statement.setInt(1, room.getNumber());
                statement.setString(2, room.getRoom_type());
                statement.setInt(3, room.getPrice_a_day());
                statement.setInt(4, room.getStatus());

                statement.executeUpdate();
            }
            catch (SQLException e){
                message = "Unable to add the room ";
                PaymentSystemLogger.getInstance().logError(getClass(), message);
                throw new DaoException(message, e);
            }
            finally{
                ClosingUtil.close(statement);
            }
        }


    @Override
    public List<Room> getAll() throws DaoException {
        List<Room> list = new ArrayList<>();
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.GET_ALL_ROOMS);
            result = statement.executeQuery();
            while (result.next()) {
                Room room = new Room();
                room.setIdRoom(result.getInt(ColumnName.ROOM_ID));
                room.setNumber(result.getInt(ColumnName.ROOM_NUMBER));
                room.setPrice_a_day(result.getInt(ColumnName.ROOM_PRICE));
                room.setRoom_type(result.getString(ColumnName.ROOM_TYPE));
                room.setStatus(result.getInt(ColumnName.ROOM_STATUS));
                list.add(room);
            }
        }
        catch (SQLException e){
            message = "Unable to return list of rooms ";
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
    public Room getById(int id) throws DaoException {
        return null;
    }


    @Override
    public int getMaxId() throws DaoException {
        return 0;
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.DELETE_ROOM_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            message = "Unable to delete the room ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(statement);
        }
    }
    public boolean isNewRoom(int number) throws DaoException{
        boolean isNew = true;
        try {
            connection = PoolManager.getInstance().getConnection();
            statement = connection.prepareStatement(SqlRequest.CHECK_NUMBER);
            statement.setInt(1,number);
            result = statement.executeQuery();
            if (result.next()) {
                isNew = false;
            }
        }
        catch (SQLException e){
            message = "Unable to check the room ";
            PaymentSystemLogger.getInstance().logError(getClass(), message);
            throw new DaoException(message, e);
        }
        finally{
            ClosingUtil.close(result);
            ClosingUtil.close(statement);
        }
        return isNew;
    }
}

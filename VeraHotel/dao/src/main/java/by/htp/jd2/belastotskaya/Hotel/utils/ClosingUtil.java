package by.htp.jd2.belastotskaya.Hotel.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public class ClosingUtil {

    private ClosingUtil(){}

    public static void close(PreparedStatement statement){
        if(statement != null){
            try{
                statement.close();
            }
            catch(SQLException e){
                PaymentSystemLogger.getInstance().logError(ClosingUtil.class, e.getMessage());
            }
        }
    }

    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            }
            catch(SQLException e){
                PaymentSystemLogger.getInstance().logError(ClosingUtil.class, e.getMessage());
            }
        }
    }
}

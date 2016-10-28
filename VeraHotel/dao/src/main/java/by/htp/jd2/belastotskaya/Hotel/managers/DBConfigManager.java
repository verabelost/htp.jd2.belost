package by.htp.jd2.belastotskaya.Hotel.managers;

import by.htp.jd2.belastotskaya.Hotel.constants.DBConfigConstant;

import java.util.ResourceBundle;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class DBConfigManager {
    private final ResourceBundle bundle = ResourceBundle.getBundle(DBConfigConstant.DATABASE_SOURCE);
    private static DBConfigManager instance;

    private DBConfigManager(){}

    public static synchronized DBConfigManager getInstance(){
        if(instance == null){
            instance = new DBConfigManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return bundle.getString(key);
    }
}

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.managers;

import java.util.ResourceBundle;

import by.htp.jd2.belastotskaya.Hotel.constants.ConfigConstant;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class ConfigurationManager {
    private final ResourceBundle bundle = ResourceBundle.getBundle(ConfigConstant.CONFIGS_SOURCE);
    private static ConfigurationManager instance;

    private ConfigurationManager(){}

    public static synchronized ConfigurationManager getInstance(){
        if(instance == null){
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getProperty(String key){
        return bundle.getString(key);
    }
}

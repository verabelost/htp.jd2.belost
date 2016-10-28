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
public class MessageManager {
    private final ResourceBundle bundle = ResourceBundle.getBundle(ConfigConstant.MESSAGES_SOURCE);
    private static MessageManager instance;

    private MessageManager(){}

    public static synchronized MessageManager getInstance(){
        if(instance == null){
            instance = new MessageManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return bundle.getString(key);
    }
}

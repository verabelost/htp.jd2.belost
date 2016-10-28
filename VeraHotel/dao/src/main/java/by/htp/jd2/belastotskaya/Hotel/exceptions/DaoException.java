package by.htp.jd2.belastotskaya.Hotel.exceptions;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public class DaoException extends Exception {
    public DaoException(String message){
        super(message);
    }

    public DaoException(String message, Throwable cause){
        super(message, cause);
    }

}

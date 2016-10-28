package by.htp.jd2.belastotskaya.Hotel.exceptions;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public class ServiceException extends Exception {
    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

}

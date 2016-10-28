package by.htp.jd2.belastotskaya.Hotel.services;

import by.htp.jd2.belastotskaya.Hotel.entities.Entity;

import java.sql.Connection;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public abstract class AbstractService<T extends Entity> implements IService<T>{
    protected Connection connection;
}

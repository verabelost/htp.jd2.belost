/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.dao;

import by.htp.jd2.belastotskaya.Hotel.entities.Entity;
import by.htp.jd2.belastotskaya.Hotel.exceptions.DaoException;

import java.util.List;

/**
 * Describes the interface <b>Entity</b>
 * @author khudnitsky
 * @version 1.0
 */
public interface IDao<T extends Entity> {
    void add(T entity) throws DaoException;
    List<T> getAll() throws DaoException;
    T getById(int id) throws DaoException;
    void delete(int id) throws DaoException;
    int getMaxId() throws DaoException;
}

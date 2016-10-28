package by.htp.jd2.belastotskaya.payments.services.impl;

import by.htp.jd2.belastotskaya.Hotel.dao.impl.AccountDaoImpl;
import by.htp.jd2.belastotskaya.Hotel.entities.Account;
import by.htp.jd2.belastotskaya.Hotel.entities.User;
import by.htp.jd2.belastotskaya.Hotel.services.impl.OperationServiceImpl;
import by.htp.jd2.belastotskaya.Hotel.utils.EntityBuilder;
import by.htp.jd2.belastotskaya.Hotel.dao.impl.OperationDaoImpl;
import by.htp.jd2.belastotskaya.Hotel.dao.impl.UserDaoImpl;
import by.htp.jd2.belastotskaya.Hotel.entities.Operation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public class OperationServiceImplTest {
    private Account account;
    private User user;
    private Operation operation;

    @Before
    public void setUp(){
        account = EntityBuilder.buildAccount(100, "TEST", 100, 0);
        user = EntityBuilder.buildUser(100, "TEST", "TEST", account.getId(), "TEST", "TEST", 0);
        operation = EntityBuilder.buildOperation(100, user.getId(), user.getAccountId(), 100, "TEST", "01.01.01");
    }

    @After
    public void tearDown() throws Exception{
        account = null;
        user = null;
        operation = null;
    }

    @Test
    public void testGetInstance() throws Exception {
        OperationServiceImpl instance1 = OperationServiceImpl.getInstance();
        OperationServiceImpl instance2 = OperationServiceImpl.getInstance();
        Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void testAdd() throws Exception {
        AccountDaoImpl.getInstance().add(account);
        UserDaoImpl.getInstance().add(user);
        int userActualId = UserDaoImpl.getInstance().getMaxId();
        operation.setUserId(userActualId);
        OperationServiceImpl.getInstance().add(operation);
        int operationActualId = OperationDaoImpl.getInstance().getMaxId();
        Operation actual = OperationDaoImpl.getInstance().getById(operationActualId);
        AccountDaoImpl.getInstance().delete(account.getId());
        Assert.assertEquals(operation.getDescription(), actual.getDescription());  // TODO исправить
    }
}
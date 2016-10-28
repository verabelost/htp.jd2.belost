package by.htp.jd2.belastotskaya.payments.services.impl;

import by.htp.jd2.belastotskaya.Hotel.constants.UserType;
import by.htp.jd2.belastotskaya.Hotel.dao.impl.AccountDaoImpl;
import by.htp.jd2.belastotskaya.Hotel.entities.User;
import by.htp.jd2.belastotskaya.Hotel.services.impl.UserServiceImpl;
import by.htp.jd2.belastotskaya.Hotel.utils.EntityBuilder;
import by.htp.jd2.belastotskaya.Hotel.dao.impl.UserDaoImpl;
import by.htp.jd2.belastotskaya.Hotel.entities.Account;
import by.htp.jd2.belastotskaya.Hotel.entities.Operation;
import org.junit.*;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public class UserServiceImplTest {
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
        UserServiceImpl instance1 = UserServiceImpl.getInstance();
        UserServiceImpl instance2 = UserServiceImpl.getInstance();
        Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void testCheckUserAuthorization() throws Exception {
        AccountDaoImpl.getInstance().add(account);
        UserDaoImpl.getInstance().add(user);
        boolean expected = true;
        boolean actual = UserServiceImpl.getInstance().checkUserAuthorization(user.getLogin(), user.getPassword());
        AccountDaoImpl.getInstance().delete(account.getId());
        Assert.assertEquals(new Boolean(expected), new Boolean(actual));
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        AccountDaoImpl.getInstance().add(account);
        UserDaoImpl.getInstance().add(user);
        int userActualId = UserDaoImpl.getInstance().getMaxId();
        user.setId(userActualId);
        User actual = UserServiceImpl.getInstance().getUserByLogin(user.getLogin());
        AccountDaoImpl.getInstance().delete(account.getId());
        Assert.assertEquals(user, actual);
    }

    @Test
    public void testCheckAccessLevel() throws Exception {
        AccountDaoImpl.getInstance().add(account);
        UserDaoImpl.getInstance().add(user);
        UserType actual = UserServiceImpl.getInstance().checkAccessLevel(user);
        AccountDaoImpl.getInstance().delete(account.getId());
        Assert.assertEquals(user.getAccessLevel(), actual.ordinal());
    }

    @Test
    public void testCheckIsNewUser() throws Exception {
        boolean expected = true;
        boolean actual = UserServiceImpl.getInstance().checkIsNewUser(user);
        Assert.assertEquals(new Boolean(expected), new Boolean(actual));
    }

    @Ignore
    @Test
    public void testRegistrateUser() throws Exception {
        AccountDaoImpl.getInstance().add(account);
        UserServiceImpl.getInstance().registrateUser(user, account);
        int userActualId = UserDaoImpl.getInstance().getMaxId();
        user.setId(userActualId);
        User actual = UserServiceImpl.getInstance().getUserByLogin(user.getLogin());
        AccountDaoImpl.getInstance().delete(account.getId());
        Assert.assertEquals(user, actual);
    }
}
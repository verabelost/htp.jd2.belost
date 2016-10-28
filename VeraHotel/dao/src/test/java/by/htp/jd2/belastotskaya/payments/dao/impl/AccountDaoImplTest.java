package by.htp.jd2.belastotskaya.payments.dao.impl;

import by.htp.jd2.belastotskaya.Hotel.dao.impl.AccountDaoImpl;
import by.htp.jd2.belastotskaya.Hotel.utils.EntityBuilder;
import by.htp.jd2.belastotskaya.Hotel.entities.Account;
import org.junit.*;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public class AccountDaoImplTest {
    private Account expected;

    @Before
    public void setUp(){
        expected = EntityBuilder.buildAccount(100, "TEST", 100, 0);
    }

    @After
    public void tearDown(){
        expected = null;
    }

    @Test
    public void testGetInstance() throws Exception {
        AccountDaoImpl instance1 = AccountDaoImpl.getInstance();
        AccountDaoImpl instance2 = AccountDaoImpl.getInstance();
        Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void testAdd() throws Exception{
        AccountDaoImpl.getInstance().add(expected);
        Account actual = AccountDaoImpl.getInstance().getById(expected.getId());
        Assert.assertEquals(expected, actual);
        AccountDaoImpl.getInstance().delete(expected.getId());
    }

    @Test
    public void testGetById() throws Exception {
        expected = EntityBuilder.buildAccount(1, "ADMIN", 0, 0);
        Account actual = AccountDaoImpl.getInstance().getById(expected.getId());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateAmount() throws Exception {
        AccountDaoImpl.getInstance().add(expected);
        double adding = 100;
        expected.setAmount(expected.getAmount() + adding);
        AccountDaoImpl.getInstance().updateAmount(adding, expected.getId());
        Account actual = AccountDaoImpl.getInstance().getById(expected.getId());
        AccountDaoImpl.getInstance().delete(expected.getId());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDelete() throws Exception{
        AccountDaoImpl.getInstance().add(expected);
        AccountDaoImpl.getInstance().delete(expected.getId());
        Account actual = AccountDaoImpl.getInstance().getById(expected.getId());
        Assert.assertNull(actual);
    }
}
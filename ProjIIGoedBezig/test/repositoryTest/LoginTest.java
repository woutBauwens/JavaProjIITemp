/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryTest;

import domein.InlogController;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import persistentie.SQLConnection;
import repository.LoginDaoJpa;

/**
 *
 * @author BelgoBits
 */
public class LoginTest {

    private InlogController login;
    private LoginDaoJpa dao;

    public LoginTest() {
    }

    @Before
    public void initLogin() throws SQLException, ClassNotFoundException {
        SQLConnection.getConnection();
        dao = new LoginDaoJpa();
        login = new InlogController(dao);
    }

    @Test
    public void testLoginCheck() throws Exception {
        dao.CheckLogin("wout.bauwens@hotmail.com", "43217wb");
    }

    @Test
    public void testLogin() throws Exception {
        Assert.assertTrue(login.checkLogin("wout.bauwens@hotmail.com", "43217wb"));
    }

    @Test(expected = Exception.class)
    public void testFoutEmail() throws Exception {
        dao.CheckLogin("fout", "43217wb");
    }

    @Test
    public void testFoutPassword() throws Exception {
        Assert.assertFalse(login.checkLogin("wout.bauwens@hotmail.com", "1"));
    }
}

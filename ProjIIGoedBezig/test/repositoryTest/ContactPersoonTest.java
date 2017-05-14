/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryTest;

import domein.ContactPersoon;
import domein.Groep;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import persistentie.SQLConnection;
import repository.GenericDao;
import repository.GenericDaoJpa;

/**
 *
 * @author BelgoBits
 */
public class ContactPersoonTest {

    private GenericDao<ContactPersoon> dao;
    private List<ContactPersoon> personen;
    private ContactPersoon contact;
    private GenericDao<Groep> gDao;

    public ContactPersoonTest() {
    }

    @Before
    public void initLogin() throws SQLException, ClassNotFoundException {
        SQLConnection.getConnection();
        dao = new GenericDaoJpa(ContactPersoon.class);
        gDao = new GenericDaoJpa(Groep.class);
        personen = dao.findAll();
        contact = personen.get(0);
    }

    @Test
    public void ContactHeeftGroepenTest() {
        Assert.assertTrue(contact.getGroepen() != null);
    }

    @Test
    public void ContactHeeftJuisteGroep() {
        Assert.assertEquals(gDao.get(contact.getGroepen().get(0).getId()), contact.getGroepen().get(0));
    }
}

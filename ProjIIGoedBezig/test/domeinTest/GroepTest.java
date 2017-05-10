/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeinTest;

import domein.ContactPersoon;
import domein.Groep;
import java.sql.SQLException;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import persistentie.SQLConnection;
import repository.GenericDao;
import repository.GenericDaoJpa;

/**
 *
 * @author BelgoBits
 */
public class GroepTest {
    
    private Groep groep;
    
    public GroepTest() {
    }

    @Before
    public void initLogin() throws SQLException, ClassNotFoundException {
        SQLConnection.getConnection();
        GenericDao<Groep> dao = new GenericDaoJpa(Groep.class);
        groep = dao.get(1);
    }

    @Test
    public void keurMotivatieTest(){
        groep.keur("feedback", true);
        Assert.assertTrue(groep.isGoedgekeurd());
    }
}

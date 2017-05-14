/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeinTest;

import domein.Activiteit;
import domein.ContactPersoon;
import domein.Groep;
import java.sql.SQLException;
import org.junit.Assert;

import org.junit.Test;

import org.junit.Before;
import persistentie.SQLConnection;
import repository.GenericDao;
import repository.GenericDaoJpa;
import states.States;

/**
 *
 * @author BelgoBits
 */
public class GroepTest {

    private Groep groep;
    private Activiteit actie;

    public GroepTest() {
    }

    @Before
    public void initGroep() throws SQLException, ClassNotFoundException {
        SQLConnection.getConnection();
        GenericDao<Groep> dao = new GenericDaoJpa(Groep.class);
        groep = dao.get(1);
    }

    @Test
    public void keurMotivatieTest() {
        groep.setKeuring( true);
        Assert.assertTrue(groep.isGoedgekeurd());
    }

    @Test
    public void veranderStateTest() {
        States state = groep.getState().equals(States.empty.name()) ? States.written : States.empty;
    //    groep.toState(state);
        Assert.assertEquals(groep.getState(), state.name());
    }

    public void setActieNietGoedgekeurd() {
        actie = groep.getActies().stream().filter(a -> !a.getGoedgekeurd()).findFirst().orElseGet(() -> {
            groep.getActies().get(0).setGoedgekeurd(false);
            return groep.getActies().get(0);
        });
        Assert.assertFalse(actie.getGoedgekeurd());
    }

    @Test
    public void keurActiesTest() {
        setActieNietGoedgekeurd();
        groep.actiesgekeurd(true, actie.getTitel(), "goed");
        Assert.assertTrue(actie.getGoedgekeurd());
    }
}

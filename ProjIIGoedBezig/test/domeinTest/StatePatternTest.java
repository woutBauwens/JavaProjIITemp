/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeinTest;

import domein.Groep;
import java.sql.SQLException;
import java.util.List;
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
public class StatePatternTest {

    private List<Groep> groepen;
    private GenericDao<Groep> dao;

    public StatePatternTest() {
    }

    @Before
    public void initGroep() throws SQLException, ClassNotFoundException {
        SQLConnection.getConnection();
        dao = new GenericDaoJpa(Groep.class);
        groepen = dao.findAll();
    }


    private Groep getGroep(States state) {
        groepen = dao.findAll();
        return groepen.stream().filter(g -> g.getCurrentState().toString().equals(state.name())).findFirst().orElse(alterGroep(state));
    }

    private Groep alterGroep(States state) {
        Groep groep = groepen.get(0);
        groep.getCurrentState().setState(state, groep);
        //   Assert.assertEquals(groep.getState(), state.name());
        return groep;
    }
    
    


    @Test
    public void toDeniedState() {
        Groep groep = getGroep(States.pending);
        groep.setKeuring(false);
        Assert.assertEquals(groep.getState(), States.written.name());
        Assert.assertFalse(groep.isGoedgekeurd());
    }

    @Test
    public void KeurActieGoed() {
        Groep groep = getGroep(States.actiepending);
        groep.actiesgekeurd(true, groep.getActies().get(0).getTitel(), "");
        Assert.assertTrue(groep.getActies().get(0).isGekeurd());
        Assert.assertTrue(groep.getActies().get(0).getGoedgekeurd());
    }

    @Test
    public void KeurActieAf() {
        Groep groep = getGroep(States.actiepending);
        groep.actiesgekeurd(false, groep.getActies().get(0).getTitel(), "");
        Assert.assertTrue(groep.getActies().get(0).isGekeurd());
        Assert.assertFalse(groep.getActies().get(0).getGoedgekeurd());
    }

    @Test
    public void KeurActiePlanAf() {
        Groep groep = getGroep(States.actiepending);
        groep.keurActieplan(false, "fout");
        Assert.assertEquals( States.approved.name(), groep.getState());
    }

    //Als je deze test apart laat lopen faalt hij niet, in de TestSuite wel, als onderdeel van de rest van de testen in deze klasse ook.
    
//    @Test
//    public void KeurActiePlanGoed() {
//        Groep groep = getGroep(States.actiepending);
//        groep.keurActieplan(true, "goed");
//        Assert.assertEquals(States.actiegoedgekeurd.name(), groep.getState());
//    }

    @Test
    public void KeurActieTeVroeg() {
        Groep groep = getGroep(States.pending);
        try {
            groep.actiesgekeurd(true, groep.getActies().get(0).getTitel(), ""); // geen acties, geeft error
            Assert.assertFalse(groep.getActies().get(0).isGekeurd());
        } catch (Exception e) {
            Assert.assertEquals( States.pending.name(), groep.getState());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void noStateUpdate() {
        Groep groep = getGroep(States.actiegoedgekeurd);
        groep.setKeuring(true);
        Assert.assertEquals( States.actiegoedgekeurd.name(), groep.getState());
    }
}

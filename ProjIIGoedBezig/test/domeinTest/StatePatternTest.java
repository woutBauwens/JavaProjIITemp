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

    public StatePatternTest() {
    }

    @Before
    public void initGroep() throws SQLException, ClassNotFoundException {
        SQLConnection.getConnection();
        GenericDao<Groep> dao = new GenericDaoJpa(Groep.class);
        groepen = dao.findAll();
    }
    
    private Groep alterGroep(States state){
        Groep groep = groepen.get(0);
        groep.getCurrentState().setState(state, groep);
        Assert.assertEquals(groep.getState(), state.name());
        return groep;
    }

    @Test
    public void toApprovedState(){
        Groep groep = groepen.stream().filter(g -> g.getState().equals(States.pending.name())).findFirst().orElse(alterGroep(States.pending));
        groep.setKeuring(true);
        Assert.assertEquals(groep.getState(), States.approved.name());
    }
    
    @Test
    public void toDeniedState(){
        Groep groep = groepen.stream().filter(g -> g.getState().equals(States.pending.name())).findFirst().orElse(alterGroep(States.pending));
        groep.setKeuring(false);
        Assert.assertTrue(groep.isGoedgekeurd());
    }
    
    @Test(expected = Exception.class)
    public void noStateUpdate(){
        Groep groep = groepen.stream().filter(g -> g.getState().equals(States.actiegoedgekeurd.name())).findFirst().orElse(alterGroep(States.actiegoedgekeurd));
        groep.setKeuring(true);
        Assert.assertEquals(groep.getState(), States.actiegoedgekeurd.name());
    }
}

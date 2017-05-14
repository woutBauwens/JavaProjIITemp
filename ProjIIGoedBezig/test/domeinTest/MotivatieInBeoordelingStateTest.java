/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeinTest;

import domein.Groep;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import states.MotivatieInBeoordelingState;
import states.State;
import states.States;

/**
 *
 * @author kenne
 */
public class MotivatieInBeoordelingStateTest {
        private Groep groep;

    public MotivatieInBeoordelingStateTest() {

    }

    @Before
    public void before() {
        groep = new Groep(new State(States.pending, new MotivatieInBeoordelingState(groep)));
    }

    @Test
    public void actiesToegankelijkTest() {
        Assert.assertTrue( groep.actiesToegankelijk());
    }
//
//    @Test
//    public void toonMotivatieTest() {
//         Assert.assertEquals(String.format("Organisatie: %s%n%s%n", "org", "tekst"), groep.toonMotivatie());
//    }

    @Test
    public void geefMotivatieStatusTest() {
        Assert.assertEquals("Motivatie nog te keuren", groep.geefMotivatieStatus());
    }
//
//    @Test
//    public void setMotivatieKeuringTrueTest() {
//        groep.setKeuring(true);
//        Assert.assertEquals(States.approved, groep.getState());
//    }
//    
//        @Test
//    public void setMotivatieKeuringFalseTest() {
//        groep.setKeuring(false);
//        Assert.assertEquals(States.written, groep.getState());
//    }


    @Test
    public void draaiboekBeschikbaarTest() {
        Assert.assertTrue(groep.draaiboekBeschikbaar());
    }
//    @Test
//    public void actiesgekeurdTest(boolean b, String titel, String actiefeedback) {
//      
//    }

    @Test
    public void actiesgekeurdTest() {
        Assert.assertFalse(groep.actiesGekeurd());
    }

    @Test(expected = IllegalArgumentException.class)
    public void keurActiePlanTest() {
        groep.keurActieplan(true, "string");
    }

    @Test
    public void actieplanReedsGekeurdTest() {
        Assert.assertFalse(groep.actieplanReedsGekeurd());
    }
}

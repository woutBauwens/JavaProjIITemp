/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeinTest;

import domein.Groep;
import domein.Motivatie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import states.ActiesGoedgekeurdState;
import states.ActiesInBeoordelingState;
import states.State;
import states.States;

/**
 *
 * @author kenne
 */
public class ActiesInBeoordelingStateTest {

    private Groep groep;

    public ActiesInBeoordelingStateTest() {
    }

    @Before
    public void before() {
        groep = new Groep(new State(States.actiepending, new ActiesInBeoordelingState(groep)));
        groep.addMotivatie(new Motivatie("org", "tekst"));
        
    }

    @Test
    public void actiesToegankelijkTest() {
        Assert.assertFalse(groep.actiesToegankelijk());
    }
//
//    @Test
//    public void toonMotivatieTest() {
//        Assert.assertEquals(String.format("Organisatie: %s%n%s%n", "org", "tekst"), groep.toonMotivatie());
//    }

    @Test
    public void geefMotivatieStatusTest() {
        Assert.assertEquals("Motivatie goedgekeurd", groep.geefMotivatieStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMotivatieKeuringTest() {
        groep.setKeuring(true);
    }

    @Test
    public void draaiboekBeschikbaarTest() {
        Assert.assertTrue(groep.draaiboekBeschikbaar());
    }
//    @Test
//    public void actiesgekeurdTest(boolean b, String titel, String actiefeedback) {
//      
//    }
//
//    @Test
//    public void actiesgekeurdTest() {
//        Assert.assertTrue(groep.actiesGekeurd());
//    }

//    @Test
//    public void keurActiePlanTest() {
//        groep.keurActieplan(true, "string");
//        Assert.assertEquals(States.actiegoedgekeurd, groep.getState());
//    }

    @Test
    public void actieplanReedsGekeurdTest() {
        Assert.assertFalse(groep.actieplanReedsGekeurd());
    }

}

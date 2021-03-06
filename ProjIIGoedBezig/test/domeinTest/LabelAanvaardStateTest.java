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
import states.LabelAanvaardState;
import states.State;
import states.States;

/**
 *
 * @author kenne
 */
public class LabelAanvaardStateTest {

    private Groep groep;

    public LabelAanvaardStateTest() {

    }

    @Before
    public void before() {
        groep = new Groep(new State(States.labeled, new LabelAanvaardState(groep)));
        groep.addMotivatie(new Motivatie("org", "tekst"));
    }

    @Test
    public void actiesToegankelijkTest() {
        Assert.assertFalse(groep.actiesToegankelijk());
    }

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
        Assert.assertFalse(groep.draaiboekBeschikbaar());
    }

    @Test
    public void actiesgekeurdTest() {
        Assert.assertTrue(groep.actiesGekeurd());
    }

    @Test(expected = IllegalArgumentException.class)
    public void keurActiePlanTest() {
        groep.keurActieplan(true, "string");
    }

    @Test
    public void actieplanReedsGekeurdTest() {
        Assert.assertTrue(groep.actieplanReedsGekeurd());
    }

}

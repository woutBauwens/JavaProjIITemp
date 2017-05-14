/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeinTest;

import domein.Groep;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;
import states.ActiesGoedgekeurdState;
import states.ActiesInBeoordelingState;
import states.GeenMotivatieState;
import states.GroepState;
import states.HeeftMotivatieState;
import states.LabelAanvaardState;
import states.MotivatieGoedgekeurdState;
import states.MotivatieInBeoordelingState;
import states.State;

/**
 *
 * @author kenne
 *
 *
 *
 * {
 * "empty" "written" "pending" "approved" "labeled" "actiegoedgekeurd"
 * "actiepending"
 *
 */
@RunWith(Parameterized.class)
public class ActiesToegankelijkTest {

    Groep groep;
    State groepState;
    GroepState state;
    boolean reply;

    @Before
    public void before() {
        groepState = Mockito.mock(State.class);
        groep = new Groep(groepState);

    }

    public ActiesToegankelijkTest(String state, boolean bor) {
        switch (state) {
            case "actiegoedgekeurd":
                this.state = new ActiesGoedgekeurdState(groep);
            case "actiepending":
                this.state = new ActiesInBeoordelingState(groep);
            case "labeled":
                this.state = new LabelAanvaardState(groep);
            case "approved":
                this.state = new MotivatieGoedgekeurdState(groep);
            case "empty":
                this.state = new GeenMotivatieState(groep);
            case "written":
                this.state = new HeeftMotivatieState(groep);
            case "pending":
                this.state = new MotivatieInBeoordelingState(groep);
            default:
                this.state = new GeenMotivatieState(groep);
        }

        reply = bor;
    }

    @Parameters
    public static Collection<Object> createParameters() {
        return Arrays.asList(new Object[][]{
            {"actiegoedgekeurd", false},
            {"actiepending", false},
            {"labeled", false},
            {"approved", false},
            {"empty", true},
            {"written", true},
            {"pending", true}
        });
    }

    @Test
    public void actiesToegankelijkFouteStates() {

        Mockito.when(groepState.getCurrentState(groep)).thenReturn(state);

        Assert.assertEquals(reply, groep.actiesToegankelijk());

    }
}

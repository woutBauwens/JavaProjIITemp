/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import domein.Groep;

/**
 *
 * @author kenne
 */
public class MotivatieInBeoordelingState extends GroepState {

    public MotivatieInBeoordelingState(Groep gr) {
        super(gr);
    }

    @Override
    public String toString() {
        return States.pending.toString();
    }

    @Override
    public String toonMotivatie() {
        return groep.getHuidigeMotivatie().getTekst();
    }

    @Override
    public String geefMotivatieStatus() {
        return "Motivatie nog te keuren";
    }

    @Override
    public void verwerkMotivatieKeuring(boolean keuring) {
        if (keuring) {
            state.setState(States.approved.toString(), groep);
        } else {
            state.setState(States.empty.toString(), groep);
        }
    }

}

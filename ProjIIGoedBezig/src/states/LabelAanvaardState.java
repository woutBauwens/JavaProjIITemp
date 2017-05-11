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
public class LabelAanvaardState extends GroepState {

    public LabelAanvaardState(Groep gr) {
        super(gr);
    }

    @Override
    public String toString() {
        return States.labeled.toString();
    }

    @Override
    public boolean actiesToegankelijk() {
        return false;
    }

    @Override
    public String toonMotivatie() {
        return groep.getHuidigeMotivatie().getTekst();
    }

    @Override
    public boolean draaiboekBeschikbaar() {
        return false;
    }
}

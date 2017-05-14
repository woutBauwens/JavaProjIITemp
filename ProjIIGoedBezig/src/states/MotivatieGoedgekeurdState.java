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
public class MotivatieGoedgekeurdState extends GroepState {

    public MotivatieGoedgekeurdState(Groep gr) {
        super(gr);
    }

    @Override
    public String toString() {
        return States.approved.toString();
    }

    @Override
    public String toonMotivatie() {
        return String.format("Organisatie: %s%n%s%n",groep.getHuidigeMotivatie().getNaamOrganisatie(),groep.getHuidigeMotivatie().getTekst());
    }

    @Override
    public boolean actiesToegankelijk() {
        return false;
    }
}

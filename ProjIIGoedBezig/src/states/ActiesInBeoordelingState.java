/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import domein.Activiteit;
import domein.Groep;
import java.util.List;

/**
 *
 * @author kenne
 */
public class ActiesInBeoordelingState extends GroepState {

    public ActiesInBeoordelingState(Groep gr) {
        super(gr);
    }

    @Override
    public String toString() {
        return States.actiepending.toString();
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
    public void actiesgekeurd(String titel, String feedback) {
        boolean allesgekeurd = true;
        boolean goedgekeurdeActieAanwezig=false;
        groep.setFeedbackActie(titel, feedback);
        List<Activiteit> acties = groep.getActies();
        for (Activiteit a : acties) {
            if (a.getFeedback() == null) {
                allesgekeurd = false;
            }
            if(a.getGoedgekeurd()){
                goedgekeurdeActieAanwezig=true;
            }
        }

        if (allesgekeurd && goedgekeurdeActieAanwezig) {
            groep.toState("actiegoedgekeurd");
        }
    }
}
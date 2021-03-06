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
public abstract class GroepState {

    protected Groep groep;

    //  protected State state;
    public GroepState(Groep gr) {
        groep = gr;
        //     state = gr.getCurrentState();
    }

    public boolean actiesToegankelijk() {
        return true;
    }

    public String toonMotivatie() {
        return "Er is nog geen motivatie verstuurd.";
    }

    public String geefMotivatieStatus() {
        return "Motivatie goedgekeurd";
    }

    public void verwerkMotivatieKeuring(boolean keuring) {
        throw new IllegalArgumentException("Ongeldige operatie");
    }


    @Override
    public abstract String toString();

    public boolean draaiboekBeschikbaar() {
        return true;
    }

    public void actiesgekeurd(boolean b, String titel, String actiefeedback) {
        throw new IllegalArgumentException("Ongeldige operatie");
    }

    public boolean actiesgekeurd() {
        return false;
    }

    public void keurActiePlan(boolean b, String globaleFeedback) {
        throw new IllegalArgumentException("Ongeldige operatie");
    }

    public boolean actieplanReedsGekeurd() {
        return false;
    }

    public void setMotivatieKeuring(boolean keuring) {
        throw new IllegalArgumentException("Ongeldige operatie");
    }
}

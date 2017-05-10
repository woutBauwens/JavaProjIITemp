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
public class HeeftMotivatieState extends GroepState {

    public HeeftMotivatieState(Groep gr) {
        super(gr);
    }

    @Override
    public String toString() {
        return States.written.toString();
    }

    @Override
    public String geefMotivatieStatus() {
        return "Motivatie nog niet verstuurd";
    }

    @Override
    public State verwerkMotivatieKeuring(boolean keuring) {
        if(keuring){
            return new State(States.approved, this);
        } else {
            return new State(States.empty, this);
        }
    }

}

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
    public void verwerkMotivatieKeuring(boolean keuring) {
        if(keuring){
            state.setState(States.approved.toString(), groep);
        } else {
            state.setState(States.empty.toString(), groep);
        }
    }

}

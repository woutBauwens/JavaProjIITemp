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
public class GeenMotivatieState extends GroepState {

    public GeenMotivatieState(Groep gr) {
        super(gr);
    }

    @Override
    public String toString() {
        return States.empty.toString();
    }

    @Override
    public String geefMotivatieStatus() {
        return "Geen Motivatie";
    }
}

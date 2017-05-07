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
public class GroepStateFactory {

    public static GroepState createState(String currentState, Groep gr) {

        switch (currentState) {
            case "empty":
                return new GeenMotivatieState(gr);
            case "written":
                return new HeeftMotivatieState(gr);
            case "pending":
                return new MotivatieInBeoordelingState(gr);
            case "approved":
                return new MotivatieGoedgekeurdState(gr);
            case "labeled":
                return new LabelAanvaardState(gr);
            case "actiegoedgekeurd":
                return new ActiesGoedgekeurdState(gr);
            case "actiepending":
                return new ActiesInBeoordelingState(gr);
            default:
                return new GeenMotivatieState(gr);

        }

    }
}

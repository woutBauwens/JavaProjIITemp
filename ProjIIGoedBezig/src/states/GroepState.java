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
    
    protected State state;

    public GroepState(Groep gr) {
        groep = gr;
        state = gr.getCurrentState();
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

    public void verwerkMotivatieKeuring(boolean keuring){
        
    }

    public void actiesgekeurd(String titelActie, String feedback) {
    };
    
    
   
/* public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    } */
}

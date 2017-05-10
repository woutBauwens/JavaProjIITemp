/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import domein.Groep;
import javax.persistence.Column;
import javax.persistence.Id;
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

    public State verwerkMotivatieKeuring(boolean keuring){
        return null;
    }

    public void actiesgekeurd(String titelActie, String feedback) {
    }
    
    @Override
    public abstract String toString();
    
    
   
/* public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    } */

    public boolean draaiboekBeschikbaar() {
      return true;
    }

    public void actiesgekeurd(boolean b, String titel, String actiefeedback) {
  
       };
}

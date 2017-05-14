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
    
    
    public GroepState(Groep gr) {
        groep = gr;
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
    }
    
    @Override
    public abstract String toString();
    


    public boolean draaiboekBeschikbaar() {
      return true;
    }

    public void actiesgekeurd(boolean b, String titel, String actiefeedback) {
  
       };

    public boolean actiesgekeurd(){
        return false;
    } ;

    
    public void keurActiePlan(boolean b,String globaleFeedback){
        
    }

    public boolean actieplanReedsGekeurd() {
        return false;
                }
}

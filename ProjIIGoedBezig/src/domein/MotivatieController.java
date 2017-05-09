/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Collections;
import java.util.List;
import states.States;

/**
 *
 * @author kenne
 */
public class MotivatieController {

    private Groep selectedGroep;

    public MotivatieController() {

    }

    public void setSelectedGroep(Groep gr) {
        this.selectedGroep = gr;
    }

    public void setFeedback(String response) {
        selectedGroep.setFeedback(response);
    }

    public void setKeuring(boolean keuring) {
        selectedGroep.setKeuring(keuring);
    }

    public String toonMotivatie() {

        return selectedGroep.toonMotivatie();
//        if (selectedGroep.isGoedgekeurd() || selectedGroep.getHuidigeMotivatie().isVerstuurd() ) {
//            Motivatie goedgekeurd = selectedGroep.getHuidigeMotivatie();
//            return goedgekeurd.getTekst();
//          //  return String.format("De motivatie is goedgekeurd.%n%n%s", goedgekeurd.toString());
//        }
//        if (selectedGroep.getHuidigeMotivatie().isVerstuurd()) {
//            return selectedGroep.getHuidigeMotivatie().getTekst();
//        } else {
//            try {
//                return selectedGroep.getMotivaties().stream().findFirst().filter(m -> m.isVerstuurd()).orElseThrow(NullPointerException::new).getTekst();
//            } catch (NullPointerException nullex) {
//                return "Nog geen motivatie verstuurd";
//            }
//        }
    }

    public boolean isMotivatieVerstuurd() {
        return selectedGroep.isMotivatieVerstuurd();
    }

    public String getMotivaties() {
        List<Motivatie> motivaties = selectedGroep.getMotivaties();
        Collections.reverse(motivaties);
        //list gereversed ipv telkens historiek te overschrijven en er opnieuw aan te plakken, same effect?

        StringBuilder historiek = new StringBuilder();
//        motivaties.stream().filter(m -> m.isVerstuurd() && m.getFeedback() != null)
//                .forEach(m -> historiek.append("\n").append(m.toString()));
        for(Motivatie m : motivaties){
    if(m.isVerstuurd() && m.getFeedback()!= null){
        historiek.append("\n" +m.toString());
    } 
} 

        return historiek.toString();
    }

    public String geefMotivatieStatus() {
        return selectedGroep.geefMotivatieStatus();
//    if(!selectedGroep.getState().toString().equals( States.empty.toString()) 
//            && !selectedGroep.getState().toString().equals( States.written) 
//            && !selectedGroep.getState().toString().equals(States.pending.toString())){
//        return "Motivatie goedgekeurd";
//    }
//    if(selectedGroep.getState().toString().equals(States.pending.toString())){
//        return "Motivatie nog niet gekeurd";
//    }else{
//        return "Motivatie afgekeurd";
//    }
//    
//    }
    }
}

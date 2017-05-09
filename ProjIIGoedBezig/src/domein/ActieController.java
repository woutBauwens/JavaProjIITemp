/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import persistentie.ConnectionReceiver;
import repository.GenericDao;

/**
 *
 * @author kenne
 */
public class ActieController {

    private Groep selectedGroep;
    boolean keurbaar;

    public ActieController() {
    }

    public void setSelectedGroep(Groep gr) {
        this.selectedGroep = gr;
    }

    public String toonDetailActie(String titelActie) {
        List<Activiteit> acties = selectedGroep.getActies();
      //  Activiteit actie = acties.stream().filter(a -> a.getTitel().equals(titelActie)).findFirst().get();
    //    return actie.toString();
       for (Activiteit a : acties) {
            if (a.getTitel().equals(titelActie)) {
                return a.toString();
            }
        }
       return null;
    }

    public void setFeedbackActie(String titelActie, String feedback) {
//        Activiteit a = selectedGroep.getActie(titelActie);
//        a.setFeedback(feedback);
        selectedGroep.actiesgekeurd(titelActie, feedback);
    }

    public void keurActie(boolean b, String titel) {

        Activiteit a = selectedGroep.getActie(titel);
        a.setGoedgekeurd(b);
    }

    public String getActieHistoriek() {
        List<Activiteit> acties = selectedGroep.getActies();
        StringBuilder historiek = new StringBuilder();
      //  acties.stream().filter(a -> a.getFeedback() != null).forEach(a -> historiek.append(a.toString()).append("\n"));
        //als actie feedback heeft en dus gekeurd is tostring aan historiek toevoegen

        for (Activiteit a : acties) {
            if (a.getFeedback() != null) {
                historiek.append(a.toString()).append("\n");
            }
        }
        return historiek.toString();
    }

    public List<String> getActieNamenLijst() {
        List<String> actienamen = new ArrayList<>();
        // selectedGroep.getActies().stream().filter(a -> a.getFeedback() == null).forEach(a -> actienamen.add(a.getTitel());
     List<Activiteit> acties = selectedGroep.getActies();
//     acties.stream().filter((a) -> (a.getFeedback()==null)).forEachOrdered((a) -> {
//         actienamen.add(a.getTitel());
//        });
//stream werkt niet dus laat for nog staan tot we streams werkend krijgen...
     for(Activiteit a : acties){
         if(a.getFeedback()==null){
             actienamen.add(a.getTitel());
         }
     }
        return actienamen;
    }

    public List<Activiteit> getActies() {
        return selectedGroep.getActies();
    }

    public boolean Actiekeurbaar(String actie) {

//        selectedGroep.getActies().stream().filter(a -> a.getTitel().equals(actie))
//                .forEach(a -> {
//                    keurbaar = a.getFeedback() != null;
//                });
        Activiteit activiteit = selectedGroep.getActie(actie);
        return activiteit.getFeedback()!=null;
      //  return keurbaar;
    }
}

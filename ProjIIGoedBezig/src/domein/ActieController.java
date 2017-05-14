/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kenne
 */
public class ActieController {

    private Groep selectedGroep;
    boolean keurbaar;

    public Groep getSelectedGroep() {
        return selectedGroep;
    }

    public ActieController() {
    }

    public void setSelectedGroep(Groep gr) {
        this.selectedGroep = gr;
    }

    public String toonDetailActie(String titelActie) {

        //   return selectedGroep.getActie(titelActie).toString();
        return selectedGroep.getActie(titelActie).getActieDetail();
    }
//
//    public void setFeedbackActie(String titelActie, String feedback) {
//        selectedGroep.actiesgekeurd(titelActie, feedback);
//    }
//
//    public void keurActie(boolean b, String titel) {
//
//        Activiteit a = selectedGroep.getActie(titel);
//        a.setGoedgekeurd(b);
//    }

    public String getActieHistoriek() {
        StringBuilder historiek = new StringBuilder();
        if(selectedGroep.getActieplanFeedback()!=null)
            historiek.append("Actieplan feedback:").append("\n").append(selectedGroep.getActieplanFeedback()).append("\n");
        selectedGroep.getActies().stream().filter(a -> a.isGekeurd()).forEach(a -> historiek.append(a.toString()).append("\n"));
        return historiek.toString();
    }

    public List<String> getActieNamenLijst() {
        List<String> actienamen = new ArrayList<>();
        selectedGroep.getActies().stream().filter(a -> {
            return !a.isGekeurd();
        }).forEach(a -> actienamen.add(a.getTitel()));
        return actienamen;
    }

    public List<Activiteit> getActies() {
        return selectedGroep.getActies();
    }

    public boolean Actiekeurbaar(String actie) {
        return !selectedGroep.getActie(actie).isGekeurd();
    //    Activiteit activiteit = selectedGroep.getActie(actie);
    //    return activiteit.getFeedback() != null;
    }

    public void keurActie(boolean b, String titel, String actiefeedback) {

        selectedGroep.actiesgekeurd(b, titel, actiefeedback);
    }

    public boolean actiesgekeurd() {
        return selectedGroep.getCurrentState().getCurrentState(selectedGroep).actiesgekeurd();
    }

    public String getGekeurdeActiesHistoriek() {
        String historiek = "";
        for(Activiteit act: selectedGroep.getActies().stream().filter(a -> a.isGekeurd()).collect(Collectors.toList())){
            historiek = act.toString() + "\n" + historiek;
        }
        return historiek;
    }
}

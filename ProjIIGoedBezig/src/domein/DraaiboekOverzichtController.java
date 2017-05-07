/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author kenne
 */
public class DraaiboekOverzichtController {

    private Groep selectedGroep;

    public DraaiboekOverzichtController(Groep gr) {
        selectedGroep = gr;
    }

    public Groep getSelectedGroep() {
        return selectedGroep;
    }

    public List<String> getActies() {
        List<Activiteit> acties = selectedGroep.getActies();
        List<String> actieTitels = new ArrayList<>();
        for (Activiteit a : acties) {
            actieTitels.add(a.getTitel());
        }
        return actieTitels;
    }

    public Map<Integer, List<String>> getTaken(String actieTitel) {
        Activiteit actie = selectedGroep.getActie(actieTitel);
        List<Taak> taken = actie.getTaken();
        Map<Integer, List<String>> takenMap = new TreeMap<>();
        for (Taak t : taken) {
            List<String> taakValues = new ArrayList<>();
            taakValues.add(t.getWie().getEmail());
            taakValues.add(t.getWat());
            taakValues.add(t.getWanneer().toString());
            taakValues.add(t.getRealisatie());
            taakValues.add(t.getGroepBijsturing());
            taakValues.add(t.getLectorBijsturing());
            takenMap.put(t.getTaakId(), taakValues);
        }
        return takenMap;

    }

    public String getWie(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getWat(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getWanneer(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getRealisatie(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getGroepBijsturing(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getLectorBijsturing(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

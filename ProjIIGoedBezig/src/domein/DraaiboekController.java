/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import repository.GenericDao;
import repository.GenericDaoJpa;

/**
 *
 * @author kenne
 */
public class DraaiboekController {

    private Groep selectedGroep;
    private GenericDao taakRepo;

    public DraaiboekController(Groep gr, GenericDao<Taak> groeprepo) {
        selectedGroep = gr;
        taakRepo = groeprepo;
    }

    public Groep getSelectedGroep() {
        return selectedGroep;
    }

    public List<String> getActies() {
        List<Activiteit> acties = selectedGroep.getActies();
        List<String> actieTitels = new ArrayList<>();
        for (Activiteit a : acties) {
            if(a.getGoedgekeurd())
            actieTitels.add(a.getTitel());
        }
        return actieTitels;
    }

//    public Map<Integer, List<String>> getTaken(String actieTitel) {
//        Activiteit actie = selectedGroep.getActie(actieTitel);
//        List<Taak> taken = actie.getTaken();
//        Map<Integer, List<String>> takenMap = new TreeMap<>();
//        for (Taak t : taken) {
//            List<String> taakValues = new ArrayList<>();
//            taakValues.add(t.getWie().getEmail());
////            taakValues.add("AAAAAH");
//            taakValues.add(t.getWat());
//            taakValues.add(t.getWanneer().toString());
//            taakValues.add(t.getRealisatie());
//            taakValues.add(t.getGroepBijsturing());
//            taakValues.add(t.getLectorBijsturing());
//            takenMap.put(t.getTaakId(), taakValues);
//        };
//        return takenMap;
//
//    }

    public List<Taak> geefTaken(String actie) {
       return selectedGroep.getActie(actie).getTaken();
    
    }

//    public String getWie(String s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public String getWat(String s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public String getWanneer(String s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public String getRealisatie(String s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public String getGroepBijsturing(String s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public String getLectorBijsturing(String s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public void keurDraaiboek(boolean keuring, String feedback, String actie) {
        Activiteit activiteit = selectedGroep.getActie(actie);
        List<Taak> taken = activiteit.getTaken();
        for (Taak t : taken) {
            t.setIsGoedgekeurd(keuring);
            t.setLectorBijsturing(feedback);
            taakRepo.update(selectedGroep);
            taakRepo.persist(t);
        }
//        taakRepo.update(selectedGroep);
//        taakRepo.persist(taken);
    }

    public void setFeedbackTaak(Taak taak, String text) {
        taak.setLectorBijsturing(text);
        taakRepo.update(taak);
        taakRepo.persist(taak);
    
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import repository.GenericDao;

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
            if (a.getGoedgekeurd()) {
                actieTitels.add(a.getTitel());
            }
        }
        return actieTitels;
    }

    public List<Taak> geefTaken(String actie) {
        return selectedGroep.getActie(actie).getTaken();

    }

    public void keurDraaiboek(boolean keuring, String feedback, String actie) {
        Activiteit activiteit = selectedGroep.getActie(actie);
        List<Taak> taken = activiteit.getTaken();
        for (Taak t : taken) {
            t.setIsGoedgekeurd(keuring);
            t.setLectorBijsturing(feedback);
            taakRepo.update(selectedGroep);
            taakRepo.persist(t);
        }
    }

    public void setFeedbackTaak(Taak taak, String text) {
        taak.setLectorBijsturing(text);
        taakRepo.update(taak);
        taakRepo.persist(taak);

    }

}

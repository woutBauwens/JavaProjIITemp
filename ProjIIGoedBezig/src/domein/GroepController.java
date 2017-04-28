/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;
import repository.GroepDaoJpa;

/**
 *
 * @author Jonas
 */
public class GroepController {

    private ContactPersoon lector;
    private Groep selectedGroep;
    private GroepDaoJpa groepRepo;

    public GroepController(GroepDaoJpa jp, ContactPersoon lector) {
        groepRepo = jp;
        this.lector = lector;
    }

    public List<Groep> getGroepenByLector() {
        return lector.getGroepen();
    }

    public void setFeedback(String response) {
        selectedGroep.getHuidigeMotivatie().setFeedback(response);

    }

    public void keur(boolean keuring) {
        selectedGroep.setKeuring(keuring);

    }

    public String toonMotivatie() {

        return selectedGroep.getHuidigeMotivatie().getTekst();

    }

    public void setSelectedGroep(String naam) {

        List<Groep> groepen = lector.getGroepen();
        for (Groep g : groepen) {
            if (g.getNaam().equals(naam)) {
                selectedGroep = g;
            }

        }

    }

    public Groep getSelectedGroep() {
        return selectedGroep;
    }

    public void update() {
        groepRepo.update(selectedGroep);
    }
}

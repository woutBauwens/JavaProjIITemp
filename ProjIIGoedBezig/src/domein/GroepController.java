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

    public List<Groep> getGroepenByLector() {
        return lector.getGroepen();
    }

    public void setFeedback(String response) {
        selectedGroep.getHuidigeMotivatie().setFeedback(response);
        groepRepo.update(selectedGroep);
    }

    public String toonMotivatie(Groep g) {

        return g.getHuidigeMotivatie().getTekst();

    }

    public Groep getSelectedGroep() {
        return selectedGroep;
    }
}

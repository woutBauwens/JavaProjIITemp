/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.SQLConnection;
import java.util.List;
import java.util.function.Supplier;
import repository.GenericDao;
import repository.GenericDaoJpa;
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
        if(selectedGroep.isGoedgekeurd()){
            Motivatie goedgekeurd = selectedGroep.getMotivaties().get(selectedGroep.getMotivaties().size()-1);
            return String.format("De motivatie is goedgekeurd.%n%n%s%nFeedback:%n%s", goedgekeurd.getTekst(), goedgekeurd.getFeedback()); 
        }
        if (selectedGroep.getHuidigeMotivatie().isVerstuurd()) {
        return selectedGroep.getHuidigeMotivatie().getTekst();
        } else {
            try {
                return selectedGroep.getMotivaties().stream().findFirst().filter(m -> m.isVerstuurd()).orElseThrow(NullPointerException::new).getTekst();
            } catch (NullPointerException nullex) {
                return "Nog geen motivatie verstuurd";
            }
        }
    }

    public boolean isMotivatieVerstuurd() {
        return selectedGroep.getHuidigeMotivatie().isVerstuurd();
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
//        if (selectedGroep == null) {
//            return lector.getGroepen().get(0);
//        }
        return selectedGroep;
    }

    public void update() {
        groepRepo.update(selectedGroep);
        GenericDao entity = new GenericDaoJpa(Groep.class);
        entity.persist(selectedGroep);
    }

    public String toonDetailActie(String titelActie) {
        List<Activiteit> acties = selectedGroep.getActies();
        for (Activiteit a : acties) {
            if (a.getTitel().equals(titelActie)) {
                return a.toString();
            }
        }
        return null;
    }

    public void setFeedbackActie(String titelActie, String feedback) {
        Activiteit a = getActie(titelActie);
        a.setFeedback(feedback);

    }

    public void keurActie(boolean b, String titel) {
        Activiteit a = getActie(titel);
        a.setGoedgekeurd(b);
    }

    private Activiteit getActie(String titel) {

        List<Activiteit> acties = getSelectedGroep().getActies();
        for (Activiteit a : acties) {
            if (a.getTitel().equals(titel)) {

                return a;
            }
        }
        return null;
    }

    public String getActieHistoriek() {
        List<Activiteit> acties = selectedGroep.getActies();
        StringBuilder historiek = new StringBuilder();
        for (Activiteit a : acties) {
            if (a.getFeedback() != null) {
                historiek.append(a.toString()).append("\n");
            }
        }
        return historiek.toString();
    }
}

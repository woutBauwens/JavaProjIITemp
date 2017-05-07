/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import persistentie.ConnectionReceiver;
import persistentie.SQLConnection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Application;
import repository.GenericDao;
import repository.GenericDaoJpa;

/**
 *
 * @author Jonas
 */
public class GroepController {

    private ContactPersoon lector;
    private Groep selectedGroep;
    private GenericDao groepRepo;

    public GroepController(GenericDao jp, ContactPersoon lector) {
        try {
            groepRepo = jp;
            this.lector = lector;
            ConnectionReceiver receiver = new ConnectionReceiver(this);
            ExecutorService pool = Executors.newFixedThreadPool(1);
            pool.execute(receiver);
        } catch (Exception ex) {
            Logger.getLogger(GroepController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ContactPersoon getLector() {
        return lector;
    }

    public void setLector(ContactPersoon lector) {
        if (this.lector != null && !this.lector.equals(lector)) {
            this.lector = lector;
            System.out.println("Er zijn nieuwe meldingen.");
        }
    }

    public void setGroep(Groep g) {
        if (selectedGroep != null) {
            lector.getGroepen().remove(lector.getGroepen().indexOf(selectedGroep));
            lector.getGroepen().add(g);
        }
    }

    public void setObject(Object o) {
        if (o instanceof Lector) {
            lector = (Lector) o;
        } else if (o instanceof Groep) {
            selectedGroep = (Groep) o;
        }
    }

    public void updateLector() {
        lector = SQLConnection.refreshLector(lector);
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
        if (selectedGroep.isGoedgekeurd()) {
            Motivatie goedgekeurd = selectedGroep.getMotivaties().get(selectedGroep.getMotivaties().size() - 1);
            return String.format("De motivatie is goedgekeurd.%n%n%s", goedgekeurd.toString());
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
       /* try {
            List<Groep> groepen = lector.getGroepen();
            // werkt ook niet, waarschijnlijk ook JPA. 
            selectedGroep = groepen.stream().filter(g -> g.getNaam().equals(naam)).findFirst().orElseGet(() -> {
                for (Groep g : groepen) {
                    if (g.getNaam().equals(naam)) {
                        return g;
                    }
                }
                return null;
            });
        } catch (Exception e) {*/
            // lukt niet op andere manier
            if (selectedGroep == null) {
                for (Groep g : lector.getGroepen()) {
                    if (g.getNaam().equals(naam)) {
                        selectedGroep = g;
                    }
                }
            }
   //     }
        selectedGroep.initializeState();
//als findfirst geen resultaat levert => null

//        for (Groep g : groepen) {
//            if (g.getNaam().equals(naam)) {
//                selectedGroep = g;
//            }
//
//        }
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
        Activiteit actie = acties.stream().filter(a -> a.getTitel().equals(titelActie)).findFirst().orElse(null);
        return actie.toString();
//        for (Activiteit a : acties) {
//            if (a.getTitel().equals(titelActie)) {
//                return a.toString();
//            }
//        }
//        return null;
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
        return acties.stream().filter(a -> a.getTitel().equals(titel)).findFirst().orElse(null);

//        for (Activiteit a : acties) {
//            if (a.getTitel().equals(titel)) {
//
//                return a;
//            }
//        }
//        return null;
    }

    public String getActieHistoriek() {
        List<Activiteit> acties = selectedGroep.getActies();
        StringBuilder historiek = new StringBuilder();
        acties.stream().filter(a -> a.getFeedback() != null).forEach(a -> historiek.append(a.toString()).append("\n"));
        //als actie feedback heeft en dus gekeurd is tostring aan historiek toevoegen

//        for (Activiteit a : acties) {
//            if (a.getFeedback() != null) {
//                historiek.append(a.toString()).append("\n");
//            }
//        }
        return historiek.toString();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import persistentie.ConnectionReceiver;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.CursistDaoJpa;
import repository.GenericDao;
import states.States;

/**
 *
 * @author Jonas
 */
public class GroepController {

    private ContactPersoon lector;
    private Groep selectedGroep;
    private GenericDao groepRepo;
    private CursistDaoJpa ledenRepo;
    private ActieController AC;
    private MotivatieController MC;

    public GroepController(GenericDao groepRepo, CursistDaoJpa ledenRepo, ContactPersoon lector) {
        try {
            AC = new ActieController();
            MC = new MotivatieController();

            this.groepRepo = groepRepo;
            this.ledenRepo = ledenRepo;
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

//    public void setGroep(Groep g) {
//        if (selectedGroep != null) {
//            lector.getGroepen().remove(lector.getGroepen().indexOf(selectedGroep));
//            lector.getGroepen().add(g);
//        }
//    }
    public void setObject(Object o) {
        if (o instanceof Lector) {
            lector = (Lector) o;
        } else if (o instanceof Groep) {
            selectedGroep = (Groep) o;
        }
    }

//    public void updateLector() {
//        lector = SQLConnection.refreshLector(lector);
//    }
    public List<Groep> getGroepenByLector() {
        return lector.getGroepen();
    }

//    public void setMotivatieFeedback(String response) {
//        MC.setFeedback(response);
//        // selectedGroep.getHuidigeMotivatie().setFeedback(response);
//    }
//
//    public void keurMotivatie(boolean keuring) {
//        MC.setKeuring(keuring);
//        //selectedGroep.setKeuring(keuring);
//    }
//
//    public String toonMotivatie() {
//        return MC.toonMotivatie();
////        if (selectedGroep.isGoedgekeurd()) {
////            Motivatie goedgekeurd = selectedGroep.getMotivaties().get(selectedGroep.getMotivaties().size() - 1);
////            return String.format("De motivatie is goedgekeurd.%n%n%s", goedgekeurd.toString());
////        }
////        if (selectedGroep.getHuidigeMotivatie().isVerstuurd()) {
////            return selectedGroep.getHuidigeMotivatie().getTekst();
////        } else {
////            try {
////                return selectedGroep.getMotivaties().stream().findFirst().filter(m -> m.isVerstuurd()).orElseThrow(NullPointerException::new).getTekst();
////            } catch (NullPointerException nullex) {
////                return "Nog geen motivatie verstuurd";
////            }
////        }
//    }
//
//    public boolean isMotivatieVerstuurd() {
//        return MC.isMotivatieVerstuurd();
//        // return selectedGroep.getHuidigeMotivatie().isVerstuurd();
//    }
    public void setSelectedGroep(String naam) {

        List<Groep> groepen = lector.getGroepen();
        selectedGroep = groepen.stream().filter(g -> g.getNaam().equals(naam)).findFirst().get();

//        selectedGroep.initializeState();
//als findfirst geen resultaat levert => null
   /*     for (Groep g : groepen) {
            if (g.getNaam().equals(naam)) {
                g.toState(g.getState());
                selectedGroep = g;
            }

        } */
    }

    public Groep getSelectedGroep() {
//        if (selectedGroep == null) {
//            return lector.getGroepen().get(0);
//        }
        return selectedGroep;
    }

    public void update() {
        groepRepo.update(selectedGroep);
        //     GenericDao entity = new GenericDaoJpa(Groep.class);
        groepRepo.persist(selectedGroep);
    }

//    public String toonDetailActie(String titelActie) {
//        return AC.toonDetailActie(titelActie);
////        List<Activiteit> acties = selectedGroep.getActies();
////        Activiteit actie = acties.stream().filter(a -> a.getTitel().equals(titelActie)).findFirst().orElse(null);
////        return actie.toString();
////        for (Activiteit a : acties) {
////            if (a.getTitel().equals(titelActie)) {
////                return a.toString();
////            }
////        }
////        return null;
//    }
//    public void setFeedbackActie(String titelActie, String feedback) {
//        AC.setFeedbackActie(titelActie, feedback);
////        Activiteit a = getActie(titelActie);
////        a.setFeedback(feedback);
//
//    }
//
//    public void keurActie(boolean b, String titel) {
//        AC.keurActie(b, titel);
////        Activiteit a = getActie(titel);
////        a.setGoedgekeurd(b);
//    }
//    private Activiteit getActie(String titel) {
//
//        List<Activiteit> acties = getSelectedGroep().getActies();
//        return acties.stream().filter(a -> a.getTitel().equals(titel)).findFirst().orElse(null);
//
////        for (Activiteit a : acties) {
////            if (a.getTitel().equals(titel)) {
////
////                return a;
////            }
////        }
////        return null;
//    }
//    public String getActieHistoriek() {
//        return AC.getActieHistoriek();
////        List<Activiteit> acties = selectedGroep.getActies();
////        StringBuilder historiek = new StringBuilder();
////        acties.stream().filter(a -> a.getFeedback() != null).forEach(a -> historiek.append(a.toString()).append("\n"));
////        //als actie feedback heeft en dus gekeurd is tostring aan historiek toevoegen
////
//////        for (Activiteit a : acties) {
//////            if (a.getFeedback() != null) {
//////                historiek.append(a.toString()).append("\n");
//////            }
//////        }
////        return historiek.toString();
//    }
    public String getGroepState() {
        return selectedGroep.getState();
    }

    public boolean actiesToegankelijk() {
        return selectedGroep.actiesToegankelijk();
    }

    public boolean MotivatieKeurbaar() {

        return selectedGroep.MotivatieKeurbaar();

    }

    public String toonLeden() {
        StringBuilder ledenlijst = new StringBuilder();
        List<Cursist> cursistenlijst = ledenRepo.getCursistenByGroep(selectedGroep.getId());
        cursistenlijst.forEach((c) -> {
            //            String email = c.getEmail();
//            String voornaam = email.substring(0, email.indexOf('.'));
//            String achternaam = email.substring(voornaam.length() + 1,email.indexOf((".", email.indexOf(".") + 1)));

//            ledenlijst.append(String.format("%s %s,%n", voornaam, achternaam));
ledenlijst.append(c.getEmail()).append("\n");
        });
        return ledenlijst.toString();
    }

    public boolean draaiboekBeschikbaar() {
       return !selectedGroep.getState().equals(States.actiegoedgekeurd.toString());
    }
}

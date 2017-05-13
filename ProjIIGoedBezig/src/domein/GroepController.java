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
import java.util.stream.Collectors;
import repository.CursistDao;
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
    private CursistDao ledenRepo;
    private ActieController AC;
    private MotivatieController MC;

    public void setGroep(Groep g) {
        selectedGroep = g;
    }

    public GroepController(GenericDao groepRepo, CursistDao ledenRepo, ContactPersoon lector) {
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

    public List<Groep> getGroepenByLector() {
        return lector.getGroepen();
    }

    public void setSelectedGroep(String naam) {
        try {
            List<Groep> groepen = lector.getGroepen();
            selectedGroep = groepen.stream().filter(g -> g.getNaam().equals(naam)).findFirst().get();
            // selectedGroep.toState(States.valueOf(selectedGroep.getState()));
        } catch (Exception e) {

        }

    }

    public Groep getSelectedGroep() {
        return selectedGroep;
    }

    public void update() {
        groepRepo.update(selectedGroep);
        groepRepo.persist(selectedGroep);
    }

//    public String getGroepState() {
//        return selectedGroep.getState();
//    }
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
            ledenlijst.append(c.getEmail()).append("\n");
        });
        return ledenlijst.toString();
    }

    public boolean draaiboekBeschikbaar() {
        return selectedGroep.draaiboekBeschikbaar();
        // return !selectedGroep.getState().equals(States.actiegoedgekeurd.toString());
    }

    public List<String> getGroepsNamen() {
        List<Groep> groepen = lector.getGroepen();
        return groepen.stream().map(Groep::getNaam).collect(Collectors.toList());
    }

    public void keurActieplan(boolean b, String feedback) {
        selectedGroep.keurActieplan(b, feedback);
    }

    public String getActieplanFeedback() {
        return selectedGroep.getActieplanFeedback();
    }

    public boolean actieplanReedsGekeurd() {
        return selectedGroep.actieplanReedsGekeurd();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

//import domein.gbGroepStatePattern.GroepStateFactory;
//import domein.gbGroepStatePattern.State;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import states.GroepStateFactory;
import states.State;
import states.States;

/**
 *
 * @author Jonas
 */
@Entity
public class Groep implements Serializable {

    @Id
    private int GBGroepId;
    @OneToMany(mappedBy = "GBGroepId", cascade = CascadeType.REFRESH)
    private List<Motivatie> motivaties;
    @OneToMany(mappedBy = "GBGroepId", cascade = CascadeType.REFRESH)
    private List<Activiteit> acties;

    private boolean MotivatieIsGoedgekeurd;
    private String naam;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "CurrentState")
    private State currentState;

    @ManyToOne
    @JoinColumn(name = "HoofdLectorContactPersoonId")
    private ContactPersoon HoofdLectorContactPersoonId;

    @Column(name = "ActieplanFeedback")
    private String actieplanFeedback;

    public Groep(State state) {
        currentState = state;
        motivaties = new ArrayList<>();
    }

    protected Groep() {

    }


    public boolean isGoedgekeurd() {
        return MotivatieIsGoedgekeurd;
    }

    public boolean isVerstuurd() {
        return motivaties.get(0).isVerstuurd();
    }

    public String getNaam() {
        return naam;
    }

    public Motivatie getHuidigeMotivatie() {
        return motivaties.get(0);
    }

    public List<Activiteit> getActies() {
        return acties.subList(0, acties.size());
    }

    public void addMotivatie(Motivatie m) {
        motivaties.add(m);
    }

    public void setKeuring(boolean keuring) {

        currentState.getCurrentState(this).setMotivatieKeuring(keuring);
    }

    public State getCurrentState() {
        return currentState;
    }

    public List<Motivatie> getMotivaties() {
        return motivaties.subList(0, motivaties.size());
    }

    public String getState() {
        return currentState.toString();

    }

    public void setFeedback(String response) {
        getHuidigeMotivatie().setFeedback(response);
    }

    public boolean isMotivatieVerstuurd() {
        return getHuidigeMotivatie().isVerstuurd();
    }

    public Activiteit getActie(String titelActie) {

        for (Activiteit a : acties) {
            if (a.getTitel().equals(titelActie)) {
                return a;

            }
        }
        return null;
    }

    public int getId() {
        return GBGroepId;
    }

    public boolean actiesToegankelijk() {
        return currentState.getCurrentState(this).actiesToegankelijk();
    }

    public String toonMotivatie() {
        return currentState.getCurrentState(this).toonMotivatie();
    }

    public String geefMotivatieStatus() {
        return currentState.getCurrentState(this).geefMotivatieStatus();
    }

    public boolean MotivatieKeurbaar() {
        return getState().equals(States.pending.toString());
    }

    private void toState(States state) {
        GroepStateFactory stateFactory = new GroepStateFactory(this);
        currentState = new State(state, stateFactory.createState(state));
    }

    public boolean draaiboekBeschikbaar() {
        return currentState.getCurrentState(this).draaiboekBeschikbaar();
    }

    public void actiesgekeurd(boolean b, String titel, String actiefeedback) {
        currentState.getCurrentState(this).actiesgekeurd(b, titel, actiefeedback);

    }

    public void keurActieplan(boolean b, String actieplanFeedback) {
        currentState.getCurrentState(this).keurActiePlan(b, actieplanFeedback);
    }

    public String getActieplanFeedback() {
        return actieplanFeedback;
    }

    public void setActieplanFeedback(String actieplanFeedback) {

        this.actieplanFeedback = actieplanFeedback;
    }

    public boolean actiesGekeurd() {
        return currentState.getCurrentState(this).actiesgekeurd();
    }

    public void keurActiePlan(boolean b) {

        if (b) {
            toState(States.actiegoedgekeurd);
        } else {
            toState(States.approved);
        }
    }

    public void verwerkActieKeuring(boolean b, String titel, String feedback) {
        Activiteit actie = getActie(titel);
        actie.setGoedgekeurd(b);
        actie.setFeedback(feedback);

    }

    public boolean actieplanReedsGekeurd() {
        return currentState.getCurrentState(this).actieplanReedsGekeurd();
    }

    public void setMotivatieKeuring(boolean keuring) {
        MotivatieIsGoedgekeurd = keuring;
        if (keuring) {
            toState(States.approved);
        } else {
            toState(States.written);
        }
    }

}

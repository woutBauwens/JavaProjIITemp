/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

//import domein.gbGroepStatePattern.GroepStateFactory;
//import domein.gbGroepStatePattern.State;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import states.GroepState;
import states.GroepStateFactory;
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

    @Column(name = "CurrentState")
    private String currentState;

    @ManyToOne
    @JoinColumn(name = "HoofdLectorContactPersoonId")
    private ContactPersoon HoofdLectorContactPersoonId;

//    @Transient
//    private State state;
    @Transient
    private GroepState groepState;

    protected Groep() {
    }

//    public void initializeState() {
//        GroepStateFactory gsf = new GroepStateFactory();
//        state = gsf.createPlayerFactory(currentState);
//    }
    @Override
    public String toString() {
        return String.format("%s\n%s\n%s", naam, motivaties.get(0).getTekst(), acties.isEmpty() ? "Geen acties" : acties.get(0).getId());
    }

    public boolean isGoedgekeurd() {
        return MotivatieIsGoedgekeurd;
    }

    public boolean isVerstuurd() {
        return motivaties.get(0).isVerstuurd();
    }

    public void keur(String feedback, boolean b) {
        motivaties.get(0).setFeedback(feedback);
    }

    public String getNaam() {
        return naam;
    }

    public Motivatie getHuidigeMotivatie() {
        return motivaties.get(0);
    }

    public List<Activiteit> getActies() {
        return acties;
    }

    public void addMotivatie(Motivatie m) {
        motivaties.add(m);
    }

    void setKeuring(boolean keuring) {
        MotivatieIsGoedgekeurd = keuring;
        groepState.verwerkMotivatieKeuring(keuring);
        if (!keuring) {
            toState("written");
        } else {
            toState("approved");
        }
    }

    public List<Motivatie> getMotivaties() {
        return motivaties;
    }

    public String getState() {
        //  groepState = states.GroepStateFactory.createState(currentState, this);
        return currentState;

    }

    void setFeedback(String response) {
        getHuidigeMotivatie().setFeedback(response);
    }

    boolean isMotivatieVerstuurd() {
        return getHuidigeMotivatie().isVerstuurd();
    }

    Activiteit getActie(String titelActie) {

        for (Activiteit a : acties) {
            if (a.getTitel().equals(titelActie)) {
                return a;

            }
        }
        //    return acties.stream().filter(a -> a.getTitel().equals(titelActie)).findFirst().get();
        return null;
    }

    public boolean actiesToegankelijk() {
        return groepState.actiesToegankelijk();
    }

    public String toonMotivatie() {
        return groepState.toonMotivatie();
    }

    public String geefMotivatieStatus() {
        return groepState.geefMotivatieStatus();
    }

    public boolean MotivatieKeurbaar() {
        return getState().toString().equals(States.pending.toString());
    }

    public void toState(String state) {
        groepState = GroepStateFactory.createState(state, this);
        currentState = state;
    }

    public void actiesgekeurd(String titelActie, String feedback) {
        groepState.actiesgekeurd(titelActie, feedback);
    }

    public void setFeedbackActie(String titel, String feedback) {
        Activiteit a = getActie(titel);
        a.setFeedback(feedback);
    }

}

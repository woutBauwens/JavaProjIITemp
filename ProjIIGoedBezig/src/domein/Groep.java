/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import domein.gbGroepStatePattern.GroepStateFactory;
import domein.gbGroepStatePattern.State;
import persistentie.SQLConnection;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.transformation.SortedList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import jdk.nashorn.internal.ir.annotations.Ignore;

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

    @Transient
    private State state;

    protected Groep() {
    }

    public void initializeState(){
        GroepStateFactory gsf = new GroepStateFactory();
        state = gsf.createPlayerFactory(currentState);
    }
    
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
        return acties.subList(0,acties.size());
    }

    public void addMotivatie(Motivatie m) {
        motivaties.add(m);
    }

    void setKeuring(boolean keuring) {
        MotivatieIsGoedgekeurd = keuring;
        if (!keuring) {
            currentState = "empty";
        }
    }

    public List<Motivatie> getMotivaties() {
        return motivaties.subList(0,motivaties.size());
    }
}

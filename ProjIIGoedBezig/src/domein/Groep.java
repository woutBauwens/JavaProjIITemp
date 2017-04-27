/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.SQLConnection;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.transformation.SortedList;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author Jonas
 */
@Entity
public class Groep implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "ContactPersoonId")
    private ContactPersoon GBGroepId;
    @OneToMany
    private List<Motivatie> motivaties;
    @OneToMany(mappedBy = "GBGroepId")
    private List<Activiteit> acties;

    private boolean MotivatieIsGoedgekeurd;
    private String naam;

    private int HoofdLectorContactPersoonId;

    protected Groep() {
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
    
    public void keur(String feedback, boolean b){
        motivaties.get(0).setFeedback(feedback);
        MotivatieIsGoedgekeurd = b;
    
    }
    
    public String getNaam(){
        return naam;
    }
    
    public Motivatie getHuidigeMotivatie(){
        if(isVerstuurd())
            return motivaties.get(0);
        return null; 
    }

    public List<Activiteit> getActies() {
        return acties;
    }
    
    public void addMotivatie(Motivatie m){
        motivaties.add(m);
    }
    
    
}

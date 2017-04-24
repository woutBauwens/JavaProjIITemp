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
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author Jonas
 */
@Entity
public class Groep implements Serializable {

    @Id
    private int GBGroepId;
    @OneToMany(mappedBy = "GBGroepId")
    private List<Motivatie> motivaties;
    @OneToMany(mappedBy = "GBGroepId")
    private List<Activiteit> acties;

    private String naam;

    private int HoofdLectorContactPersoonId;

    @Transient
    private EntityManager em;

    protected Groep() {
        em = SQLConnection.getManager();
    }

    public Groep(int id) {
        GBGroepId = id;
        em.createQuery("SELECT o, m, a FROM Groep o JOIN Motivatie m ON o.GBGroepId = m.GBGroepId JOIN Activiteit a ON a.GBGroepId = o.GBGroepId WHERE o.GBGroepId = :id;", Groep.class).setParameter("id", id).getSingleResult();
        motivaties = new ArrayList<>();
        motivaties.add(new Motivatie(id));
        acties = new ArrayList<>();
        acties.add(new Activiteit(id));
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n%s", naam, motivaties.get(0).getTekst(),acties.isEmpty()? "Geen acties": acties.get(0).getId());
    }
}

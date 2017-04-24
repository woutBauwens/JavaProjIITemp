/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.SQLConnection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 *
 * @author Jonas
 */
@Entity
@Table(name = "Motivatie")
public class Motivatie {

    @Id
    private int MotivatieId;
    private String MotivatieTekst;
    private String Feedback;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GBGroepId")
    private Groep GBGroepId;

    @Transient
    private EntityManager em;

    private Motivatie() {
        em = SQLConnection.getManager();
    }

    public Motivatie(int id) {
      //  GBGroepId = id;
        em.createQuery("SELECT o FROM Motivatie o WHERE o.MotivatieId = :id", Motivatie.class).setParameter("id", MotivatieId).getSingleResult();
    }

    public String getTekst() {
        return MotivatieTekst;
    }

    public void setTekst(String tekst) {
        MotivatieTekst = tekst;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

}

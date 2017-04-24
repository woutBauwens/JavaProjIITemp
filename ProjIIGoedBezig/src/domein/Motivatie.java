/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.SQLConnection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private String tekst;
    private String feedback;

    @Ignore
        private EntityManager em;

    
    private Motivatie(){
        em = SQLConnection.getManager();
    }
    
    public Motivatie(int id){
         em.createQuery("SELECT o FROM Motivatie o WHERE o.MotivatieId = :id", Motivatie.class).setParameter("id", MotivatieId).getSingleResult();
    }
    

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.SQLConnection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Jonas
 */
@Entity
@Table(name = "Motivatie")
public class Motivatie {

    private String Feedback;
    @Temporal(TemporalType.DATE)
    private Date Date;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MotivatieId;
    private String MotivatieTekst;
    private String NaamOrganisatie;
    private String AdresOrganisatie;
    private boolean isVerstuurd;
    private String EmailOrganisatie;
    private String WebsiteUrlOrganisatie;
    private String NaamContactPersoon;
    private String VoornaamContactPersoon;
    private String EmailContactPersoon;
    private String TitelContactPersoon;
    private int GBGroepId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name = "GBGroepId")
//    private Groep GBGroep;
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

    public boolean isVerstuurd() {
        return isVerstuurd;
    }

    public int getMotivatieId() {
        return MotivatieId;
    }

  

}

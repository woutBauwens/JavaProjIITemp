/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jonas
 */
@Entity
@Table(name = "Motivatie")
public class Motivatie implements Serializable {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GBGroepId")
    private Groep GBGroepId;
    

    private Motivatie() {
    }
    public Motivatie(String organisatie, String tekst){
        NaamOrganisatie=organisatie;
        MotivatieTekst = tekst;
    }

    public String getTekst() {
        return MotivatieTekst;
    }
    
    public String getNaamOrganisatie(){
        return NaamOrganisatie;
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

  @Override
  public String toString(){
      return String.format("%s%n%s%nMotivatie:%n%s%n%n%s%n-------------------------------------------------------------%n",NaamOrganisatie,DateFormat.getInstance().format(Date), MotivatieTekst, Feedback==null?"": "Feedback: \n" + Feedback);
  }

}

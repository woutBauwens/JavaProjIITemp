/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import javax.persistence.Column;
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
    @Column(name="Feedback")
    private String feedback;
    @Column(name="Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MotivatieId;")
    private int motivatieId;
    @Column(name = "MotivatieTekst")
    private String motivatieTekst;
    @Column(name = "NaamOrganisatie")
    private String naamOrganisatie;
    @Column(name = "AdresOrganisatie")
    private String adresOrganisatie;
    @Column(name = "IsVerstuurd")
    private boolean isVerstuurd;
    @Column(name = "EmailOrganisatie")
    private String emailOrganisatie;
    @Column(name = "WebsiteUrlOrganisatie")
    private String websiteUrlOrganisatie;
    @Column(name = "NaamContactPersoon")
    private String naamContactPersoon;
    @Column(name = "VoornaamContactPersoon")
    private String voornaamContactPersoon;
    @Column(name = "EmailContactPersoon")
    private String emailContactPersoon;
    @Column(name = "TitelContactPersoon")
    private String titelContactPersoon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GBGroepId")
    private Groep gBGroepId;

    private Motivatie() {
    }

    public Motivatie(String organisatie, String tekst) {
        naamOrganisatie = organisatie;
        motivatieTekst = tekst;
    }

    public String getTekst() {
        return motivatieTekst;
    }

    public String getNaamOrganisatie() {
        return naamOrganisatie;
    }

    public void setTekst(String tekst) {
        motivatieTekst = tekst;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean isVerstuurd() {
        return isVerstuurd;
    }

    public int getMotivatieId() {
        return motivatieId;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s%nMotivatie:%n%s%n%n%s%n-------------------------------------------------------------%n", naamOrganisatie, DateFormat.getInstance().format(date), motivatieTekst, feedback == null ? "" : "Feedback: \n" + feedback);
    }

}

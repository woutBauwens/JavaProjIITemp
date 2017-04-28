/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.SQLConnection;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Jonas
 */
@Entity
@Table(name = "Activiteit")
public class Activiteit implements Serializable {

    @Id
    private int ActiviteitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GBGroepId")
    private Groep GBGroepId;

    private String titel;
    private String omschrijving;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date UitvoeringsDate;

    private String feedback;
    private boolean isGoedgekeurd;

    public int getId() {
        return ActiviteitId;
    }

    public void setId(int id) {
        this.ActiviteitId = id;
    }

    protected Activiteit() {

    }

    public String getTitel() {
        return titel;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public Date getUitvoeringsdatum() {
        return UitvoeringsDate;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if (UitvoeringsDate != null) {
            b.append(String.format("Datum:%n%s%n", UitvoeringsDate));
        }
        
        b.append(String.format("Titel:%n%s%nOmschrijving:%n%s%n", titel, omschrijving));
        if (feedback!= null){
            b.append(String.format("Feedback:%n%s%n", feedback));
        }
        return b.toString();
    }

    public String getFeedback() {
        //  return feedback;
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setGoedgekeurd(boolean b) {
        isGoedgekeurd = b;
    }
    
    public boolean getGoedgekeurd(){
        return isGoedgekeurd;
    }

}

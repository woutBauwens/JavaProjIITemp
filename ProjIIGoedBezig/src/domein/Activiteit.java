/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @ManyToOne
    @JoinColumn(name = "GBGroepId")
    private Groep GBGroepId;

    @OneToMany(mappedBy = "activiteitId", cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ActiviteitId")
    private List<Taak> taken;

    private String titel;
    private String omschrijving;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date UitvoeringsDate;

    private String feedback;
    private boolean isGoedgekeurd;
    
   @Column(name="IsGekeurd")
    private boolean isGekeurd;

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
        b.append(String.format("%s%n", titel));
        if (UitvoeringsDate != null) {
            b.append(String.format("%s%n", DateFormat.getInstance().format(UitvoeringsDate)));
        }
        b.append(String.format("%nOmschrijving:%n%s%n", omschrijving));
    //    if (feedback != null) {
            b.append(String.format("%n%n%s%nFeedback:%n%s%n-------------------------------------------------------------%n", isGoedgekeurd ? "Goedgekeurd" : "Afgekeurd", feedback));
  //      }
        
        return b.toString();
    }
    
    public String getActieDetail(){
        return  String.format("%s%nOmschrijving:%n%s%n",titel,omschrijving);
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setGoedgekeurd(boolean b) {
        isGekeurd = true;
        isGoedgekeurd = b;
    }

    public boolean getGoedgekeurd() {
        return isGoedgekeurd;
    }

    public List<Taak> getTaken() {
        return taken;
    }

    public boolean isGekeurd() {
        return isGekeurd;
    }
}

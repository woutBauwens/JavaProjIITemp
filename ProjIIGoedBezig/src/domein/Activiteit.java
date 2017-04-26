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
    private Date uitvoeringsdatum;

    public int getId() {
        return ActiviteitId;
    }

    public void setId(int id) {
        this.ActiviteitId = id;
    }

    protected Activiteit() {

    }

    public Activiteit(int id) {
        // GBGroepId = id;
        SQLConnection.getManager().createQuery("SELECT a FROM dbo.Activiteit a WHERE a.GBGroepId = :id;", Activiteit.class).setParameter("id", id).getResultList();
    }

    public String getTitel() {
        return titel;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public Date getUitvoeringsdatum() {
        return uitvoeringsdatum;
    }

}

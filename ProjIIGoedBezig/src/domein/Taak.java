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
 * @author kenne
 */
@Entity
@Table(name = "Taak")
public class Taak implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaakId")
    private int taakId;
    @JoinColumn(name = "ActiviteitId")
    @ManyToOne
    private Activiteit activiteitId;
    @JoinColumn(name = "WieCursistId")
    @ManyToOne
    private Cursist wie;
    @Column(name = "OmschrijvingTaak")
    private String wat;
    @Column(name = "WijzigingsDate")
    @Temporal(TemporalType.DATE)
    private Date wanneer;
    @Column(name = "RealisatieNiveau")
    private String realisatie;
    @Column(name = "TeamBijsturing")
    private String groepBijsturing;
    @Column(name = "LectorBijsturing")
    private String lectorBijsturing;
    @Column(name = "IsGoedgekeurd")
    private boolean isGoedgekeurd;

    protected Taak() {

    }

    public int getTaakId() {
        return taakId;
    }

    public Activiteit getActiviteitd() {
        return activiteitId;
    }

    public Cursist getWie() {
        return wie;
    }

    public String getWat() {
        return wat;
    }

    public String getWanneer() {
        return DateFormat.getInstance().format(wanneer);
    }

    public String getRealisatie() {
        return realisatie;
    }

    public String getGroepBijsturing() {
        return groepBijsturing;
    }

    public String getLectorBijsturing() {
        return lectorBijsturing;
    }

    public void setIsGoedgekeurd(boolean keuring) {
        isGoedgekeurd = keuring;
    }

    public void setLectorBijsturing(String tekst) {
        lectorBijsturing = tekst;
    }

}

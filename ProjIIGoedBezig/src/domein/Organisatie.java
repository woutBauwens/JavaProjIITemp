/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.SQLConnection;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 *
 * @author BelgoBits
 */

@Entity
public class Organisatie implements Serializable {
    
    @Id
    private int OrganisatieId;
    
    private String Email;
    private String EmailExtentie;
    private String Locatie;
    private String Naam;
    private boolean heeftLabel;
    
    @Ignore
    private EntityManager em;

    
    private Organisatie(){
        em = SQLConnection.getManager();
    }
    
    public Organisatie(int Id){
        em.createQuery("SELECT o FROM Organisatie o WHERE o.OrganisatieId = :id", Organisatie.class).setParameter(0, Id).getSingleResult();
    }
}

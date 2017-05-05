/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BelgoBits
 */
@Entity
@Table(name = "ContactPersoon")
@NamedQuery(name = "ContactPersoon.CheckLogin", query="SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :email")
public class ContactPersoon implements Serializable {

    @Id
    protected int ContactPersoonId;

    protected String EmailContactPersoon;
    protected String Functie;
    protected String NaamContactPersoon;
    protected String VoornaamContactPersoon;
    protected int OrganisatieId1;

    private String Discriminator;

    @OneToMany(mappedBy = "HoofdLectorContactPersoonId", cascade = CascadeType.REFRESH)
    private List<Groep> groepen;

    public ContactPersoon() {

    }

    public int getId() {
        return ContactPersoonId;
    }

  /*  public ContactPersoon getLector() {
        if (Discriminator.equals("Lector")) {
            return this;
        }
        return null;
    } */

    public List<Groep> getGroepen() {
        return groepen;
    }
    
    public boolean isLector(){
        return Discriminator.equals("Lector");
    }
    
    public String passwordExtention(){
        return String.valueOf(OrganisatieId1) + String.valueOf(VoornaamContactPersoon.toLowerCase().charAt(0) + NaamContactPersoon.toLowerCase().charAt(0)) + String.valueOf(VoornaamContactPersoon.toLowerCase().charAt(0)) + String.valueOf(NaamContactPersoon.toLowerCase().charAt(0));
    }
    
    @Override
    public String toString(){
        return VoornaamContactPersoon + " " + NaamContactPersoon;
    }
}

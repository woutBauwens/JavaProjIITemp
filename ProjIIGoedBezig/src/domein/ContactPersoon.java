/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.LoginUser;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author BelgoBits
 */

@Entity
@Table(name = "ContactPersoon")
public class ContactPersoon implements Serializable{

    @Id
    protected int ContactPersoonId;
    
    protected String EmailContactPersoon;
    protected String Functie;
    protected String NaamContactPersoon;
    protected String VoornaamContactPersoon;
    @OneToOne
    @JoinColumn(name = "ContactPersoonId")
    private LoginUser user;

    private String Discriminator;
    
    public ContactPersoon(){
        
    }
    
    public int getId() {
        return ContactPersoonId;
    }

    public Lector getLector() {
        if(Discriminator.equals("lector")){
            return (Lector)this;
        }
        return null;
    }
    
}

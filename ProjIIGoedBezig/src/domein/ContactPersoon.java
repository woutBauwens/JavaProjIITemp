/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    

    public ContactPersoon(){
        
    }
    
    public int getId() {
        return ContactPersoonId;
    }
}

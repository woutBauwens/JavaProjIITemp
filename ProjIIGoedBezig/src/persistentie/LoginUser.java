/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.ContactPersoon;
import domein.Groep;
import java.io.Serializable;
import java.util.List;
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
@Table(name = "JavaUser")
public class LoginUser implements Serializable {

    private String password; // voorlopig simpel hashing later

    @Id
    @OneToOne
    @JoinColumn(name = "UserId")
    private ContactPersoon lector;

    protected LoginUser() {
    }

    public LoginUser(ContactPersoon lector){
        this.lector = lector;
    }
    
    public LoginUser(ContactPersoon lector, String password){
        this(lector);
        this.password = password;
    }
    
    public List<Groep> getGroepen() {
        return lector.getGroepen();
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return lector.getId();
    }
    
    

}

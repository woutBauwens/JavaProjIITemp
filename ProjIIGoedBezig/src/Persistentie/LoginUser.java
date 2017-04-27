/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistentie;

import domein.ContactPersoon;
import domein.DomeinController;
import domein.Groep;
import domein.Lector;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NoResultException;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.eclipse.persistence.annotations.ReadOnly;

/**
 *
 * @author BelgoBits
 */
@Entity
@Table(name = "JavaUser")
public class LoginUser implements Serializable {

    @Id
    private int UserId;

    private String password; // voorlopig simpel hashing later

    @OneToOne
    @JoinColumn(name = "USERID")
    private ContactPersoon lector;

    //@NamedQuery(name = "LectorByEmail", query = "SELECT c FROM ContactPersoon c WHERE c.Discriminator = 'Lector' AND c.EmailContactPersoon = :email;")
    protected LoginUser() {
    }

    public LoginUser(ContactPersoon lector){
        this.lector = lector;
    }
    
    public void login(){
        UserId = lector.getId();
        validate(password);
    }
    
    private void validate(String p) {
        String pass;
        try {
       //     pass = em.createQuery("SELECT u.password FROM LoginUser u WHERE u.UserId = :id").setParameter("id", UserId).getSingleResult().toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("De gebruiker is nog niet geregistreerd");
        }
/*
        if (p.equals(pass)) {
            password = p;
        } else {
            throw new IllegalArgumentException("Het paswoord komt niet overeen met het email adresss.");
        } */

    }

    public List<Groep> getGroepen() {
        return lector.getLector().getGroepenByLector();
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return UserId;
    }
    
    

}

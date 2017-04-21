/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistentie;

import domein.ContactPersoon;
import domein.Lector;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Transient;

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

    @Transient
    private final EntityManager em;

    //@NamedQuery(name = "LectorByEmail", query = "SELECT c FROM ContactPersoon c WHERE c.Discriminator = 'Lector' AND c.EmailContactPersoon = :email;")
    private LoginUser() {
        em = SQLConnection.getManager();
    }

    public LoginUser(String email, String password) {
        this();
        ContactPersoon l = em.createQuery("SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :mail", Lector.class).setParameter("mail", email).getSingleResult();
        if (l == null) {
            throw new IllegalArgumentException("De lector kan niet gevonden worden.");
        } else {
            UserId = l.getId();
            validate(password);
        }
    }

    private void validate(String p){
        try {
            String pass = em.createQuery("SELECT u FROM LoginUser u WHERE u.UserId = :id", LoginUser.class).setParameter("id", UserId).getSingleResult().password;
            if (p.equals(pass)) {
                password = p;
            } else {
                throw new IllegalArgumentException("Het paswoord komt niet overeen met het email adresss.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("De gebruiker is nog niet geregistreerd.");
        }
        
    }
}

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
import javax.persistence.NoResultException;
import javax.persistence.Table;
import javax.persistence.Transient;
import jdk.nashorn.internal.ir.annotations.Ignore;

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

    @Transient
    private Lector lector;

    
    
    //@NamedQuery(name = "LectorByEmail", query = "SELECT c FROM ContactPersoon c WHERE c.Discriminator = 'Lector' AND c.EmailContactPersoon = :email;")
    protected LoginUser() {
        em = SQLConnection.getManager();
    }

    public LoginUser(String email, String password) {
        this();
        DomeinController dc = new DomeinController();
        try {
            lector = new Lector(
                    em.createQuery("SELECT c from JavaUser c WHERE c.Lector = :mail",
                            ContactPersoon.class).setParameter("mail", email).getSingleResult().getId());
        } catch (NoResultException ex) {
            throw new NoResultException("Ongeldige Login");
        }
        UserId = lector.getId();
        validate(password);
        dc.setUser(lector);//subj to change
    }

    private void validate(String p) {
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

    public List<Groep> getGroepen() {
        return lector.getGroepenByLector();
    }
    
}

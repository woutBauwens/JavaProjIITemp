/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistentie;

import domein.Lector;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import jdk.nashorn.internal.ir.annotations.Ignore;
import util.JPAUtil;

/**
 *
 * @author BelgoBits
 */


@Entity
@Table(name = "JavaUser")
public class LoginUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LectorId")
    private int id;
    private String password; // voorlopig simpel hashing later
    
    @PersistenceContext
    private final EntityManager em;
    
    //@NamedQuery(name = "LectorByEmail", query = "SELECT c FROM ContactPersoon c WHERE c.Discriminator = 'Lector' AND c.EmailContactPersoon = :email;")
    
    private LoginUser() {
        em = SQLConnection.getManager();
    }

    public LoginUser(String email, String password) {
        this();
        Lector l = em.createQuery("SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :email;", Lector.class).setParameter("email", email).getSingleResult();
        if (l == null) {
            throw new IllegalArgumentException("De lector kan niet gevonden worden.");
        } else {
            getUserById(l.getId());
        }
    }

    private void getUserById(int id) {
        LoginUser u = em.createQuery("SELECT u.* FROM LoginUser u WHERE u.id == :id", LoginUser.class).setParameter(0, id).getSingleResult();
        if(password.equals(u.password)){
            
        } else {
            throw new IllegalArgumentException("Het paswoord komt niet overeen met het email adresss.");
        }
    }
}

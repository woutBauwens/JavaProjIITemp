/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.SQLConnection;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
/**
 *
 * @author Jonas
 */
@Entity
public class Groep implements Serializable {
    @Id
    private int GBGroepId;
    @OneToMany
    private List<Motivatie> motivaties;
    @OneToMany
    private List<Activiteit> acties;
    
    private String naam;
    
    private int HoofdLectorContactPersoonId;
    
    @Transient
    private EntityManager em;
    
    protected Groep(){
        em = SQLConnection.getManager();
    }
    
    public Groep(int id){
        GBGroepId = id;
        em.createQuery("SELECT o FROM Groep o WHERE o.GBGroepId = :id", Motivatie.class).setParameter("id", id).getSingleResult();
    }   
    
    @Override
    public String toString(){
        return naam;
    }
}

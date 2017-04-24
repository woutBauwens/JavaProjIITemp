/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.SQLConnection;
import java.io.Serializable;
import java.util.List;
import javafx.collections.transformation.SortedList;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 *
 * @author Jonas
 */
@Entity
@Table(name = "Groep")
public class Groep implements Serializable {
    @Id
    private int Id;
    @OneToMany
    private SortedList<Motivatie> motivaties;
    @OneToMany
    private List<Activiteit> acties;
    
    private int HoofdLectorContactPersoonId;
    
    @Ignore
    private EntityManager em;
    
    protected Groep(){
        em = SQLConnection.getManager();
    }
    
    public Groep(int id){
         em.createQuery("SELECT o FROM Groep o WHERE o.Id = :id", Motivatie.class).setParameter(0, Id).getSingleResult();
    }
    public Groep(int id, int lectorId){
         em.createQuery("SELECT o FROM Groep o WHERE o.Id = :id", Motivatie.class).setParameter(0, Id).getSingleResult();
    }
    
}

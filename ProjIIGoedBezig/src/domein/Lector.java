/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.SQLConnection;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author BelgoBits
 */
public class Lector extends ContactPersoon {

    private List<Groep> groepen;
    private final EntityManager em;

    public Lector(int id) {
        em = SQLConnection.getManager();
        ContactPersoonId = id;
        groepen = em.createQuery("SELECT g FROM Groep g WHERE g.HoofdLectorContactPersoonId = :lectorId", Groep.class).setParameter("lectorId", id).getResultList();
        
    }

    public List<Groep> getGroepenByLector() {
        return groepen;
    }
}

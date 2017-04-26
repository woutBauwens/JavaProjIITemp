/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistentie;

import domein.Groep;
import domein.Motivatie;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author kenne
 */
public class GroepMapper {

    public Motivatie geefMotivatie(String groepsnaam) {
        int id = (int) em.createQuery("SELECT g.GBGroepId FROM Groep g WHERE g.naam = :naam").setParameter("naam", groepsnaam).getSingleResult();
        Motivatie m =  em.createQuery("SELECT m FROM Motivatie m WHERE m.GBGroepId = :id",Motivatie.class).setParameter("id",id).getSingleResult();
       // return em.find(Motivatie.class,motivid);
return m;
    }

    private final EntityManager em;

    public GroepMapper() {
        em = SQLConnection.getManager();
    }

    public void bewaarFeedback(Groep gr, String response) {
        int id = gr.getHuidigeMotivatie().getMotivatieId();
        em.createQuery("update Motivatie set Feedback = :feedback WHERE MotivateId = :id ", Motivatie.class).setParameter("feedback", response).setParameter("id", id);

    }

    public List<Groep> getGroepenByLector(int id) {
        return em.createQuery("SELECT g FROM Groep g WHERE g.HoofdLectorContactPersoonId = :id").setParameter("id", id).getResultList();
    }

    public Groep geefGroep(String groepsnaam) {
        return em.createQuery("SELECT g FROM Groep g WHERE g.naam =:naam", Groep.class).setParameter("naam", groepsnaam).getSingleResult();
    }
}

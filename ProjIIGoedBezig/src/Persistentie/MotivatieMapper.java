/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistentie;

import domein.Groep;
import domein.Motivatie;
import javax.persistence.EntityManager;

/**
 *
 * @author kenne
 */
public class MotivatieMapper {

    public Groep geefGroep(String groepsnaam) {
        return em.createQuery("Select g from Groep g WHERE g.naam = :naam ", Groep.class).setParameter("naam", groepsnaam).getSingleResult();

    }

    private final EntityManager em;

    public MotivatieMapper() {
        em = SQLConnection.getManager();
    }

    public void bewaarFeedback(Groep gr, String response) {
        int id = gr.getHuidigeMotivatie().getMotivatieId();
        em.createQuery("update Motivatie set Feedback = :feedback WHERE MotivateId = :id ", Motivatie.class).setParameter("feedback", response).setParameter("id", id);

    }
}

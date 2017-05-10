/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Cursist;
import java.util.List;
import javax.persistence.Query;
import static repository.GenericDaoJpa.em;

/**
 *
 * @author kenne
 */
public class CursistDaoJpa extends GenericDaoJpa<Cursist> implements CursistDao {

    public CursistDaoJpa() {
        super(Cursist.class);
    }

    @Override
    public List<Cursist> getCursistenByGroep(int groepId) {
        List<Cursist> cursistenlijst;
        cursistenlijst = em.createNamedQuery("Cursist.getCursistenByGroep", Cursist.class).setParameter("groepId", groepId).getResultList();
        return cursistenlijst;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.SQLConnection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Id;

/**
 *
 * @author BelgoBits
 */
public class Lector extends ContactPersoon {

    @Id
    protected int ContactPersoonId;
    private List<Groep> groepen;
    //private final EntityManager em;

    
    
    public List<Groep> getGroepenByLector() {
        return groepen;
    }
}

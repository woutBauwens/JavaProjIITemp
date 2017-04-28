/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.ContactPersoon;
import domein.Groep;

/**
 *
 * @author Jonas
 */
public class GroepDaoJpa extends GenericDaoJpa<Groep>{

    public GroepDaoJpa() {
        super(Groep.class);
    }
    
    
}

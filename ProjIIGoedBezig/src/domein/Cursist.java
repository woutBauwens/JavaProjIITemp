/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kenne
 */
@Entity
@Table(name ="Cursist")
@NamedQuery(name = "Cursist.getCursistenByGroep", query="SELECT c from Cursist c WHERE c.gbGroepId = :groepId")
public class Cursist implements Serializable {

    @Id
    @Column(name="GBGroepId")
    private int gbGroepId;
    
    @Column(name="Email")
    private String email;

    
    protected Cursist(){
        
    }
    
    public String getEmail(){
        return email;
    }
    
    @Override
    public String toString(){
        return email;
    }
}

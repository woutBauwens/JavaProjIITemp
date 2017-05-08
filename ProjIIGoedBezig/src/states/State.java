/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import domein.Groep;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author BelgoBits
 */
@Entity
public class State implements Serializable {

    @Column(name = "Name")
    @Id
    protected String name;

    @Transient
    private GroepState state;

    public State() {
    }

    public GroepState getState(Groep g) {
        state = GroepStateFactory.createState(name, g);
        return state;
    }
    
    public void setState(String state, Groep g){
        name = state;
        this.state = GroepStateFactory.createState(name, g);
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    public GroepState getCurrentState(){
        return state;
    }
}
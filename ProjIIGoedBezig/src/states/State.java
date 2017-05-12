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

    @Transient
    private GroepStateFactory stateFactory;

    public State() {
    }

    public State(States name, GroepState state) {
        
        this.name = name.name();
        this.state = state;
    }

    public void setState(States s, Groep g) {
        state = new GroepStateFactory(g).createState(s);
    }

    @Override
    public String toString() {
        if (state == null) {
            return name;
        }
        return state.toString();
    }

    public GroepState getCurrentState() {
        return state;
    }
}

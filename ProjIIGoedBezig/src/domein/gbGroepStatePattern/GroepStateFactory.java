/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.gbGroepStatePattern;

import domein.Groep;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import repository.GenericDao;
import repository.GenericDaoJpa;

/**
 *
 * @author BelgoBits
 */
public class GroepStateFactory {

    private final Map<String, Supplier<State>> map = new HashMap();
    private List<State> states;

    public final void add(String name, Supplier<State> value) {
        map.put(name, value);
    }

    public GroepStateFactory() {
        GenericDao g = new GenericDaoJpa(State.class);
        states = g.getStates();
        initializeClasses();
    }

    private void initializeClasses() {
        for (State s : states) {
            add(s.getName(), s.getSupplier(s.getName()));
        }
    }

    public State createPlayerFactory(String state) {
        return map.get(state).get();
    }
}

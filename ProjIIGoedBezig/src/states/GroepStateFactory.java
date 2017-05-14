/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import domein.Groep;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * @author kenne
 */
public class GroepStateFactory {

    private final Map<States, Supplier<GroepState>> map = new HashMap();

    public final void add(States name, Supplier<GroepState> state) {
        map.put(name, state);
    }

    public GroepStateFactory(Groep gr) {
        initializeClasses(gr);
    }

    private void initializeClasses(Groep gr) {
        add(States.empty, () -> new GeenMotivatieState(gr));
        add(States.written, () -> new HeeftMotivatieState(gr));
        add(States.pending, () -> new MotivatieInBeoordelingState(gr));
        add(States.approved, () -> new MotivatieGoedgekeurdState(gr));
        add(States.labeled, () -> new LabelAanvaardState(gr));
        add(States.actiegoedgekeurd, () -> new ActiesGoedgekeurdState(gr));
        add(States.actiepending, () -> new ActiesInBeoordelingState(gr));
    }

    public  GroepState createState(States currentState) {
        return map.get(currentState).get();
    }
}

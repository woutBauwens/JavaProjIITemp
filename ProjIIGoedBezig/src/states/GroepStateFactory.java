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
/*
    public static GroepState createState(String currentState, Groep gr) {

        switch (currentState) {
            case "empty":
                return new GeenMotivatieState(gr);
            case "written":
                return new HeeftMotivatieState(gr);
            case "pending":
                return new MotivatieInBeoordelingState(gr);
            case "approved":
                return new MotivatieGoedgekeurdState(gr);
            case "labeled":
                return new LabelAanvaardState(gr);
            case "actiegoedgekeurd":
                return new ActiesGoedgekeurdState(gr);
            case "actiepending":
                return new ActiesInBeoordelingState(gr);
            default:
                return new GeenMotivatieState(gr);

        }

    } */
}

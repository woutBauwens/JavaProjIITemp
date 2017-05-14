
package states;

import domein.Activiteit;
import domein.Groep;
import java.util.List;

/**
 *
 * @author kenne
 */
public class ActiesInBeoordelingState extends GroepState {

    public ActiesInBeoordelingState(Groep gr) {
        super(gr);
    }

    @Override
    public String toString() {
        return States.actiepending.toString();
    }

    @Override
    public boolean actiesToegankelijk() {
        return false;
    }

    @Override
    public String toonMotivatie() {
        return String.format("Organisatie: %s%n%s%n", groep.getHuidigeMotivatie().getNaamOrganisatie(), groep.getHuidigeMotivatie().getTekst());
    }

    @Override
    public void actiesgekeurd(boolean b, String titel, String feedback) {
        groep.verwerkActieKeuring(b,titel,feedback);
    }

    @Override
    public boolean actiesgekeurd() {
        List<Activiteit> acties = groep.getActies();
        boolean allesgekeurd = true;
        for (Activiteit a : acties) {
            //!a.isGekeurd() || in if
            if (!a.isGekeurd()) {
                allesgekeurd = false;
            }
        }
        return allesgekeurd;
    }
  
        @Override
    public void keurActiePlan(boolean b, String globaleFeedback) {
        if (!b && globaleFeedback.isEmpty()) {
            throw new IllegalArgumentException();
        }
        groep.setActieplanFeedback(globaleFeedback);
        groep.keurActie(b);
    }
}

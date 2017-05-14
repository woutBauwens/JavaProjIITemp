/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author kenne
 */
public class MotivatieController {

    private Groep selectedGroep;

    public MotivatieController() {

    }

    public void setSelectedGroep(Groep gr) {
        this.selectedGroep = gr;
    }

    public void setFeedback(String response) {
        selectedGroep.setFeedback(response);
    }

    public void setKeuring(boolean keuring) {
        selectedGroep.setKeuring(keuring);
    }

    public String toonMotivatie() {
        return selectedGroep.toonMotivatie();
    }

    public boolean isMotivatieVerstuurd() {
        return selectedGroep.isMotivatieVerstuurd();
    }

    public String getMotivaties() {
        List<Motivatie> motivaties = selectedGroep.getMotivaties();
        Collections.reverse(motivaties);
        //list gereversed ipv telkens historiek te overschrijven en er opnieuw aan te plakken, same effect?

        StringBuilder historiek = new StringBuilder();
        motivaties.stream().filter((m) -> (m.isVerstuurd() && m.getFeedback() != null)).forEachOrdered((m) -> {
            historiek.append("\n").append(m.toString());
        });

        return historiek.toString();
    }

    public String geefMotivatieStatus() {
        return selectedGroep.geefMotivatieStatus();
    }
}

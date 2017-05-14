package states;

import domein.Groep;

public class MotivatieInBeoordelingState extends GroepState {

    public MotivatieInBeoordelingState(Groep gr) {
        super(gr);
    }

    @Override
    public String toString() {
        return States.pending.toString();
    }

    @Override
    public String toonMotivatie() {
        return String.format("Organisatie: %s%n%s%n", groep.getHuidigeMotivatie().getNaamOrganisatie(), groep.getHuidigeMotivatie().getTekst());
    }

    @Override
    public String geefMotivatieStatus() {
        return "Motivatie nog te keuren";
    }

    @Override
    public void verwerkMotivatieKeuring(boolean keuring) {
        groep.setKeuring(keuring);
    }
}

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieZakonczenieObslugi extends BasicSimEvent {
    public ZdarzenieZakonczenieObslugi(Kasa kasa, double delay, Klient params) throws SimControlException {
        super(kasa, delay, params);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Kasa k = (Kasa)getSimObj();
        Klient klient = (Klient) eventParams;
        k.kasaZajeta = false;
        k.zajetoscKasy.setValue(0);
        k.czasPrzebywania.setValue(simTime() - klient.czasWejscia);
        System.out.println("[" + simTime() + "] ZAKONCZENIE OBSLUGI -> klient nr: " + klient.nr + ", dlugosc kolejki: " + k.kolejka.size());
        new ZdarzenieRozpoczecieObslugi(k);
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}

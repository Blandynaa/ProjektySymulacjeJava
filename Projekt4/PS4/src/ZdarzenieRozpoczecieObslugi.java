import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieRozpoczecieObslugi extends BasicSimEvent {
    public ZdarzenieRozpoczecieObslugi(Kasa kasa) throws SimControlException {
        super(kasa);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Kasa p = (Kasa)getSimObj();
        if(!p.kolejka.isEmpty()){
            Klient klient = p.kolejka.remove(0);
            p.dlugoscKolejki.setValue(p.kolejka.size());
            p.obslugiwany = klient;
            p.kasaZajeta = true;
            p.zajetoscKasy.setValue(1);
            double czasObslugi = p.rng.exponential(p.mi);
            System.out.println("[" + simTime() + "] ROZPOCZECIE OBSLUGI -> interesant nr: " + klient.nr + ", dlugosc kolejki: " + p.kolejka.size());
            new ZdarzenieZakonczenieObslugi(p, czasObslugi, klient);
        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}

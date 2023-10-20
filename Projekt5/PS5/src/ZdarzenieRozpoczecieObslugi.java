import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieRozpoczecieObslugi extends BasicSimEvent {
    public ZdarzenieRozpoczecieObslugi(Kasa kasa) throws SimControlException {
        super(kasa);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Kasa kasa = (Kasa)getSimObj();
        if(!kasa.sklep.kolejka.isEmpty() && !kasa.sklep.okienkaZepsute){
            Interesant interesant = kasa.sklep.kolejka.remove(0);
            kasa.sklep.dlugoscKolejki.setValue(kasa.sklep.kolejka.size());
            kasa.zajete = true;
            kasa.interesant = interesant;
            double czasObslugi = kasa.sklep.rng.exponential(kasa.sklep.mi);
            System.out.println("[" + simTime() + "] :: Rozpoczęcie obsługi, Interesant nr: " + interesant.nr + ", Dlugość kolejki: " + kasa.sklep.kolejka.size());
            kasa.zakonczenieObslugi = new ZdarzenieZakonczenieObslugi(kasa, czasObslugi, interesant);
        }
        kasa.sklep.zajetoscOkienek.setValue(kasa.sklep.zajete());
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}

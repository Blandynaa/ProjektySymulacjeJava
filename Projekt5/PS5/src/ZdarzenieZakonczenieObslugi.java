import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieZakonczenieObslugi extends BasicSimEvent {
    public ZdarzenieZakonczenieObslugi(Kasa kasa, double delay, Interesant params) throws SimControlException {
        super(kasa, delay, params);
    }

    @Override
    protected void stateChange() throws SimControlException {

        Kasa kasa = (Kasa)getSimObj();
        if(kasa.zakonczenieObslugi != null) {
            Interesant interesant = (Interesant) eventParams;
            kasa.zajete = false;
            kasa.interesant = null;
            kasa.zakonczenieObslugi = null;

            kasa.sklep.czasPrzebywania.setValue(simTime() - interesant.czasWejscia);
            System.out.println("[" + simTime() + "] :: Zakończenie obsługi, Interesant nr: " + interesant.nr + ", Dlugość kolejki: " + kasa.sklep.kolejka.size() + ", Zajete kasy: " + kasa.sklep.zajete());

            if (kasa.sklep.p >= Math.abs(kasa.sklep.rng.nextDouble())) {
                if (kasa.sklep.kolejka.size() < kasa.sklep.L) {
                    kasa.sklep.kolejka.add(interesant);
                    System.out.println("[" + simTime() + "] :: Ponowne dołączenie, Interesant nr: " + interesant.nr + ", Dlugość kolejki: " + kasa.sklep.kolejka.size() + ", Zajete kasy: " + kasa.sklep.zajete());
                } else {
                    kasa.sklep.straceni++;
                    System.out.println("[" + simTime() + "] :: Strata interesanta, Interesant nr: " + interesant.nr + ", Dlugość kolejki: " + kasa.sklep.kolejka.size() + ", Zajete kasy: " + kasa.sklep.zajete());
                }
            }
            kasa.sklep.zajetoscOkienek.setValue(kasa.sklep.zajete());

            new ZdarzenieRozpoczecieObslugi(kasa);
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

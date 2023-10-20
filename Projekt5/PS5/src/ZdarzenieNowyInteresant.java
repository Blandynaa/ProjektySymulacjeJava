import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieNowyInteresant extends BasicSimEvent {
    public ZdarzenieNowyInteresant(Sklep sklep, double delay) throws SimControlException {
        super(sklep, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Sklep sklep = (Sklep)getSimObj();
        if(sklep.licznikInteresantow < sklep.maxInteresantow){
            Interesant interesant = new Interesant(++sklep.licznikInteresantow, sklep.simTime(), sklep.rng.exponential(sklep.ro), sklep);
            if(sklep.kolejka.size() <= sklep.L)
            {
                sklep.kolejka.add(interesant);
                sklep.dlugoscKolejki.setValue(sklep.kolejka.size());
                System.out.println("[" + simTime() + "] :: Nowy Interesant, Interesant nr: " + interesant.nr + ", Dlugość kolejki: " + sklep.kolejka.size() + ", Zajete kasy: " + sklep.zajete());
                for(Kasa kasa : sklep.okienka)
                {
                    if(!kasa.zajete && !sklep.okienkaZepsute) new ZdarzenieRozpoczecieObslugi(kasa);
                }
            }
            else{
                System.out.println("[" + simTime() + "] Strata interesanta, Interesant nr: " + interesant.nr + ", Dlugość kolejki: " + sklep.kolejka.size() + ", Zajete kasy: " + sklep.zajete());
                sklep.straceni++;
            }
        }
        sklep.zajetoscOkienek.setValue(sklep.zajete());
        double kolejnyInteresant = sklep.rng.exponential(sklep.lambda);
        new ZdarzenieNowyInteresant(sklep, kolejnyInteresant);
        if(sklep.kolejka.size() == 0 && sklep.zajete() == 0 && !sklep.okienkaZepsute) stopSimulation();
    }



    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}

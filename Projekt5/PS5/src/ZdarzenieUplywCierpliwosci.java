import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieUplywCierpliwosci extends BasicSimEvent {
    public ZdarzenieUplywCierpliwosci(Interesant interesant, double delay) throws SimControlException {
        super(interesant, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Interesant interesant = (Interesant)getSimObj();
        boolean obslugiwany = false;
        for(Kasa kasa : interesant.sklep.okienka){
            if(kasa.interesant == interesant) obslugiwany = true;
        }
        if(!obslugiwany){
            System.out.println("[" + simTime() + "] :: Uplyw cierpliwosci, Interesant nr: " + interesant.nr + ", Dlugość kolejki: " + interesant.sklep.kolejka.size() + ", Zajete kasy: " + interesant.sklep.zajete());
            interesant.sklep.kolejka.remove(interesant);
            interesant.sklep.straceni++;
        }
        interesant.sklep.zajetoscOkienek.setValue(interesant.sklep.zajete());
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}

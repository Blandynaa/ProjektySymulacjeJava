import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieZepsucieKas extends BasicSimEvent {
    public ZdarzenieZepsucieKas(Sklep sklep, double delay) throws SimControlException {
        super(sklep, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Sklep sklep = (Sklep) getSimObj();
        sklep.okienkaZepsute = true;

        String zwroceni = "";

        for(Kasa kasa : sklep.okienka){
            if(kasa.getSimEventList().size() > 0) kasa.getSimEventList().getFirst().terminate();
            kasa.zajete = false;
            if(kasa.interesant != null){
                kasa.sklep.kolejka.add(0, kasa.interesant);
                zwroceni += kasa.interesant.nr + ", ";
                kasa.interesant = null;
            }
        }
        sklep.zajetoscOkienek.setValue(sklep.zajete());
        System.out.println("[" + simTime() + "] :: Zepsucie kas, Interesanci nr: " + zwroceni + ", Dlugość kolejki: " + sklep.kolejka.size() + ", Zajete kasy: " + sklep.zajete());

        new ZdarzenieNaprawaKas(sklep, sklep.rng.exponential(sklep.beta));
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
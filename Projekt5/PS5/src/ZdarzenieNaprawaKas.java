import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieNaprawaKas extends BasicSimEvent {
    public ZdarzenieNaprawaKas(Sklep sklep, double delay) throws SimControlException {
        super(sklep, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Sklep sklep = (Sklep)getSimObj();
        sklep.okienkaZepsute = false;

        for(Kasa kasa : sklep.okienka){
            new ZdarzenieRozpoczecieObslugi(kasa);
        }
        sklep.zajetoscOkienek.setValue(sklep.zajete());
        System.out.println("[" + simTime() + "] :: Naprawa okienek, Interesant nr: -, Dlugość kolejki: " + sklep.kolejka.size() + ", Zajete kasy: " + sklep.zajete());
        new ZdarzenieZepsucieKas(sklep, sklep.rng.exponential(sklep.alfa));
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
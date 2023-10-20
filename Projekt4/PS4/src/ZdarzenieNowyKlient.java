import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieNowyKlient extends BasicSimEvent {
    public ZdarzenieNowyKlient(Kasa kasa, double delay) throws SimControlException {
        super(kasa, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Kasa k = (Kasa)getSimObj();
        if(k.licznikKlientow < k.maxKlientow){
            Klient klient = new Klient(++k.licznikKlientow, k.simTime());
            k.kolejka.add(klient);
            k.dlugoscKolejki.setValue(k.kolejka.size());
            System.out.println("[" + simTime() + "] NOWY KLIENT -> klient nr: " + klient.nr + ", dlugosc kolejki: " + k.kolejka.size());

            if(!k.kasaZajeta) new ZdarzenieRozpoczecieObslugi(k);
        }
        double kolejnyKlient = k.rng.exponential(k.lambda);
        new ZdarzenieNowyKlient(k, kolejnyKlient);
        if(k.kolejka.size() == 0 && k.kasaZajeta == false) stopSimulation();
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}

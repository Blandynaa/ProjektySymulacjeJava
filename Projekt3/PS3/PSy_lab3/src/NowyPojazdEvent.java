import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.random.RNGenerator;

public class NowyPojazdEvent extends BasicSimEvent<Trasa, Object>  {
    RNGenerator random = new RNGenerator();
    int ID = 0;
    int aktKrok = 1;
    public NowyPojazdEvent(Trasa trasa, Object params, double period) throws SimControlException{
        super(trasa, params, period);
    }

    @Override
    protected void stateChange(){
        Trasa trasa = getSimObj();
        if(trasa.czasPojawieniaSieNowegoPojazdu <= simTime())
        {
            double predkosc1 = trasa.rng.uniform(trasa.minV, trasa.maxV);
            double predkosc2 = trasa.rng.uniform(trasa.minV, trasa.maxV);
            double parkowanie = trasa.rng.uniform(trasa.minP, trasa.maxP);

            Pojazd pojazd = new Pojazd( ID, predkosc1, predkosc2, parkowanie, simTime(), simTime());
            ID++;
            trasa.listaPojazdowNaTrasie.add(pojazd);

            trasa.czasPojawieniaSieNowegoPojazdu = random.exponential(trasa.lambda) + simTime();
            System.out.println("[" + simTime() + "] Nowy pojazd, Pojazd nr " + pojazd.ID);
        }
        aktKrok++;
        if(aktKrok == trasa.krokowWOdcinku){
            trasa.liczbaPojazdowNaTrasie.setValue(trasa.listaPojazdowNaTrasie.size());
            aktKrok = 0;
        }
    }

    @Override
    protected void onTermination() {
    }

    @Override
    public Object getEventParams() {
        return null;
    }
}

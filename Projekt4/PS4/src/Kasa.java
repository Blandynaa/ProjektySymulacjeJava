import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

import java.util.ArrayList;

public class Kasa extends BasicSimObj {
    RNGenerator rng;
    double lambda;
    double mi;
    ArrayList<Klient> kolejka;
    Klient obslugiwany;
    MonitoredVar czasPrzebywania;
    MonitoredVar dlugoscKolejki;
    MonitoredVar zajetoscKasy;
    int licznikKlientow;
    int maxKlientow;
    boolean kasaZajeta;

    public Kasa(RNGenerator rng, double lambda, double mi, int maxKlientow){
        this.rng = rng;
        this.lambda = lambda;
        this.mi = mi;
        this.maxKlientow = maxKlientow;
        kolejka = new ArrayList<>();
        obslugiwany = null;
        kasaZajeta = false;
        czasPrzebywania = new MonitoredVar();
        dlugoscKolejki = new MonitoredVar();
        zajetoscKasy = new MonitoredVar();
        licznikKlientow = 0;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}

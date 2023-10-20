import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

import java.util.ArrayList;

public class Sklep extends BasicSimObj {
    RNGenerator rng;
    double mi;
    ArrayList<Interesant> kolejka;
    boolean okienkoZajete;
    MonitoredVar czasPrzebywania;
    MonitoredVar dlugoscKolejki;
    MonitoredVar zajetoscOkienek;
    public int licznikInteresantow;
    double lambda;
    int maxInteresantow;
    ArrayList<Kasa> okienka;
    boolean okienkaZepsute;
    int M, L;
    double alfa, beta;
    double p;
    int straceni;
    double ro;

    public Sklep(RNGenerator rng, double mi, int ilosc_okienek, double alfa, double beta, int L, double p, double lambda, int maxInteresantow, double ro){
        this.rng = rng;
        this.mi = mi;
        kolejka = new ArrayList<>();
        okienkoZajete = false;
        czasPrzebywania = new MonitoredVar();
        dlugoscKolejki = new MonitoredVar();
        zajetoscOkienek = new MonitoredVar();
        M = ilosc_okienek;
        okienkaZepsute = false;
        this.L = L;
        this.alfa =alfa;
        this.beta = beta;
        this.p = p;
        straceni = 0;
        okienka = new ArrayList<>();
        this.lambda = lambda;
        this.maxInteresantow = maxInteresantow;
        this.ro = ro;
        licznikInteresantow = 0;
        generacjaOkienek(alfa, beta, rng, lambda, mi, maxInteresantow, ro);
    }

    private void generacjaOkienek(double alfa, double beta, RNGenerator rng, double lambda, double mi, int maxInteresantow, double ro)
    {
        for(int i = 0; i < M; i++){
            okienka.add(new Kasa(this, rng, lambda, maxInteresantow, ro));
        }

        try {
            new ZdarzenieZepsucieKas(this, rng.exponential(alfa));
        } catch (SimControlException e) {
            throw new RuntimeException(e);
        }
    }

    public int zajete(){
        int x = 0;
        for(Kasa kasa : okienka) if(kasa.zajete) x++;
        return x;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

import java.util.ArrayList;
import java.util.List;

public class Trasa extends BasicSimObj {
    public RNGenerator rng;
    public List<Pojazd> listaPojazdowNaTrasie;
    MonitoredVar czasPrzejazdu;
    MonitoredVar liczbaPojazdowNaTrasie;
    double czasPojawieniaSieNowegoPojazdu;
    double dlugosc, minV, maxV, minP, maxP, lambda;
    double odcinek;
    double krokowWOdcinku;

    public Trasa(double d, double v1, double v2, double lam, double p1, double p2, double odc, double ileOdc){
        super();
        rng = new RNGenerator();
        czasPrzejazdu = new MonitoredVar(0);
        liczbaPojazdowNaTrasie = new MonitoredVar(0);
        listaPojazdowNaTrasie = new ArrayList<>();
        dlugosc = d;
        minV = v1;
        maxV = v2;
        lambda = lam;
        minP = p1;
        maxP = p2;
        odcinek = odc;
        krokowWOdcinku = (int)ileOdc;
        czasPojawieniaSieNowegoPojazdu = rng.uniform(minP, maxP);
    }

    public double round(double x){
        return Math.round(x * 100.0) / 100.0;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return true;
    }
}

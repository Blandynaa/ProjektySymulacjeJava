import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

public class Kasa extends BasicSimObj {
    double alfa, beta;
    Sklep sklep;
    Interesant interesant;
    public RNGenerator rng;
    public double lambda;
    public int licznikInteresantow;
    public int maxInteresantow;

    public int straceni;

    public double ro;

    ZdarzenieZakonczenieObslugi zakonczenieObslugi;
    boolean zajete;

    public Kasa(Sklep sklep, RNGenerator rng, double lambda, int maxInteresantow, double ro){
        this.sklep = sklep;
        zakonczenieObslugi = null;
        zajete = false;
        this.rng = rng;
        this.sklep = sklep;
        this.lambda = lambda;
        this.maxInteresantow = maxInteresantow;
        this.ro = ro;
        straceni = 0;
        licznikInteresantow = 0;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

public class Interesant extends BasicSimObj {
    int nr;
    double czasWejscia;
    Sklep sklep;

    public Interesant(int nr, double czasWejscia, double wyjscie, Sklep sklep){
        this.nr = nr;
        this.czasWejscia = czasWejscia;
        this.sklep = sklep;
        try {
            new ZdarzenieUplywCierpliwosci(this, wyjscie);
        } catch (SimControlException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}

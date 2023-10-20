import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

import java.util.ArrayList;
import java.util.List;

public class RuchPojazdowEvent extends BasicSimEvent<Trasa, Object> {
    int aktKrok;
    public RuchPojazdowEvent(Trasa szlak, Object object, double period) throws SimControlException{
        super(szlak, object, period);
    }

    @Override
    protected void stateChange() {

        List<Pojazd> pojazdyparkujace = new ArrayList<>();
        List<Pojazd> dousuniecia = new ArrayList<>();

        Trasa trasa = getSimObj();

        for(Pojazd pojazd : trasa.listaPojazdowNaTrasie){
            double dt = simTime() - pojazd.poprzedniKrok;
            pojazd.poprzedniKrok = simTime();
            pojazd.przebytaDroga = pojazd.przebytaDroga + pojazd.predkosc1 * dt;
            if(Math.floor(pojazd.przebytaDroga / trasa.odcinek) > pojazd.pozycja){
                pojazd.pozycja = (int)Math.floor(pojazd.przebytaDroga / trasa.odcinek);
                System.out.println("[" + simTime() + "] Ruch Pojazdu, Pojazd nr " + pojazd.ID + ", bieżąca pozycja: " + pojazd.pozycja + ", przebyta droga: " + trasa.round(pojazd.przebytaDroga));
            }

            if(pojazd.pozycja == Math.floor(trasa.dlugosc / trasa.odcinek)) {
                System.out.println("[" + simTime() + "] Parkowanie Pojazdu, Pojazd nr " + pojazd.ID + ", przebyta droga: " + trasa.round(pojazd.przebytaDroga) + ", czas jazdy: " + trasa.round(simTime() - pojazd.czasPojawienia));
                pojazd.powrot = true;
            }

            if(2*Math.floor(pojazd.przebytaDroga / trasa.odcinek) > pojazd.pozycja && pojazd.powrot == true){
                pojazd.pozycja = (int)Math.floor(pojazd.przebytaDroga / trasa.odcinek);
                System.out.println("[" + simTime() + "] Ruch powrotny Pojazdu, Pojazd nr " + pojazd.ID + ", bieżąca pozycja: " + pojazd.pozycja + ", przebyta droga: " + trasa.round(pojazd.przebytaDroga));
            }

            if(pojazd.przebytaDroga >= 2*trasa.dlugosc){
                System.out.println("[" + simTime() + "] Usunięcie Pojazdu, Pojazd nr " + pojazd.ID + ", przebyta droga: " + trasa.round(pojazd.przebytaDroga) + ", czas jazdy: " + trasa.round(simTime() - pojazd.czasPojawienia));
                dousuniecia.add(pojazd);
            }
        }

        for(Pojazd pojazd : dousuniecia){
            trasa.listaPojazdowNaTrasie.remove(pojazd);
            trasa.czasPrzejazdu.setValue(simTime() - pojazd.czasPojawienia);
        }
    }

    @Override
    protected void onTermination() throws SimControlException {
    }

    @Override
    public Object getEventParams() {
        return null;
    }
}

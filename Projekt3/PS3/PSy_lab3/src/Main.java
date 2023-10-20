import dissimlab.monitors.Diagram;
import dissimlab.monitors.Statistics;
import dissimlab.simcore.*;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws SimControlException {
        //losowanie prędkości z przedziału:
        double c = 5;
        double d = 12;
        //losowanie czasu pojawienia sie nowego pojazdu (rozkład wykładniczy z parametrem lambda):
        double lambda = 0.1;
        //losowanie czasu parkowania z przedziału:
        double e = 10;
        double f = 20;

        double dlugoscSzlaku = 1000;
        double krok = 0.1;
        int ileOdcinkow = 50;
        double odcinek = dlugoscSzlaku / ileOdcinkow;
        double krokowWOdcinku = odcinek/krok;

        SimManager sm = SimManager.getInstance();
        sm.setEndSimTime(dlugoscSzlaku);

        Trasa szlak = new Trasa(dlugoscSzlaku, c, d, lambda, e, f, odcinek, krokowWOdcinku);

        RuchPojazdowEvent rpe = new RuchPojazdowEvent(szlak, null, krok);
        NowyPojazdEvent npe = new NowyPojazdEvent(szlak, null, krok);

        sm.startSimulation();

        Diagram diagram = new Diagram(Diagram.DiagramType.TIME, "Liczba pojazdow");
        Diagram diagram2 = new Diagram(Diagram.DiagramType.DISTRIBUTION, "Czas jazdy");

        diagram.add(szlak.liczbaPojazdowNaTrasie, Color.BLUE);
        diagram.show();

        diagram2.add(szlak.czasPrzejazdu, Color.RED);
        diagram2.show();

        System.out.println("Średnia liczba pojazdów na trasie: " + Statistics.weightedMean(szlak.liczbaPojazdowNaTrasie));
        System.out.println("Średni czas jazdy: " + Statistics.arithmeticMean(szlak.czasPrzejazdu));
    }
}
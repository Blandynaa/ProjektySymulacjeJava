import dissimlab.monitors.Diagram;
import dissimlab.monitors.Statistics;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws SimControlException {
        RNGenerator rng = new RNGenerator();
        double mi = 0.018;
        double lambda = 0.08;
        int maxInteresantow = 200;
        double delay_ZdarzenieNowyInteresant = 0;
        int M = 3; //ilosc kas
        int L = 5; //dlugosc kolejki
        double p = 0.2;
        double ro = 0.03;
        double alfa = 0.03;
        double beta = 0.04;

        SimManager sm = SimManager.getInstance();
        Sklep sklep = new Sklep(rng, mi, M, alfa, beta, L, p, lambda, maxInteresantow, ro);

        try {
            ZdarzenieNowyInteresant zni = new ZdarzenieNowyInteresant(sklep, delay_ZdarzenieNowyInteresant);
        } catch (SimControlException e) {
            throw new RuntimeException(e);
        }

        sm.startSimulation();

        Diagram diagram1 = new Diagram(Diagram.DiagramType.TIME, "Zmiana w czasie długości kolejki");
        diagram1.add(sklep.dlugoscKolejki, Color.BLUE);

        Diagram diagram2 = new Diagram(Diagram.DiagramType.DISTRIBUTION, "Dystrybuanta czasu przebywania");
        diagram2.add(sklep.czasPrzebywania, Color.RED);

        Diagram diagram3 = new Diagram(Diagram.DiagramType.DISTRIBUTION, "Dystrybuanta dlugosci kolejki");
        diagram3.add(sklep.dlugoscKolejki, Color.GREEN);

        Diagram diagram4 = new Diagram(Diagram.DiagramType.TIME, "Zmiana w czasie liczby zajetych kas");
        diagram4.add(sklep.zajetoscOkienek, Color.PINK);

        diagram1.show();
        diagram2.show();
        diagram3.show();
        diagram4.show();

        System.out.println("Średnia długość kolejki: " + Statistics.arithmeticMean(sklep.dlugoscKolejki));
        System.out.println("Średni czas przebywania interesanta: " + Statistics.arithmeticMean(sklep.czasPrzebywania));
        System.out.println("Średnia zajętość okienek: " + Statistics.weightedMean(sklep.zajetoscOkienek));
        System.out.println("Prawdopodobienstwo straty: " + ((double) sklep.straceni / (double)sklep.licznikInteresantow));
    }
}
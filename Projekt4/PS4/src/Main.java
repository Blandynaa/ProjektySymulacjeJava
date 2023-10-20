import dissimlab.monitors.Diagram;
import dissimlab.monitors.Statistics;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws SimControlException {
        RNGenerator rng = new RNGenerator();
        double mi = 0.08;
        double lambda = 0.1;
        int maxKlientow = 50;
        double delay_ZdarzenieNowyInteresant = 0;

        SimManager sm = SimManager.getInstance();
        Kasa kasa = new Kasa(rng, lambda, mi, maxKlientow);

        try {
            ZdarzenieNowyKlient zni = new ZdarzenieNowyKlient(kasa, delay_ZdarzenieNowyInteresant);
        } catch (SimControlException e) {
            throw new RuntimeException(e);
        }

        sm.startSimulation();

        Diagram diagram1 = new Diagram(Diagram.DiagramType.TIME, "Zmiana w czasie długości kolejki");
        diagram1.add(kasa.dlugoscKolejki, Color.BLUE);

        Diagram diagram2 = new Diagram(Diagram.DiagramType.DISTRIBUTION, "Dystrybuanta czasu przebywania");
        diagram2.add(kasa.czasPrzebywania, Color.RED);

        Diagram diagram3 = new Diagram(Diagram.DiagramType.TIME, "Zmiana w czasie zajetosci okienka");
        diagram3.add(kasa.zajetoscKasy, Color.DARK_GRAY);

        diagram1.show();
        diagram2.show();
        diagram3.show();

        System.out.println("Średnia długość kolejki: " + Statistics.arithmeticMean(kasa.dlugoscKolejki));
        System.out.println("Średni czas przebywania interesanta: " + Statistics.arithmeticMean(kasa.czasPrzebywania));
        System.out.println("Srednia zajetosc kasy: " + Statistics.weightedMean(kasa.zajetoscKasy));

    }
}
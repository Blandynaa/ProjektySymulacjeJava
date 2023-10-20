package zadanie2;

import dissimlab.random.RNGenerator;

public class Poczta {

    public void symuluj(double czasZakon, int liczZglosz) {
        RNGenerator random = new RNGenerator();
        int N = 10; // liczba stanowisk
        int ileZglosz;
        int handled = 0; // liczba zgloszen obsluzonych
        int rejected = 0; // liczba zgloszen odrzuconych
        int waiting = 0;
        double simTime = 0;
        double lambda = 3;

        double a = 2.0;
        double b = 10.0;

        Stanowisko[] stanowisko = new Stanowisko[N];

        for(int i = 0; i < N; i++){
            stanowisko[i] = new Stanowisko(random.uniform(a,b));
        }

        for(ileZglosz = 0; simTime < czasZakon && ileZglosz < liczZglosz; ileZglosz++) {
            for(int i = 0; i < N; i++) {
                if(stanowisko[i].CzasZajetosci() <= simTime && waiting > 0) {
                    stanowisko[i].Zajecie(simTime);
                    handled++;
                    waiting--;
                }
                else if(i == (N - 1) && waiting > 0){
                    rejected ++;
                    waiting--;
                }
            }

            simTime += random.exponential(lambda);
            waiting++;
        }
        rejected++;

        double willHandled = (double)handled/ileZglosz *100;
        double willRejected = (double)rejected/ileZglosz *100;
        System.out.println("Liczba zgloszen: " + ileZglosz);
        System.out.println("Czas wykonania: " + simTime);
        System.out.println();
        System.out.println("Obsluzone zgloszenia: " + handled);
        System.out.println("Odrzucone zgloszenia: " + rejected);
        System.out.println();
        System.out.println("Prawdopodobienstwo obsluzenia zgloszenia: " + willHandled + "%");
        System.out.println("Prawdopodobienstwo odrzucenia zgloszenia: " + willRejected + "%");
    }
}

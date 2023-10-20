package zadanie1;

import dissimlab.random.RNGenerator;

public class Calka {
    public double calculate(double a, double b, IFunc f, int rep) {
        RNGenerator rnGenerator = new RNGenerator(System.nanoTime());
        double fMax = f.max(a, b);
        double x, y;
        int k = 0;

        for (int i = 0; i < rep; i++) {
            x = rnGenerator.uniform(a, b);
            y = rnGenerator.uniform(0, fMax);
            if (y <= f.func(x)) {
                k++;
            }
        }

        return ((double) k / rep) * (b - a) * fMax;
    }

}

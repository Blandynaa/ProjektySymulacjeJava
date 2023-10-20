package zadanie1;

public class Funkcja2 implements IFunc {

    public double func(double x) {
        return 1-x*x/(9+x);
    }

    public double max(double a, double b) {
        double maxValue = func(a);

        for (double i = a; i <= b; i++) {
            double currEval = func(i);
            if (currEval > maxValue) {
                maxValue = currEval;
            }
        }
        return maxValue;
    }
}

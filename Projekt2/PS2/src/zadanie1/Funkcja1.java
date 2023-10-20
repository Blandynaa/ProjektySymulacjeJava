package zadanie1;

public class Funkcja1 implements IFunc {

    public double func(double x) {
        return 3 / x;
    }

    public double max(double a, double b) {
        double maxValue = func(a);
        for (double i = a; i <= b; i++) {
            double currentVal = func(i);
            if (currentVal > maxValue) {
                maxValue = currentVal;
            }
        }
        return maxValue;
    }
}
public class MyRandom {

    private int a; //parametr generatora stojący przy x
    private int x; //ziarno
    private int b; //parametr generatora dodawany do ax
    private int M; //liczba modulo
    private int xn;

    public MyRandom(int a, int x, int b, int m) {
        this.a = a;
        this.x = x;
        this.b = b;
        M = m;
    }

    //Zad 1.
    public int nextInt() {
        return x = ((a * x) + b) % M;
    }

    public double nextDouble() {
        return (double)nextInt() / M;
    }

    public double nextDouble(double low, double high) {
        return low + (high - low) * nextDouble();
    }

    public double exponential(double lambda) {
        return -(Math.log(1 - nextDouble()) / lambda);
    }

    //Zad 2.
    public double dyskret(double[] xx, double[] p) {
        double result = 0;
        double summedNumbers = 0;
        double xk = nextDouble();

        for (int i = 0; i < xx.length; i++) {
            summedNumbers = summedNumbers + p[i];
            if (summedNumbers > xk) {
                result = xx[i];
                break;
            }
        }
        return result;
    }
}

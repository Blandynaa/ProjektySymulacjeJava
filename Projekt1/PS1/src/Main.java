import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception{
        MyRandom myRandom = new MyRandom(97, 0, 11, 100);
        Random random = new Random();

        int numberOfRepetition = 1000;
        double nextIntAVG = 0;
        double nextDoubleAVG = 0;
        double nextDoublelowhighAVG = 0;
        double exponentialLambdaAVG = 0;
        double randomNumber = 0;

        for (int i = 0; i < numberOfRepetition; i++) {
            nextIntAVG += myRandom.nextInt();
            nextDoubleAVG += myRandom.nextDouble();
            nextDoublelowhighAVG += myRandom.nextDouble(0, 20);
            exponentialLambdaAVG += myRandom.exponential(10);
            randomNumber += random.nextInt(100);
        }

        nextIntAVG = nextIntAVG / numberOfRepetition;
        nextDoubleAVG = nextDoubleAVG / numberOfRepetition;
        nextDoublelowhighAVG = nextDoublelowhighAVG / numberOfRepetition;
        exponentialLambdaAVG = exponentialLambdaAVG / numberOfRepetition;
        randomNumber = randomNumber / numberOfRepetition;

        System.out.println("Wartości 1. iteracji:");
        System.out.println("nextInt(): " + myRandom.nextInt());
        System.out.println("nextDouble(): " + myRandom.nextDouble());
        System.out.println("nextDouble(double low, double high): " + myRandom.nextDouble(0, 20));
        System.out.println("exponential(double lambda): " + myRandom.exponential(10));
        System.out.println();
        System.out.println("Wartości średnie generatorów:");
        System.out.println("nextInt(): " + nextIntAVG);
        System.out.println("nextDouble(): " + nextDoubleAVG);
        System.out.println("nextDouble(double low, double high): " + nextDoublelowhighAVG);
        System.out.println("exponential(double lambda): " + exponentialLambdaAVG);
        System.out.println("Random.nextInt(int M): " + randomNumber);


        double[] xx = {-2, -1, 0, 1, 2, 3, 4};
        double[] p = {0.1, 0.15, 0.05, 0.25, 0.2, 0.15, 0.1};

        for (int i = 1; i < 7; i++) {
            if(xx[i-1]>=xx[i]) throw new Exception("Wartość xx[i] nie może być mniejsza od wartości xx[i-1]!");
        }

        double psum = 0;
        for (int i = 0; i < 7; i++) {
            if(p[i]<=0) throw new Exception("Prawdopodobieństwo nie może przyjmować wartości ujemnych!");
            psum = psum + p[i];
        }
        if(psum!=1) throw new Exception("Suma prawdopodobieństw musi wynosić 1!");

        double dyskretAVG = 0;
        double expectedValue = 0;


        for (int i = 0; i < 7; i++) {
            expectedValue = expectedValue + xx[i] * p[i];
        }

        for (int i = 0; i < numberOfRepetition; i++) {
            dyskretAVG += myRandom.dyskret(xx, p);
        }

        System.out.println();
        System.out.println("dyskret(double[] xx, double[] p):");
        System.out.println("Wartość średnia: " + dyskretAVG / numberOfRepetition);
        System.out.println("Wartość oczekiwana: " + expectedValue);
    }
}
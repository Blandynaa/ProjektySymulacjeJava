package zadanie1;

public class Main {
    public static void main(String[] args) {

        IFunc funkcja1 = new Funkcja1();
        IFunc funkcja2 = new Funkcja2();
        double result;
        double validFunkcja1 = 3;
        double validFunkcja2 = 1.145;

        for (int i=10; i<=100000; i=i*10) {
            Calka calka = new Calka();
            result = calka.calculate(1,Math.exp(1), funkcja1, i);
            System.out.println("Funkcja1 (n=" + i +"): " + result + "   Blad bezwzgledny (od wartosci " + validFunkcja1 + ": " + Math.abs(validFunkcja1-result));
        }

        System.out.println();

        for (int i=10; i<=100000; i=i*10) {
            Calka calka = new Calka();
            result = calka.calculate(1,Math.exp(1), funkcja2, i);
            System.out.println("Funkcja2 (n=" + i +"): " + result + "   Blad bezwzgledny (od wartosci " + validFunkcja2 + ": " + Math.abs(validFunkcja2-result));
        }
    }
}
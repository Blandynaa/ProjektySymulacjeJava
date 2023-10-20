package zadanie2;

public class Stanowisko {
    double Tk; //chwila pojawienia się k-tego interesanta
    double To; // sredni czas obslugi stanowiska

    public Stanowisko(double to) {
        To = to;
    }

    public void Zajecie(double simTime) {
        Tk = To + simTime;
    }

    public double CzasZajetosci() {
        return Tk;
    }
}

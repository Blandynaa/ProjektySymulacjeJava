public class Pojazd {
    public int ID;
    public double predkosc1, predkosc2, parkowanie;
    public double przebytaDroga;
    double czasPojawienia;
    double poprzedniKrok;
    double pozycja;
    boolean powrot;
    double czasJazdy;

    public Pojazd(int ID, double predkosc1, double predkosc2, double parkowanie, double czasPojawienia, double poprzedniKrok){
        this.ID = ID;
        this.predkosc1 = predkosc1;
        this.predkosc2 = predkosc2;
        this.parkowanie = parkowanie;
        this.przebytaDroga = 0;
        this.czasPojawienia = czasPojawienia;
        this.poprzedniKrok = poprzedniKrok;
        pozycja = 0;
        powrot = false;
        czasJazdy = 0;
    }
}

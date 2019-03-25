package nl.sogyo.mancala;

public class Bakje extends Plek {


    Bakje(int aantalbakjes) {
        int aantalgemaaktebakjes = 1;
        mijnspeler = new Speler();
        this.setAantalstenen(4);
        volgendeplek = new Bakje(aantalbakjes, aantalgemaaktebakjes, mijnspeler, this);
    }


    private Bakje(int aantalbakjes, int aantalgemaaktebakjes, Speler mijnspeler, Bakje bakje0) {
        aantalgemaaktebakjes = aantalgemaaktebakjes + 1;
        this.setAantalstenen(4);
        this.mijnspeler = mijnspeler;
        if (aantalgemaaktebakjes < aantalbakjes) {
            volgendeplek = new Bakje(aantalbakjes, aantalgemaaktebakjes, mijnspeler, bakje0);
        } else if (aantalgemaaktebakjes == aantalbakjes) {
            volgendeplek = new Kalaha(aantalgemaaktebakjes, mijnspeler, bakje0);
        }
    }


    Bakje(int aantaltemakenbakjes, Speler mijnspeler, Bakje bakje0) {
        aantaltemakenbakjes--;
        this.setAantalstenen(4);
        this.mijnspeler = mijnspeler;
        if (aantaltemakenbakjes > 0) {
            volgendeplek = new Bakje(aantaltemakenbakjes, mijnspeler, bakje0);
        } else if (aantaltemakenbakjes == 0) {
            volgendeplek = new Kalaha(mijnspeler, bakje0);
        }
    }
}

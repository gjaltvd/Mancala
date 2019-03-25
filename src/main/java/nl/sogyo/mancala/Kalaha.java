package nl.sogyo.mancala;

public class Kalaha extends Plek {

    //System.out.println("something else entirely again test number something");
    Kalaha(int aantalgemaaktebakjes, Speler mijnspeler, Bakje bakje0) {
        this.aantalstenen = 0;
        this.mijnspeler = mijnspeler;
        volgendeplek = new Bakje(aantalgemaaktebakjes, mijnspeler.getTegenstander(), bakje0);
    }


    Kalaha(Speler mijnspeler, Bakje bakje0) {
        this.aantalstenen = 0;
        this.mijnspeler = mijnspeler;
        volgendeplek = bakje0;
    }

    public void doeZetEnGeefDoor() {

    }

    protected void doeZetEnGeefDoor(int aantalDoorTeGevenStenen) {
        if (this.getSpeler().getBeurt()) {
            aantalDoorTeGevenStenen--;
            this.aantalstenen++;
        }

        if (aantalDoorTeGevenStenen == 0) {
            zoekStartpuntOpruimenEnGaOpruimen(isSpelTenEinde());
        } else {
            this.volgendeplek.doeZetEnGeefDoor(aantalDoorTeGevenStenen);
        }
    }


    protected int geefHitDoorTotKalaha(int count) {
        count++;
        this.aantalstenen++;
        volgendeplek.gaStelen(count);
        return count;
    }

    void geefBuitDoorTotMijnKalaha(int buit) {
        if (this.mijnspeler.getBeurt()) {
            this.aantalstenen = this.aantalstenen + buit;
        } else {
            volgendeplek.geefBuitDoorTotMijnKalaha(buit);
        }
    }

    protected boolean zijnBakjesLeeg() {
        return true;
    }

    protected boolean isSpelTenEinde() {
        if (this.mijnspeler.getBeurt()) {
            return giveSpecificPlek(giveKalahaTegenstander()).getVolgendeplek().zijnBakjesLeeg();
        } else {
            return volgendeplek.zijnBakjesLeeg();
        }
    }

    protected void opruimenNaarKalahaTegenstander(int aantaldoortegevenstenen) {
        this.aantalstenen = this.aantalstenen + aantaldoortegevenstenen;
    }

    int giveKalahaEigen() {
        int count = 0;
        if (mijnspeler.getBeurt()) {
            return count;
        } else {
            return volgendeplek.giveKalahaEigen(count);
        }
    }

    int giveKalahaEigen(int count) {
        count++;
        if (mijnspeler.getBeurt()) {
            return count;
        } else {
            return volgendeplek.giveKalahaEigen(count);
        }
    }


    int giveKalahaTegenstander() {
        int count = 0;
        if (!this.getSpeler().getBeurt()) {
            return count;
        } else {
            return volgendeplek.giveKalahaTegenstander(count);
        }
    }

    int giveKalahaTegenstander(int count) {
        count++;
        if (!mijnspeler.getBeurt()) {
            return count;
        } else {
            return volgendeplek.giveKalahaTegenstander(count);
        }
    }

}

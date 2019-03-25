package nl.sogyo.mancala;

import java.util.LinkedList;

public class Plek {
    int aantalstenen;
    protected Speler mijnspeler;
    protected Plek volgendeplek;

    public int getAantalstenen() {
        return this.aantalstenen;
    }

    protected void setAantalstenen(int nieuwaantalstenen) {
        this.aantalstenen = nieuwaantalstenen;
    }

    public Speler getSpeler() {
        return this.mijnspeler;
    }

    public Plek getVolgendeplek() {
        return this.volgendeplek;
    }

    public void loadGame(LinkedList<Integer> savedpositions, boolean beurtspeler1) {
        if (beurtspeler1 != this.mijnspeler.getBeurt()) {
            this.mijnspeler.wisselBeurtenBeideSpelers();
        }

        this.aantalstenen = savedpositions.removeFirst();
        if (!savedpositions.isEmpty()) {
            this.volgendeplek.loadGame(savedpositions);
        }
    }

    void loadGame(LinkedList<Integer> savedpositions) {
        this.aantalstenen = savedpositions.removeFirst();
        if (!savedpositions.isEmpty()) {
            volgendeplek.loadGame(savedpositions);
        }
    }


    int giveKalahaEigen() {
        int count = 0;
        return volgendeplek.giveKalahaEigen(count);
    }

    int giveKalahaEigen(int count) {
        count++;
        return volgendeplek.giveKalahaEigen(count);
    }


    int giveKalahaTegenstander() {
        int count = 0;
        return volgendeplek.giveKalahaTegenstander(count);
    }

    int giveKalahaTegenstander(int count) {
        count++;
        return volgendeplek.giveKalahaTegenstander(count);
    }


    Plek giveSpecificPlek(int setoff) {
        if (setoff == 0) {
            return this;
        } else {
            setoff--;
            return volgendeplek.giveSpecificPlek(setoff);
        }
    }


    public void doeZetEnGeefDoor() {
        if (this.aantalstenen != 0 && this.mijnspeler.getBeurt()) {
            int aantalDoorTeGevenStenen = this.aantalstenen;
            this.aantalstenen = 0;
            volgendeplek.doeZetEnGeefDoor(aantalDoorTeGevenStenen);
        }
    }

    protected void doeZetEnGeefDoor(int aantalDoorTeGevenStenen) {
        aantalDoorTeGevenStenen--;
        this.aantalstenen++;
        if (aantalDoorTeGevenStenen == 0) {
            controleerHit(this.aantalstenen);
            mijnspeler.wisselBeurtenBeideSpelers();
            zoekStartpuntOpruimenEnGaOpruimen(isSpelTenEinde());
        } else {
            volgendeplek.doeZetEnGeefDoor(aantalDoorTeGevenStenen);
        }
    }


    protected int geefHitDoorTotKalaha() {
        int count = 0;
        count = volgendeplek.geefHitDoorTotKalaha(count);
        this.aantalstenen = 0;
        return count;
    }

    protected int geefHitDoorTotKalaha(int count) {
        count++;
        count = volgendeplek.geefHitDoorTotKalaha(count);
        return count;
    }


    protected void controleerHit(int aantalstenen) {
        if (aantalstenen == 1 && this.getSpeler().getBeurt()) {
            geefHitDoorTotKalaha();
        }
    }


    void geefBuitDoorTotMijnKalaha(int buit) {
        volgendeplek.geefBuitDoorTotMijnKalaha(buit);
    }


    protected void gaStelen(int aantalstappentotbuitvakje) {
        aantalstappentotbuitvakje--;
        if (aantalstappentotbuitvakje == 0) {
            geefBuitDoorTotMijnKalaha(this.aantalstenen);
            this.aantalstenen = 0;
        } else {
            volgendeplek.gaStelen(aantalstappentotbuitvakje);
        }

    }


    protected boolean isSpelTenEinde() {
        return giveSpecificPlek(this.giveKalahaTegenstander()).isSpelTenEinde();
    }


    protected boolean zijnBakjesLeeg() {
        if (this.aantalstenen == 0) {
            return volgendeplek.zijnBakjesLeeg();
        } else {
            return false;
        }
    }


    protected void zoekStartpuntOpruimenEnGaOpruimen(boolean spelisafgelopen) {
        if (spelisafgelopen) {
            giveSpecificPlek(this.giveKalahaEigen()).volgendeplek.opruimenNaarKalahaTegenstander();
        }
    }


    protected void opruimenNaarKalahaTegenstander() {
        int aantaldoortegevenstenen = this.aantalstenen;
        this.aantalstenen = 0;
        volgendeplek.opruimenNaarKalahaTegenstander(aantaldoortegevenstenen);
    }

    protected void opruimenNaarKalahaTegenstander(int aantaldoortegevenstenen) {
        aantaldoortegevenstenen = aantaldoortegevenstenen + this.aantalstenen;
        this.aantalstenen = 0;
        volgendeplek.opruimenNaarKalahaTegenstander(aantaldoortegevenstenen);
    }
}

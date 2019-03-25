package nl.sogyo.mancala;

class Speler {
    private Speler tegenstander;
    private boolean beurt;

    Speler() {
        this.beurt = true;
        tegenstander = new Speler(this);
    }

    private Speler(Speler speler1) {
        this.tegenstander = speler1;
        this.beurt = false;
    }

    public Speler getTegenstander() {
        return this.tegenstander;
    }

    public boolean getBeurt() {
        return beurt;
    }

    void wisselBeurtenBeideSpelers() {
        this.beurt = !beurt;
        tegenstander.wisselEigenBeurt();
    }

    void wisselEigenBeurt() {
        this.beurt = !beurt;
    }
}

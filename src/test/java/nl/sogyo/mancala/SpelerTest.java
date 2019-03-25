package nl.sogyo.mancala;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SpelerTest {
    Bakje bakje = null;

    @Before
    public void setUp() {
        bakje = new Bakje(6);
    }

    @After
    public void tearDown() {
        bakje = null;
    }

    @Test
    public void aanmaakSpeler() {
        assertNotNull(bakje.getSpeler());
    }

    @Test
    public void aanmaakTegenstander() {
        assertNotNull(bakje.getSpeler().getTegenstander());
    }

    @Test
    public void koppelingSpelers() {
        assertTrue(bakje.getSpeler() == bakje.getSpeler().getTegenstander().getTegenstander());
    }

    @Test
    public void checkBeurt() {
        assertTrue(bakje.getSpeler().getBeurt() != bakje.getSpeler().getTegenstander().getBeurt());
    }

    @Test
    public void checkBeurtWisselMethode() {
        boolean beurtmijnspelervoor = bakje.getSpeler().getBeurt();
        boolean beurttegenstandervoor = bakje.getSpeler().getTegenstander().getBeurt();
        bakje.getSpeler().wisselBeurtenBeideSpelers();
        assertTrue(beurtmijnspelervoor != bakje.getSpeler().getBeurt());
        assertTrue(beurttegenstandervoor != bakje.getSpeler().getTegenstander().getBeurt());
    }

    @Test
    public void checkEnkelEigenBeurtWisselMethode() {
        bakje.doeZetEnGeefDoor();
        boolean beurtmijnspelervoor = bakje.getSpeler().getBeurt();
        boolean beurttegenstandervoor = bakje.getSpeler().getTegenstander().getBeurt();
        bakje.getSpeler().wisselEigenBeurt();
        assertTrue(beurtmijnspelervoor != bakje.getSpeler().getBeurt());
        assertTrue(beurttegenstandervoor == bakje.getSpeler().getTegenstander().getBeurt());
    }

    @Test
    public void checkBeurtWisselNaZetEindigendInGevuldBakje() {
        boolean beurtmijnspelervoor = bakje.getSpeler().getBeurt();
        boolean beurttegenstandervoor = bakje.getSpeler().getTegenstander().getBeurt();
        bakje.doeZetEnGeefDoor();
        assertTrue(beurtmijnspelervoor != bakje.getSpeler().getBeurt());
        assertTrue(beurttegenstandervoor != bakje.getSpeler().getTegenstander().getBeurt());
    }

    @Test
    public void checkBeurtWisselNaZetEindigendInLeegBakje() {
        bakje.giveSpecificPlek(4).setAantalstenen(0);
        boolean beurtmijnspelervoor = bakje.getSpeler().getBeurt();
        boolean beurttegenstandervoor = bakje.getSpeler().getTegenstander().getBeurt();
        bakje.doeZetEnGeefDoor();
        assertTrue(beurtmijnspelervoor != bakje.getSpeler().getBeurt());
        assertTrue(beurttegenstandervoor != bakje.getSpeler().getTegenstander().getBeurt());
    }

    @Test
    public void checkBeurtWisselNaZetEindigendInEigenKalaha() {
        bakje.setAantalstenen(6);
        boolean beurtmijnspelervoor = bakje.getSpeler().getBeurt();
        boolean beurttegenstandervoor = bakje.getSpeler().getTegenstander().getBeurt();
        bakje.doeZetEnGeefDoor();
        assertTrue(beurtmijnspelervoor == bakje.getSpeler().getBeurt());
        assertTrue(beurttegenstandervoor == bakje.getSpeler().getTegenstander().getBeurt());
    }

    @Test
    public void checkZetDoenMetKalahaBeurtWissel() {
        boolean beurtmijnspelervoor = bakje.getSpeler().getBeurt();
        boolean beurttegenstandervoor = bakje.getSpeler().getTegenstander().getBeurt();
        bakje.giveSpecificPlek(6).doeZetEnGeefDoor();
        assertTrue(beurtmijnspelervoor == bakje.getSpeler().getBeurt());
        assertTrue(beurttegenstandervoor == bakje.getSpeler().getTegenstander().getBeurt());
    }

    @Test
    public void checkProberenZetOpLeegBakje() {
        bakje.setAantalstenen(0);
        boolean beurtmijnspelervoor = bakje.getSpeler().getBeurt();
        boolean beurttegenstandervoor = bakje.getSpeler().getTegenstander().getBeurt();
        bakje.doeZetEnGeefDoor();
        assertTrue(beurtmijnspelervoor == bakje.getSpeler().getBeurt());
        assertTrue(beurttegenstandervoor == bakje.getSpeler().getTegenstander().getBeurt());
    }

    @Test
    public void checkDoeZetMetBakjeTegenstander() {
        boolean beurtmijnspelervoor = bakje.getSpeler().getBeurt();
        boolean beurttegenstandervoor = bakje.getSpeler().getTegenstander().getBeurt();
        bakje.giveSpecificPlek(7).doeZetEnGeefDoor();
        assertTrue(beurtmijnspelervoor == bakje.getSpeler().getBeurt());
        assertTrue(beurttegenstandervoor == bakje.getSpeler().getTegenstander().getBeurt());
    }

    @Test
    public void checkLoadSpelSpelers() {
        boolean beurtmijnspelervoor = bakje.getSpeler().getBeurt();
        boolean beurttegenstandervoor = bakje.getSpeler().getTegenstander().getBeurt();
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(1, 0, 2, 3, 4, 1, 0, 0, 0, 0, 0, 1, 0, 0));
        boolean beurtspeler1 = false;
        bakje.loadGame(savedpositions, beurtspeler1);
        assertTrue(beurtmijnspelervoor != bakje.getSpeler().getBeurt());
        assertTrue(beurttegenstandervoor != bakje.getSpeler().getTegenstander().getBeurt());
    }
}

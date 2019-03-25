package nl.sogyo.mancala;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class MancalaTest {
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
    public void check13StappenBakje0TotKalahaTegenstander() {
        assertNotEquals(12, bakje.giveKalahaTegenstander());
        assertEquals(13, bakje.giveKalahaTegenstander());
        assertNotEquals(14, bakje.giveKalahaTegenstander());
    }

    @Test
    public void check14StappenBakje0TotZichzelf() {
        assertNotEquals(bakje, bakje.giveSpecificPlek(7));
        assertNotEquals(bakje, bakje.giveSpecificPlek(13));
        assertEquals(bakje, bakje.giveSpecificPlek(14));
        assertNotEquals(bakje, bakje.giveSpecificPlek(15));
    }

    @Test
    public void check7StappenTussenKalahas() {
        assertEquals(7, bakje.giveSpecificPlek(bakje.giveKalahaEigen()).giveKalahaTegenstander());
    }

    @Test
    public void check0StappenBinnenKalahaEigen() {
        assertEquals(0, bakje.giveSpecificPlek(bakje.giveKalahaEigen()).giveKalahaEigen());
    }

    @Test
    public void check0StappenBinnenKalahaTegenStander() {
        assertEquals(0, bakje.giveSpecificPlek(bakje.giveKalahaTegenstander()).giveKalahaTegenstander());
    }

    @Test
    public void checkSwitchKalahaTegenstanderBijBeurtWissel() {
        int stappentotkalahategenstandervoorbeurtwissel = bakje.giveKalahaTegenstander();
        bakje.doeZetEnGeefDoor();
        int stappentotkalahategenstandernabeurtwissel = bakje.giveKalahaTegenstander();
        assertNotEquals(stappentotkalahategenstandervoorbeurtwissel, stappentotkalahategenstandernabeurtwissel);
    }

    @Test
    public void checkSpecificPlek() {
        assertTrue(bakje == bakje.giveSpecificPlek(0));
        assertTrue(bakje.getVolgendeplek() == bakje.giveSpecificPlek(1));
        assertTrue(bakje == bakje.giveSpecificPlek(14));
        assertTrue(bakje != bakje.giveSpecificPlek(13));
        assertTrue(bakje != bakje.giveSpecificPlek(1));
    }

    @Test
    public void checkCountHitDoorgevenTotEigenKalaha() {
        assertNotEquals(5, bakje.geefHitDoorTotKalaha());
        assertEquals(6, bakje.geefHitDoorTotKalaha());
        assertNotEquals(7, bakje.geefHitDoorTotKalaha());
    }

    @Test
    public void checkIsSpelBeeindigdNietWaar() {
        assertFalse(bakje.isSpelTenEinde());
    }

    @Test
    public void checkIsSpelBeeindigdWaar() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        assertTrue(bakje.isSpelTenEinde());
    }

    @Test
    public void checkIsSpelBeeindigdNaHitNietWaarVanwegeWisselBeurt() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        bakje.doeZetEnGeefDoor();
        assertFalse(bakje.isSpelTenEinde());
    }

    @Test
    public void checkIsSpelBeeindigdNaEindigenInKalaha() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 1, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        bakje.giveSpecificPlek(5).doeZetEnGeefDoor();
        assertTrue(bakje.giveSpecificPlek(6).isSpelTenEinde());
    }

    @Test
    public void checkIsSpelBeeindigdBijBeurtTegenStanderNaEindigenInKalahaBijEerstGestolen() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        bakje.doeZetEnGeefDoor();
        assertFalse(bakje.giveSpecificPlek(2).isSpelTenEinde());
        bakje.giveSpecificPlek(12).doeZetEnGeefDoor();
        assertTrue(bakje.giveSpecificPlek(13).isSpelTenEinde());
    }

    @Test
    public void checkIsSpelBeeindigdNaEindigenInKalahaComplex() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 4, 4, 4, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        bakje.giveSpecificPlek(5).doeZetEnGeefDoor();
        assertTrue(bakje.giveSpecificPlek(6).isSpelTenEinde());
    }
}

package nl.sogyo.mancala;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class BakjeTest {
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
    public void aantalStenenBegin() {
        assertTrue((4 == bakje.getAantalstenen()));
    }

    @Test
    public void koppelingVolgendePlek() {
        assertNotNull(bakje.getVolgendeplek());
    }

    @Test
    public void checkBakje5NaarKalaha() {
        assertTrue((bakje.giveSpecificPlek(5).getVolgendeplek() instanceof Kalaha));
    }

    @Test
    public void checkBakje5Mijnpeler() {
        assertTrue((bakje.giveSpecificPlek(5).getSpeler() == bakje.getSpeler()));
    }

    @Test
    public void checkBakje12NaarKalaha() {
        assertTrue((bakje.giveSpecificPlek(12).getVolgendeplek() instanceof Kalaha));
    }

    @Test
    public void checkBakje12Tegenstander() {
        assertTrue((bakje.giveSpecificPlek(12).getSpeler() == bakje.getSpeler().getTegenstander()));
    }


    @Test
    public void checkZetEnGeefDoorStartpositie() {
        int volgendebakjeaantalstenenvoor = bakje.giveSpecificPlek(1).getAantalstenen();
        int vierdebakjeaantalstenenvoor = bakje.giveSpecificPlek(4).getAantalstenen();
        int vijfdebakjeaantalstenenvoor = bakje.giveSpecificPlek(5).getAantalstenen();
        bakje.doeZetEnGeefDoor();
        assertEquals(0, bakje.getAantalstenen());
        assertEquals((volgendebakjeaantalstenenvoor + 1), bakje.giveSpecificPlek(1).getAantalstenen());
        assertEquals((vierdebakjeaantalstenenvoor + 1), bakje.giveSpecificPlek(4).getAantalstenen());
        assertEquals((vijfdebakjeaantalstenenvoor), bakje.giveSpecificPlek(5).getAantalstenen());
    }

    @Test
    public void checkOverkantZetBakjeTweeNaarKalaha() {
        int zevendebakjeaantalstenenvoor = bakje.giveSpecificPlek(7).getAantalstenen();
        bakje.giveSpecificPlek(2).doeZetEnGeefDoor();
        assertEquals((zevendebakjeaantalstenenvoor), bakje.giveSpecificPlek(7).getAantalstenen());
    }

    @Test
    public void checkBakje7ZetNaBakje0() {
        int twaalfdebakjeaantalstenenvoor = bakje.giveSpecificPlek(12).getAantalstenen();
        bakje.giveSpecificPlek(0).doeZetEnGeefDoor();
        bakje.giveSpecificPlek(7).doeZetEnGeefDoor();
        assertEquals((twaalfdebakjeaantalstenenvoor), bakje.giveSpecificPlek(12).getAantalstenen());
    }


    @Test
    public void checkDoeZetWaarbijInhoudBakjeTweeKeerRondgaat() {
        int nieuwaantalstenen = 14;
        bakje.setAantalstenen(nieuwaantalstenen);
        int volgendebakjeaantalstenenvoor = bakje.giveSpecificPlek(1).getAantalstenen();
        int tweedebakjeaantalstenenvoor = bakje.giveSpecificPlek(2).getAantalstenen();
        bakje.doeZetEnGeefDoor();
        assertEquals(1, bakje.getAantalstenen());
        assertEquals((volgendebakjeaantalstenenvoor + 2), bakje.giveSpecificPlek(1).getAantalstenen());
        assertEquals((tweedebakjeaantalstenenvoor + 1), bakje.giveSpecificPlek(4).getAantalstenen());
    }

    @Test
    public void checkDoeZetMetBakjeTegenstander() {
        int bakje7aantalstenenvoor = bakje.giveSpecificPlek(7).getAantalstenen();
        bakje.giveSpecificPlek(7).doeZetEnGeefDoor();
        assertEquals(bakje7aantalstenenvoor, bakje.giveSpecificPlek(7).getAantalstenen());
    }


    @Test
    public void checkBuitDoorGevenBijBakje() {
        int eigenbakjestenenvoor = bakje.giveSpecificPlek(3).getAantalstenen();
        int tegenstanderbakjestenenvoor = bakje.giveSpecificPlek(9).getAantalstenen();
        bakje.giveSpecificPlek(3).geefBuitDoorTotMijnKalaha(4);
        assertEquals(eigenbakjestenenvoor, bakje.giveSpecificPlek(3).getAantalstenen());
        assertEquals(tegenstanderbakjestenenvoor, bakje.giveSpecificPlek(9).getAantalstenen());

    }

    @Test
    public void checkLegenHitSteenBijBakjeNaHit() {
        bakje.giveSpecificPlek(4).setAantalstenen(0);
        bakje.doeZetEnGeefDoor();
        assertEquals(0, bakje.giveSpecificPlek(4).getAantalstenen());
    }

    @Test
    public void checkGaStelenVanafEerderBakje() {
        int bakje7stenenvoor = bakje.giveSpecificPlek(7).getAantalstenen();
        bakje.giveSpecificPlek(7).gaStelen(2);
        assertEquals(0, bakje.giveSpecificPlek(8).getAantalstenen());
        assertEquals(bakje7stenenvoor, bakje.giveSpecificPlek(7).getAantalstenen());
    }

    @Test
    public void checkGaStelenVanafKalaha() {
        int bakje7stenenvoor = bakje.giveSpecificPlek(7).getAantalstenen();
        bakje.giveSpecificPlek(6).geefHitDoorTotKalaha(1);
        assertEquals(0, bakje.giveSpecificPlek(8).getAantalstenen());
        assertEquals(bakje7stenenvoor, bakje.giveSpecificPlek(7).getAantalstenen());
    }

    @Test
    public void checkGeenHit() {
        int bakje4stenenvoor = bakje.giveSpecificPlek(4).getAantalstenen();
        bakje.doeZetEnGeefDoor();
        assertEquals(bakje4stenenvoor + 1, bakje.giveSpecificPlek(4).getAantalstenen());
    }

    @Test
    public void checkBijHitVanuitZetMetVierStenenBakje0VoorTeStelenBakjeEnOmringend() {
        bakje.giveSpecificPlek(4).setAantalstenen(0);
        int bakje7stenenvoor = bakje.giveSpecificPlek(7).getAantalstenen();
        int bakje9stenenvoor = bakje.giveSpecificPlek(9).getAantalstenen();
        bakje.doeZetEnGeefDoor();
        assertEquals(0, bakje.giveSpecificPlek(8).getAantalstenen());
        assertEquals(bakje7stenenvoor, bakje.giveSpecificPlek(7).getAantalstenen());
        assertEquals(bakje9stenenvoor, bakje.giveSpecificPlek(9).getAantalstenen());
    }

    @Test
    public void checkBakjesLeegIsFalse() {
        bakje.doeZetEnGeefDoor();
        assertFalse(bakje.zijnBakjesLeeg());
    }

    @Test
    public void checkLoadSpelBakjes() {
        int bakje3stenenvoor = bakje.giveSpecificPlek(3).getAantalstenen();
        int bakje7stenenvoor = bakje.giveSpecificPlek(7).getAantalstenen();
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(1, 0, 2, 3, 4, 1, 5, 0, 0, 0, 1, 0));
        boolean beurtspeler1 = false;
        bakje.loadGame(savedpositions, beurtspeler1);
        assertNotEquals(bakje3stenenvoor, bakje.giveSpecificPlek(3).getAantalstenen());
        assertNotEquals(bakje7stenenvoor, bakje.giveSpecificPlek(7).getAantalstenen());
    }

    @Test
    public void checkBakjesLeegIsTrue() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        assertTrue(bakje.zijnBakjesLeeg());
    }

    @Test
    public void checkOpruimenBakjesDirect() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(1, 1, 1, 4, 4, 4, 20, 0, 0, 0, 0, 0, 0, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        bakje.opruimenNaarKalahaTegenstander();
        assertEquals(0, bakje.getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(1).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(2).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(3).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(4).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(5).getAantalstenen());
    }

    @Test
    public void checkOpruimenBakjesNaZetEindigendInKalaha() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 4, 4, 4, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        bakje.giveSpecificPlek(5).doeZetEnGeefDoor();
        assertEquals(0, bakje.getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(1).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(2).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(3).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(4).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(5).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(7).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(8).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(9).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(10).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(11).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(12).getAantalstenen());
    }

    @Test
    public void checkOpruimenBakjesNaZetEindigendMetStelen() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(1, 0, 2, 3, 4, 1, 0, 0, 0, 0, 0, 1, 0, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        bakje.doeZetEnGeefDoor();
        assertEquals(0, bakje.getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(1).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(2).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(3).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(4).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(5).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(7).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(8).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(9).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(10).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(11).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(12).getAantalstenen());
    }
}

package nl.sogyo.mancala;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KalahaTest {
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
        assertEquals(0, bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen());
    }

    @Test
    public void koppelingVolgendePlekBakje() {
        assertTrue(bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getVolgendeplek() instanceof Bakje);
    }

    @Test
    public void checkBakje6EigenKalaha() {
        assertTrue((bakje.giveSpecificPlek(6).getSpeler() == bakje.getSpeler()));
    }

    @Test
    public void checkBakje6KalahaNaarBakje() {
        assertTrue((bakje.giveSpecificPlek(6).getVolgendeplek() instanceof Bakje));
    }

    @Test
    public void checkBakje13KalahaNaarBakje() {
        assertTrue((bakje.giveSpecificPlek(13).getVolgendeplek() instanceof Bakje));
    }

    @Test
    public void checkBakje13TegenstanderKalaha() {
        assertTrue((bakje.giveSpecificPlek(13).getSpeler() == bakje.getSpeler().getTegenstander()));
    }

    @Test
    public void checkCountHitDoorgevenTotEigenKalaha() {
        assertEquals(6, bakje.giveSpecificPlek(6).geefHitDoorTotKalaha(5));
    }

    @Test
    public void checkSteenOptellenHitEnBuitDoorgevenTotEigenKalaha() {
        int eigenkalahastenenvoor = bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen();
        bakje.geefHitDoorTotKalaha();
        assertEquals((eigenkalahastenenvoor + 5), bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen());
    }

    @Test
    public void checkDoeZetWaarbijInhoudBakjeTweeKeerRondgaat() {
        bakje.setAantalstenen(14);
        int eigenkalahastenenvoor = bakje.giveSpecificPlek(6).getAantalstenen();
        int tegenstanderkalahastenenenvoor = bakje.giveSpecificPlek(13).getAantalstenen();
        bakje.doeZetEnGeefDoor();
        assertEquals((eigenkalahastenenvoor + 1), bakje.giveSpecificPlek(6).getAantalstenen());
        assertEquals((tegenstanderkalahastenenenvoor), bakje.giveSpecificPlek(13).getAantalstenen());
    }

    @Test
    public void checkBuitDoorGevenBijEigenKalaha() {
        int eigenkalahastenenvoor = bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen();
        bakje.giveSpecificPlek(bakje.giveKalahaEigen()).geefBuitDoorTotMijnKalaha(4);
        assertEquals(eigenkalahastenenvoor + 4, bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen());
    }

    @Test
    public void checkBuitDoorGevenBijTegenstanderKalaha() {
        int tegenstanderkalahastenenvoor = bakje.giveSpecificPlek(bakje.giveKalahaTegenstander()).getAantalstenen();
        bakje.giveSpecificPlek(bakje.giveKalahaTegenstander()).geefBuitDoorTotMijnKalaha(4);
        assertEquals(tegenstanderkalahastenenvoor, bakje.giveSpecificPlek(bakje.giveKalahaTegenstander()).getAantalstenen());
    }

    @Test
    public void checkCountHitBinnenGekregenGaStelenVanafKalahaEnOntvangBuitEigenKalaha() {
        int eigenkalahastenenvoor = bakje.giveSpecificPlek(6).getAantalstenen();
        int testelenbakjeaantalstenen = bakje.giveSpecificPlek(8).getAantalstenen();
        bakje.giveSpecificPlek(6).geefHitDoorTotKalaha(1);
        assertEquals(eigenkalahastenenvoor + testelenbakjeaantalstenen + 1, bakje.giveSpecificPlek(6).getAantalstenen());
    }

    @Test
    public void checkGeenHitGevolgEigenKalaha() {
        int eigenkalahastenenvoor = bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen();
        bakje.doeZetEnGeefDoor();
        assertEquals(eigenkalahastenenvoor, bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen());
    }

    @Test
    public void checkGeenHitGevolgTegenstanderKalaha() {
        int tegenstanderkalahastenenvoor = bakje.giveSpecificPlek(bakje.giveKalahaTegenstander()).getAantalstenen();
        bakje.doeZetEnGeefDoor();
        assertEquals(tegenstanderkalahastenenvoor, bakje.giveSpecificPlek(bakje.giveKalahaTegenstander()).getAantalstenen());
    }

    @Test
    public void checkBijHitVanuitZetMetVierStenenBakje0VoorEigenKalaha() {
        bakje.giveSpecificPlek(4).setAantalstenen(0);
        int eigenkalahastenenvoor = bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen();
        int testelenbakjeaantalstenen = bakje.giveSpecificPlek(8).getAantalstenen();
        bakje.doeZetEnGeefDoor();
        assertEquals(eigenkalahastenenvoor + testelenbakjeaantalstenen + 1, bakje.giveSpecificPlek(bakje.giveKalahaTegenstander()).getAantalstenen());
    }

    @Test
    public void checkBijHitVanuitZetMetVierStenenBakje0VoorTegenstanderKalaha() {
        bakje.giveSpecificPlek(4).setAantalstenen(0);
        int eigenkalahastenenvoor = bakje.giveSpecificPlek(bakje.giveKalahaTegenstander()).getAantalstenen();
        bakje.doeZetEnGeefDoor();
        assertEquals(eigenkalahastenenvoor, bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen());
    }

    @Test
    public void checkZetDoenMetKalahaEffectOpKalaha() {
        bakje.giveSpecificPlek(bakje.giveKalahaEigen()).setAantalstenen(1);
        bakje.giveSpecificPlek(bakje.giveKalahaEigen()).doeZetEnGeefDoor();
        assertEquals(1, bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen());
    }

    @Test
    public void checkOpruimenBakjesDirect() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(1, 1, 1, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        bakje.opruimenNaarKalahaTegenstander();
        assertEquals(15, bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(bakje.giveKalahaTegenstander()).getAantalstenen());

    }

    @Test
    public void checkOpruimenBakjesNaZetEindigendInKalaha() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 4, 4, 4, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        bakje.giveSpecificPlek(5).doeZetEnGeefDoor();
        assertEquals(1, bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen());
        assertEquals(15, bakje.giveSpecificPlek(bakje.giveKalahaTegenstander()).getAantalstenen());
    }

    @Test
    public void checkOpruimenBakjesNaZetEindigendMetStelen() {
        LinkedList<Integer> savedpositions = new LinkedList<Integer>(Arrays.asList(1, 0, 2, 3, 4, 1, 0, 0, 0, 0, 0, 1, 0, 0));
        boolean beurtspeler1 = true;
        bakje.loadGame(savedpositions, beurtspeler1);
        bakje.doeZetEnGeefDoor();
        assertEquals(12, bakje.giveSpecificPlek(bakje.giveKalahaTegenstander()).getAantalstenen());
        assertEquals(0, bakje.giveSpecificPlek(bakje.giveKalahaEigen()).getAantalstenen());
    }
}


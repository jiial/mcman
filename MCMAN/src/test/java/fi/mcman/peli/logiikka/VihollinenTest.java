package fi.mcman.peli.logiikka;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ljone
 * 
 * Testaa luokan Vihollinen toimintaa eri metodeilla ja syötteillä.
 * 
 */
public class VihollinenTest {

    private Peli peli;
    private Pelaaja kohde;
    private Vihollinen vihu;
    private Random arpoja;

    public VihollinenTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.arpoja = new Random();
        this.peli = new Peli();
        this.kohde = peli.getPelaaja();
        this.vihu = new Vihollinen("jorma", peli.getPelaaja().getX(), peli.getPelaaja().getY() - 2, this.peli);
        vihu.setKohde(kohde);
        vihu.setArpoja(arpoja);

    }

    @Test
    public void vihuLoytaaPelaajan() {
        vihu.liiku();
        vihu.liiku();
        assertTrue(kohde.osuuViholliseen(vihu));
    }

    @Test
    public void vihuLoytaaPelaajan2() {
        vihu.siirra(peli.getPelaaja().getX() - 5, peli.getPelaaja().getY());
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        assertTrue(kohde.osuuViholliseen(vihu));
    }

    @Test
    public void eiMeneUlosOikealta() {
        vihu.siirra(290, 290);
        vihu.setSuurinX(292);
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        assertEquals(292, vihu.getX());
    }
    
    @Test
    public void eiMeneUlosAlhaalta() {
        vihu.siirra(340, 370);
        vihu.setSuurinY(370);
        vihu.liiku();
        assertEquals(370, vihu.getY());
    }
    
    @Test
    public void eiMeneUlosVasemmalta() {
        vihu.siirra(310, 300);
        vihu.setPieninX(308);
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        assertEquals(308, vihu.getX());
    }
    
    @Test
    public void eiMeneUlosYlhaalta() {
        vihu.siirra(300, 310);
        vihu.setPieninY(308);
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        assertEquals(308, vihu.getY());
    }
    
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

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
        this.vihu = new Vihollinen("jorma", peli.getPelaaja().getX() - 1, peli.getPelaaja().getY() - 2, this.peli);
        vihu.setKohde(kohde);
        vihu.setArpoja(arpoja);
        vihu.setSuurinX(peli.getAlusta().getLeveys() - 1);
        vihu.setSuurinY(peli.getAlusta().getKorkeus() - 1);
        vihu.setPieninX(peli.getAlusta().getLeveys() - 10);
        vihu.setPieninY(peli.getAlusta().getKorkeus() - 10);

    }

    @Test
    public void vihuLoytaaPelaajan() {
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        assertTrue(kohde.osuukoViholliseen(vihu));
    }

    @Test
    public void vihuLoytaaPelaajan2() {
        vihu.siirra(peli.getAlusta().getLeveys() - 6, peli.getAlusta().getKorkeus() - 4);
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        assertTrue(kohde.osuukoViholliseen(vihu));
    }

    @Test
    public void eiMeneUlosOikealta() {
        Pelaaja p = new Pelaaja(peli);
        p.siirra(vihu.getX()+8, 0);
        vihu.setKohde(p);
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        assertEquals(peli.getAlusta().getLeveys()-1, vihu.getX());
    }
    
    @Test
    public void eiMeneUlosAlhaalta() {
        Pelaaja p = new Pelaaja(peli);
        p.siirra(0, vihu.getY()+7);
        vihu.setKohde(p);
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        vihu.liiku();
        assertEquals(peli.getAlusta().getKorkeus()-1, vihu.getY());
    }
    
    @Test
    public void eiMeneUlosVasemmalta() {
        Pelaaja p = new Pelaaja(peli);
        p.siirra(vihu.getX()-11, 0);
        vihu.setKohde(p);
        for (int i = 0; i < 15; i++) {
            vihu.liiku();
        }
        assertEquals(peli.getAlusta().getLeveys()-10, vihu.getX());
    }
    
    @Test
    public void eiMeneUlosYlhaalta() {
        Pelaaja p = new Pelaaja(peli);
        p.siirra(0, vihu.getY()-11);
        vihu.setKohde(p);
        for (int i = 0; i < 15; i++) {
            vihu.liiku();
        }
        assertEquals(peli.getAlusta().getKorkeus()-10, vihu.getY());
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

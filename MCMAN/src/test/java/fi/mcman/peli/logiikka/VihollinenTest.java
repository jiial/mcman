package fi.mcman.peli.logiikka;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa luokan Vihollinen toimintaa eri metodeilla ja syötteillä.
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
        this.vihu = new Vihollinen("jorma", peli.getPelaaja().getX(), peli.getPelaaja().getY() - 2, this.peli);
        vihu.setKohde(kohde);
        vihu.setArpoja(arpoja);

    }

    @Test
    public void palauttaaXoikein() {
        assertEquals(340, vihu.getX());
    }
    
    @Test
    public void palauttaaYoikein() {
        assertEquals(378, vihu.getY());
    }
    
    @Test
    public void nimiPalautetaanOikein() {
        assertEquals("jorma", vihu.getNimi());
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
    
    @Test
    public void eiLiikuSeinanLapi() {
        vihu.siirra(20, 20);
        vihu.setSuunta(Suunta.VASEN);
        assertFalse(vihu.voiLiikkua());
    }
    
    @Test
    public void eiLiikuSeinanLapi2() {
        vihu.siirra(460, 460);
        vihu.setSuunta(Suunta.OIKEA);
        assertFalse(vihu.voiLiikkua());
    }
    
    @Test
    public void eiLiikuSeinanLapi3() {
        vihu.siirra(20, 20);
        vihu.setSuunta(Suunta.YLOS);
        assertFalse(vihu.voiLiikkua());
    }
    
    @Test
    public void voiLiikkuaJosEiEsteita() {
        vihu.siirra(20, 20);
        vihu.setSuunta(Suunta.ALAS);
        assertTrue(vihu.voiLiikkua());
    }
    
    @Test
    public void voiLiikkuaJosEiEsteita2() {
        vihu.siirra(460, 20);
        vihu.setSuunta(Suunta.VASEN);
        assertTrue(vihu.voiLiikkua());
    }
    
    @Test
    public void suunnanMuutosToimii() {
        vihu.setSuunta(Suunta.YLOS);
        vihu.setSuunnanMuutos(10);
        vihu.liiku();
        vihu.liiku();
        assertEquals(376, vihu.getY());
    }
    
    @Test
    public void suunnanMuutosToimii2() {
        vihu.siirra(440, 20);
        vihu.setSuunta(Suunta.VASEN);
        vihu.setSuunnanMuutos(40);
        vihu.liiku();
        vihu.liiku();
        assertEquals(438, vihu.getX());
    }
    
    @Test
    public void josSuuntaaEiVoiMuuttaaNiinEiMuuteta() {
        vihu.siirra(40, 20);
        vihu.setSuunta(Suunta.OIKEA);
        vihu.valitseSuunta();
        assertEquals(Suunta.OIKEA, vihu.getSuunta());
    }
    
    @Test
    public void josSuuntaaEiVoiMuuttaaNiinEiMuuteta2() {
        vihu.siirra(20, 80);
        vihu.setSuunta(Suunta.ALAS);
        vihu.valitseSuunta();
        assertEquals(Suunta.ALAS, vihu.getSuunta());
    }

    @Test
    public void valitseeSuunnanOikeinJosPelaajaTahtaimessa() {
        vihu.valitseSuunta();
        assertEquals(Suunta.ALAS, vihu.getSuunta());
    }
    
    @Test
    public void valitseeSuunnanOikeinJosPelaajaTahtaimessa2() {
        vihu.siirra(330, 380);
        vihu.valitseSuunta();
        assertEquals(Suunta.OIKEA, vihu.getSuunta());
    }
    
    @Test
    public void valitseeSuunnanOikeinJosPelaajaLahettyvilla() {
        vihu.siirra(300, 340);
        vihu.valitseSuuntaJosPelaajaLahettyvilla();
        assertEquals(Suunta.OIKEA, vihu.getSuunta());
    }
    
    @Test
    public void valitseeSuunnanOikeinJosPelaajaLahettyvilla2() {
        kohde.siirra(220, 60);
        vihu.siirra(20, 80);
        vihu.valitseSuuntaJosPelaajaLahettyvilla();
        assertEquals(Suunta.YLOS, vihu.getSuunta());
    }
    
    @Test
    public void voiMuuttaaAlasTaiYlosToimii() {
        vihu.siirra(20, 40);
        assertTrue(vihu.voiMuuttaaAlasTaiYlos());
    }
    
    @Test
    public void voiMuuttaaAlasTaiYlosToimii2() {
        vihu.siirra(440, 460);
        assertFalse(vihu.voiMuuttaaAlasTaiYlos());
    }
    
    @Test
    public void voiMuuttaaVasemmalleTaiOikealleToimii() {
        vihu.siirra(20, 40);
        assertFalse(vihu.voiMuuttaaVasemmalleTaiOikealle());
    }
    
    @Test
    public void voiMuuttaaVasemmalleTaiOikealleToimii2() {
        vihu.siirra(460, 460);
        assertTrue(vihu.voiMuuttaaVasemmalleTaiOikealle());
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

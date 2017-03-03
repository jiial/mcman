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
    public void suunnanValitseminenToimii() {
        vihu.siirra(340, 380);
        kohde.siirra(300, 380);
        vihu.valitseSuuntaJosPelaajaLahettyvilla();
        assertEquals(Suunta.VASEN, vihu.getSuunta());
    }
    
    @Test
    public void suunnanValitseminenToimii2() {
        vihu.siirra(340, 360);
        kohde.siirra(340, 380);
        vihu.valitseSuuntaJosPelaajaLahettyvilla();
        assertEquals(Suunta.ALAS, vihu.getSuunta());
    }
    
    @Test
    public void suuntaValitaanKivasti() {
        vihu.setSuunta(Suunta.ALAS);
        vihu.setSuunnanMuutos(10);
        vihu.siirra(460, 440);
        vihu.valitseSuunta();
        assertEquals(Suunta.ALAS, vihu.getSuunta());
    }
    
    @Test
    public void suuntaValitaanKivasti2() {
        vihu.setSuunta(Suunta.YLOS);
        vihu.setSuunnanMuutos(10);
        vihu.siirra(460, 440);
        vihu.valitseSuunta();
        assertEquals(Suunta.YLOS, vihu.getSuunta());
    }
    
    @Test
    public void suuntaValitaanKivasti3() {
        vihu.setSuunta(Suunta.ALAS);
        vihu.setSuunnanMuutos(10);
        vihu.siirra(460, 440);
        kohde.siirra(20, 20);
        vihu.liiku();
        assertEquals(Suunta.ALAS, vihu.getSuunta());
    }
    
    @Test
    public void suunnanMuutosPalautuuOikein() {
        vihu.setSuunnanMuutos(100);
        vihu.liiku();
        assertEquals(99, vihu.getSuunnanMuutos());
    }
    
    @Test
    public void suuntaValitaanOikeinJosPelaajaLahella() {
        vihu.siirra(140, 180);
        kohde.siirra(140, 140);
        vihu.liiku();
        assertEquals(Suunta.OIKEA, vihu.getSuunta());
    }
    
    @Test
    public void valitseeSuunnanOikein() {
        vihu.setSuunnanMuutos(100);
        vihu.setSuunta(Suunta.OIKEA);
        vihu.siirra(20, 20);
        vihu.liiku();
        assertEquals(Suunta.ALAS, vihu.getSuunta());
    }
    
    @Test
    public void valitseeSuunnanOikein2() {
        vihu.setSuunnanMuutos(100);
        vihu.setSuunta(Suunta.OIKEA);
        vihu.siirra(40, 20);
        vihu.liiku();
        assertEquals(Suunta.OIKEA, vihu.getSuunta());
    }
    
    @Test
    public void valitseeSuunnanOikein3() {
        vihu.siirra(60, 80);
        kohde.siirra(20, 80);
        vihu.valitseSuuntaJosPelaajaTahtaimessa();
        assertEquals(Suunta.YLOS, vihu.getSuunta());
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
    public void vihuLiikkuuOikein() {
        vihu.siirra(340, 380);
        kohde.siirra(320, 380);
        vihu.liiku();
        assertEquals(339, vihu.getX());
    }

    @Test
    public void vihuLiikkuuOikein2() {
        vihu.siirra(340, 380);
        kohde.siirra(340, 360);
        vihu.liiku();
        assertEquals(379, vihu.getY());
    }
    
    @Test
    public void vihuLiikkuuOikein3() {
        vihu.siirra(340, 360);
        kohde.siirra(380, 360);
        vihu.liiku();
        assertEquals(340, vihu.getX());
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
    
    @Test
    public void voiLiikkuaToimii() {
        vihu.siirra(340, 379);
        vihu.setSuunta(Suunta.ALAS);
        assertTrue(vihu.voiLiikkua());
    }
    
    @Test
    public void voiLiikkuaToimii2() {
        vihu.siirra(340, 380);
        vihu.setSuunta(Suunta.ALAS);
        assertFalse(vihu.voiLiikkua());
    }
    
    @Test
    public void vihuValitseeSuunnanOikeinJosPelaajaTahtaimessa() {
        vihu.siirra(340, 360);
        kohde.siirra(380, 360);
        vihu.liiku();
        assertEquals(361, vihu.getY());
    }
    
    @Test
    public void vihuValitseeSuunnanOikeinJosPelaajaTahtaimessa2() {
        vihu.siirra(20, 80);
        kohde.siirra(60, 80);
        vihu.liiku();
        assertEquals(81, vihu.getY());
    }
    
    @Test
    public void vihuValitseeSuunnanOikeinJosPelaajaTahtaimessa3() {
        vihu.siirra(80, 20);
        kohde.siirra(80, 60);
        vihu.liiku();
        assertEquals(81, vihu.getX());
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

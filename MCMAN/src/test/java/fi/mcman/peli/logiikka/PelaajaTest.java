package fi.mcman.peli.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa luokan Pelaaja toimintaa.
 *
 * @author ljone
 */
public class PelaajaTest {

    private Pelaaja pelaaja;
    private Peli peli;

    public PelaajaTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        peli = new Peli();
        pelaaja = peli.getPelaaja();

    }
    
    @Test
    public void setNimiToimii() {
        pelaaja.setNimi(null);
        assertEquals("McMan", pelaaja.getNimi());
    }
    
    @Test
    public void setNimiToimii2() {
        pelaaja.setNimi("Pena");
        assertEquals("Pena", pelaaja.getNimi());
    }

    @Test
    public void onElossaAluksi() {
        assertTrue(pelaaja.onElossa());
    }
    
    @Test
    public void nimiAsetetaanOikein() {
        assertEquals("McMan", pelaaja.getNimi());
    }
    
    @Test
    public void suuntaAsetetaanOikein() {
        pelaaja.setSuunta(Suunta.OIKEA);
        assertEquals(Suunta.OIKEA, pelaaja.getSuunta());
    }

    @Test
    public void liikkuuVasemmalle() {
        pelaaja.setSuunta(Suunta.VASEN);
        pelaaja.liiku();
        assertEquals(338, pelaaja.getX());
    }

    @Test
    public void liikkuuOikealle() {
        pelaaja.setSuunta(Suunta.VASEN);
        pelaaja.liiku();
        pelaaja.setSuunta(Suunta.OIKEA);
        pelaaja.liiku();
        assertEquals(340, pelaaja.getX());
    }

    @Test
    public void liikkuuYlos() {
        pelaaja.setSuunta(Suunta.YLOS);
        pelaaja.liiku();
        assertEquals(378, pelaaja.getY());
    }

    @Test
    public void liikkuuALAS() {
        pelaaja.setSuunta(Suunta.YLOS);
        pelaaja.liiku();
        pelaaja.setSuunta(Suunta.ALAS);
        pelaaja.liiku();
        assertEquals(380, pelaaja.getY());
    }

    @Test
    public void eiLiikuUlosVasemmalta() {
        pelaaja.siirra(20, 20);
        pelaaja.setSuunta(Suunta.VASEN);
        pelaaja.liiku();
        pelaaja.liiku();
        assertEquals(20, pelaaja.getX());
    }

    @Test
    public void eiLiikuUlosYlhaalta() {
        pelaaja.siirra(20, 20);
        pelaaja.setSuunta(Suunta.YLOS);
        pelaaja.liiku();
        pelaaja.liiku();
        assertEquals(20, pelaaja.getY());
    }

    @Test
    public void eiLiikuUlosOikealta() {
        pelaaja.siirra(460, 460);
        pelaaja.setSuunta(Suunta.OIKEA);
        pelaaja.liiku();
        pelaaja.liiku();
        assertEquals(peli.getAlusta().getLeveys() - 40, pelaaja.getX());
    }

    @Test
    public void eiLiikuUlosAlhaalta() {
        pelaaja.siirra(460, 460);
        pelaaja.setSuunta(Suunta.ALAS);
        pelaaja.liiku();
        pelaaja.liiku();
        assertEquals(peli.getAlusta().getKorkeus() - 40, pelaaja.getY());
    }

    @Test
    public void kuoleeJosOsuuViholliseen() {
        pelaaja.siirra(20, 22);
        peli.getViholliset().get(0).liiku();
        peli.getViholliset().get(0).liiku();
        pelaaja.osuuViholliseen(peli.getViholliset().get(0));
        assertFalse(pelaaja.onElossa());
    }
    
    @Test
    public void osuuViholliseenToimiiOikein() {
        pelaaja.siirra(20, 22);
        Vihollinen v = peli.getViholliset().get(0);
        assertFalse(pelaaja.osuuViholliseen(v));
    }

    @Test
    public void osuuBurgerinToimiiOikein() {
        pelaaja.siirra(20, 20);
        assertTrue(pelaaja.osuuBurgeriin(peli.getBurgerit().get(0)));
    }
    
    @Test
    public void voiLiikkuaAlasToimiiOikein() {
        assertFalse(pelaaja.voiLiikkuaAlas(460, 460));
    }
    
    @Test
    public void voiLiikkuaYlosToimiiOikein() {
        assertFalse(pelaaja.voiLiikkuaYlos(20, 20));
    }
    
    @Test
    public void voiLiikkuaOikealleToimiiOikein() {
        assertFalse(pelaaja.voiLiikkuaOikealle(460, 460));
    }
    
    @Test
    public void voiLiikkuaVasemmalleToimiiOikein() {
        assertFalse(pelaaja.voiLiikkuaVasemmalle(20, 20));
    }
    @Test
    public void voiLiikkuaAlasToimiiOikein2() {
        assertTrue(pelaaja.voiLiikkuaAlas(460, 450));
    }
    
    @Test
    public void voiLiikkuaYlosToimiiOikein2() {
        assertTrue(pelaaja.voiLiikkuaYlos(20, 30));
    }
    
    @Test
    public void voiLiikkuaOikealleToimiiOikein2() {
        assertTrue(pelaaja.voiLiikkuaOikealle(450, 460));
    }
    
    @Test
    public void voiLiikkuaVasemmalleToimiiOikein2() {
        assertTrue(pelaaja.voiLiikkuaVasemmalle(30, 20));
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

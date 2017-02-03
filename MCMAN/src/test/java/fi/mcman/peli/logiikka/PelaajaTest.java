package fi.mcman.peli.logiikka;

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
    public void onElossaAluksi() {
        assertTrue(pelaaja.onElossa());
    }

    @Test
    public void liikkuuVasemmalle() {
        pelaaja.setSuunta(Suunta.VASEN);
        pelaaja.liiku();
        assertEquals(peli.getAlusta().getLeveys()-2, pelaaja.getX());
    }

    @Test
    public void liikkuuOikealle() {
        pelaaja.setSuunta(Suunta.VASEN);
        pelaaja.liiku();
        pelaaja.setSuunta(Suunta.OIKEA);
        pelaaja.liiku();
        assertEquals(peli.getAlusta().getLeveys()-1, pelaaja.getX());
    }

    @Test
    public void liikkuuYlos() {
        pelaaja.setSuunta(Suunta.YLOS);
        pelaaja.liiku();
        assertEquals(peli.getAlusta().getKorkeus()-2, pelaaja.getY());
    }

    @Test
    public void liikkuuALAS() {
        pelaaja.setSuunta(Suunta.YLOS);
        pelaaja.liiku();
        pelaaja.setSuunta(Suunta.ALAS);
        pelaaja.liiku();
        assertEquals(peli.getAlusta().getKorkeus()-1, pelaaja.getY());
    }

    @Test
    public void eiLiikuUlosVasemmalta() {
        pelaaja.setSuunta(Suunta.VASEN);
        for (int i = 0; i < peli.getAlusta().getLeveys()+2; i++) {
            pelaaja.liiku();
        }
        assertEquals(0, pelaaja.getX());
    }
    
    @Test
    public void eiLiikuUlosYlhaalta() {
        pelaaja.setSuunta(Suunta.YLOS);
        for (int i = 0; i < peli.getAlusta().getKorkeus()+2; i++) {
            pelaaja.liiku();
        }
        assertEquals(0, pelaaja.getY());
    }
    
    @Test
    public void eiLiikuUlosOikealta() {
        pelaaja.setSuunta(Suunta.OIKEA);
        pelaaja.liiku();
        pelaaja.liiku();
        pelaaja.liiku();
        assertEquals(peli.getAlusta().getLeveys()-1, pelaaja.getX());
    }
    
    @Test
    public void eiLiikuUlosAlhaalta() {
        pelaaja.setSuunta(Suunta.ALAS);
        pelaaja.liiku();
        pelaaja.liiku();
        pelaaja.liiku();
        assertEquals(peli.getAlusta().getKorkeus()-1, pelaaja.getY());
    }
    
    @Test
    public void kuoleeJosOsuuViholliseen() {
        pelaaja.siirra(399, 399);
        peli.lisaaVihollinen("Jorma", 398, 398, peli);
        peli.getViholliset().get(0).liiku();
        peli.getViholliset().get(0).liiku();
        pelaaja.osuukoViholliseen(peli.getViholliset().get(0));
        assertFalse(pelaaja.onElossa());
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

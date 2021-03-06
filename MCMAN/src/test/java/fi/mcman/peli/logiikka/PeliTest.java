package fi.mcman.peli.logiikka;

import fi.mcman.peli.kayttoliittyma.Kayttoliittyma;
import fi.mcman.peli.kayttoliittyma.Piirtoalusta;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa luokan Peli toimintaa.
 *
 * @author ljone
 */
public class PeliTest {

    private Peli peli;

    public PeliTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.peli = new Peli();
        peli.setKl(new Kayttoliittyma(peli));
        peli.setPaivitettava(peli.getKl().getAlusta());
    }

    @Test
    public void onAloitettuToimii() {
        assertFalse(peli.onAloitettu());
    }

    @Test
    public void onAloitettuToimii2() {
        peli.aloita();
        assertTrue(peli.onAloitettu());
    }

    @Test
    public void pelaajaKuoleeToimii() {
        peli.getPelaaja().kuolee();
        assertFalse(peli.getPelaaja().onElossa());
    }

    @Test
    public void peliJatkuuAluksi() {
        assertTrue(peli.jatkuu());
    }

    @Test
    public void highscoreTesti() {
        ArrayList<HighscoreTulos> lista = new ArrayList();
        peli.setLukija(new Scanner("100 koira"));
        assertEquals(100, peli.annaHighscoret().get(0).getPisteet());
    }

    @Test
    public void highscoreTesti2() {
        ArrayList<HighscoreTulos> lista = new ArrayList();
        peli.setLukija(new Scanner("002 koira"));
        assertEquals(2, peli.annaHighscoret().get(0).getPisteet());
    }

    @Test
    public void highscoreTesti3() {
        ArrayList<HighscoreTulos> lista = new ArrayList();
        peli.setLukija(new Scanner("002 koira"));
        assertEquals("002 koira", peli.annaHighscoret().get(0).getSisalto());
    }

    @Test
    public void lisaaNykyinenTulosToimii() {
        peli.setPisteet(100);
        peli.lisaaNykyinenTulos();
        assertEquals(100, peli.getHs().get(0).getPisteet());
    }

    @Test
    public void lisaaNykyinenTulosToimii2() {
        peli.setPisteet(6);
        peli.lisaaNykyinenTulos();
        assertEquals(6, peli.getHs().get(0).getPisteet());
    }

    @Test
    public void lisaaNykyinenTulosToimii4() {
        peli.setPisteet(16);
        peli.lisaaNykyinenTulos();
        assertEquals(16, peli.getHs().get(0).getPisteet());
    }

    @Test
    public void lisaaNykyinenTulosToimii3() {
        peli.setPisteet(6);
        peli.lisaaNykyinenTulos();
        assertEquals("006 McMan\n", peli.getHs().get(0).getSisalto());
    }

    @Test
    public void kayttoliittymaAsetetaanOikein() {
        peli.setKl(new Kayttoliittyma(peli));
        assertEquals(0, peli.getKl().getPeli().getPisteet());
    }

    @Test
    public void kirjoittajaToimii() {
        peli.setKirjoittaja(new StringWriter());
        StringWriter k = (StringWriter) peli.getKirjoittaja();
        peli.setPisteet(10);
        peli.kirjoitaTiedostoon();
        assertEquals("010 McMan\n", k.toString());
    }

    @Test
    public void kirjoittajaToimii2() {
        peli.setKirjoittaja(new StringWriter());
        StringWriter k = (StringWriter) peli.getKirjoittaja();
        peli.setPisteet(200);
        peli.paivitaHighscoret();
        assertEquals("200 McMan\n", k.toString());
    }

    @Test
    public void kirjoittajaToimii3() {
        peli.setKirjoittaja(new StringWriter());
        StringWriter k = (StringWriter) peli.getKirjoittaja();
        peli.setPisteet(22);
        peli.getPelaaja().setNimi("Esa");
        peli.kirjoitaTiedostoon();
        assertEquals("022 Esa\n", k.toString());
    }

    @Test
    public void kirjoittajaToimii4() {
        peli.setKirjoittaja(new StringWriter());
        StringWriter k = (StringWriter) peli.getKirjoittaja();
        peli.setLukija(new Scanner("010 Esa"));
        peli.setPisteet(22);
        peli.getPelaaja().setNimi("Esa");
        peli.kirjoitaTiedostoon();
        assertEquals("022 Esa\n", k.toString());
    }

    @Test
    public void kirjoittajaToimii5() {
        peli.setKirjoittaja(new StringWriter());
        StringWriter k = (StringWriter) peli.getKirjoittaja();
        peli.setLukija(new Scanner(""));
        peli.setPisteet(22);
        peli.getPelaaja().setNimi("Esa");
        peli.kirjoitaTiedostoon();
        assertEquals("022 Esa\n", k.toString());
    }

    @Test
    public void paivitettavaAsetetaanOikein() {
        peli.setPaivitettava(new Piirtoalusta(peli));
        Paivitettava p = peli.getPaivitettava();
        assertNotEquals(null, p.toString());
    }

    @Test
    public void nalkaPalautetaanOikein() {
        assertEquals(0, peli.getNalka());
    }

    @Test
    public void nalkaPalautetaanOikein2() {
        peli.setNalka(1000);
        assertEquals(1000, peli.getNalka());
    }

    @Test
    public void josPeliEiJatkuNiinPeliaEiEnaaPaiviteta() {
        peli.getPelaaja().kuolee();
        try {
            peli.start();
            Thread.sleep(100);
            assertEquals(0, peli.getPisteet());
        } catch (InterruptedException e) {
            System.out.println("Testi keskeytyi.");
        }
    }

    @Test
    public void josPeliEiJatkuNiinPeliaEiEnaaPaiviteta2() {
        peli.getPelaaja().kuolee();
        try {
            peli.start();
            Thread.sleep(100);
            assertEquals(20, peli.getViholliset().get(0).getX());
        } catch (InterruptedException e) {
            System.out.println("Testi keskeytyi.");
        }
    }

    @Test
    public void peliEiJatkuKunPelaajaKuollut() {
        peli.getPelaaja().kuolee();
        assertFalse(peli.jatkuu());
    }

    @Test
    public void peliEiJatkuKunPelaajaSyoKaikkiBurgeritEliVoittaa() {
        for (Burgeri b : peli.getBurgerit()) {
            b.setSyoty(true);
        }
        assertFalse(peli.jatkuu());
    }

    @Test
    public void onBurgereitaMetodiToimii() {
        assertTrue(peli.onBurgereita());
    }

    @Test
    public void onBurgereitaToimii2() {
        for (Burgeri b : peli.getBurgerit()) {
            b.setSyoty(true);
        }
        assertFalse(peli.onBurgereita());
    }

    @Test
    public void pisteetAlussaNolla() {
        assertEquals(0, peli.getPisteet());
    }

    @Test
    public void vihollisetLuodaanOikein() {
        assertEquals(20, peli.getViholliset().get(0).getX());
    }

    @Test
    public void vihollisetLuodaanOikein2() {
        assertEquals(460, peli.getViholliset().get(3).getY());
    }

    @Test
    public void vihollisetLuodaanOikein3() {
        assertEquals(140, peli.getViholliset().get(4).getX());
    }

    @Test
    public void burgeritLuodaanOikein() {
        assertEquals(20, peli.getBurgerit().get(0).getX());
    }

    @Test
    public void burgeritLuodaanOikein2() {
        assertEquals(460, peli.getBurgerit().get(143).getY());
    }

    @Test
    public void burgerinSyominenVahentaaNalkaa() {
        peli.setNalka(100);
        try {
            peli.start();
            peli.aloita();
            Thread.sleep(30);
            assertEquals(41, peli.getNalka());
        } catch (InterruptedException e) {
            System.out.println("Testi keskeytyi.");
        }
    }

    @Test
    public void burgerinSyominenEiVahennaNalkaaAlleNollan() {
        peli.setNalka(20);
        try {
            peli.start();
            peli.aloita();
            Thread.sleep(30);
            assertEquals(0, peli.getNalka());
        } catch (InterruptedException e) {
            System.out.println("Testi keskeytyi.");
        }
    }

    @Test
    public void pisteetKasvavatKunPelaajaSyoBurgerin() {
        try {
            peli.start();
            peli.aloita();
            Thread.sleep(100);
            assertEquals(1, peli.getPisteet());
        } catch (InterruptedException e) {
            System.out.println("Testi keskeytyi.");
        }
    }

    @Test
    public void pisteetEivatKasvaKunPelaajaEiSyoBurgeria() {
        peli.getPelaaja().siirra(341, 380);
        try {
            peli.start();
            Thread.sleep(100);
            assertEquals(0, peli.getPisteet());
        } catch (InterruptedException e) {
            System.out.println("Testi keskeytyi.");
        }
    }

    @Test
    public void peliLoppuuKunNalkaKasvaaLiianSuureksi() {
        peli.setNalka(1500);
        assertFalse(peli.jatkuu());
    }

    @Test
    public void peliJatkuuJosNalkaEiKasvaLiianSuureksi() {
        peli.setNalka(1400);
        assertTrue(peli.jatkuu());
    }

//    @Test
//    public void peliPaivittaaHighscoretOikein() {
//        peli.setPisteet(200);
//        peli.paivitaHighscoret();
//        assertEquals(200, peli.annaHighscoret().get(0).getPisteet());
//    }
//    @Test
//    public void peliAntaaHighscoretOikein() {
//        assertEquals(5, peli.annaHighscoret().size());
//    }
//    @Test
//    public void onkoPisteetSuuremmatToimiiOikein() {
//        peli.setPisteet(200);
//        String s = "120 Uolevi";
//        assertTrue(peli.onkoPisteetSuuremmat(s));
//    }
//    @Test
//    public void onkoPisteetSuuremmatToimiiOikein2() {
//        peli.setPisteet(121);
//        String s = "120 Uolevi";
//        assertTrue(peli.onkoPisteetSuuremmat(s));
//    }
//    
////    @Test
////    public void onkoPisteetSuuremmatToimiiOikein3() {
////        peli.setPisteet(120);
////        String s = "120 Uolevi";
////        assertFalse(peli.onkoPisteetSuuremmat(s));
////    }
//    
//    @Test
//    public void onkoPisteetSuuremmatToimiiOikein4() {
//        peli.setPisteet(10);
//        String s = "120 Uolevi";
//        assertFalse(peli.onkoPisteetSuuremmat(s));
//    }
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

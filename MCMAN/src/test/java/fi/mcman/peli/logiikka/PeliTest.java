
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
 * 
 * Testaa luokan Peli toimintaa.
 * 
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
    }
    
    @Test
    public void peliJatkuuAluksi() {
        assertTrue(peli.jatkuu());
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
    public void pisteetAlussaNolla() {
        assertEquals(0, peli.getPisteet());
    }
    
    @Test
    public void pisteetKasvavatKunPelaajaSyoBurgerin() {
        
        
    }
    
    @Test
    public void peliLoppuuKunNalkaKasvaaLiianSuureksi() {
        peli.setNalka(500);
        assertFalse(peli.jatkuu());
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

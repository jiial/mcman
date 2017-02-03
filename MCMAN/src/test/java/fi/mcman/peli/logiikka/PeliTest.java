
package fi.mcman.peli.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        assertTrue(peli.jatkuuko());
    }
    
    @Test
    public void peliEiJatkuKunPelaajaKuollut() {
        peli.getPelaaja().kuolee();
        assertFalse(peli.jatkuuko());
    }
    
    @Test
    public void peliEiJatkuKunPelaajaSyoKaikkiBurgeritEliVoittaa() {
        peli.getBurgerit().get(0).setSyoty(true);
        assertFalse(peli.jatkuuko());
    }
    
    @Test
    public void onBurgereitaMetodiToimii() {
        assertTrue(peli.onBurgereita());
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

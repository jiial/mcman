/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Testaa että luokan Tiili metodit toimivat oikein.
 * 
 */
public class TiiliTest {
    private Tiili t;
    
    public TiiliTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        t = new Tiili(200, 200, 9);
    }
    
    @Test
    public void tiiliPalauttaaArvonOikein() {
        assertEquals(9, t.getArvo());
    }
    
    @Test
    public void tiiliPalauttaaKoordinaatinOikein() {
        assertEquals(200, t.getAlkuX());
    }
    
    @Test
    public void tiiliPalauttaaKoordinaatinOikein2() {
        assertEquals(200, t.getAlkuY());
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

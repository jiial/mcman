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
 * Testaa että luokan Tiili metodit toimivat oikein.
 *
 * @author ljone
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
    public void tiiliPalauttaaArvonOikein2() {
        Tiili b = new Tiili(0, 0, 1);
        assertEquals(1, b.getArvo());
    }
    
    @Test
    public void tiiliPalauttaaArvonOikein3() {
        t.asetaArvo(5);
        assertEquals(5, t.getArvo());
    }
    
    @Test
    public void tiiliAsettaaArvonOikein() {
        t.asetaArvo(3);
        assertEquals(3, t.getArvo());
    }

    @Test
    public void tiiliPalauttaaKoordinaatinOikein() {
        assertEquals(200, t.getAlkuX());
    }

    @Test
    public void tiiliPalauttaaKoordinaatinOikein2() {
        assertEquals(200, t.getAlkuY());
    }
    
    @Test
    public void tiiliPalauttaaKoordinaatinOikein3() {
        Tiili b = new Tiili(120, 120, 9);
        assertEquals(120, b.getAlkuX());
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

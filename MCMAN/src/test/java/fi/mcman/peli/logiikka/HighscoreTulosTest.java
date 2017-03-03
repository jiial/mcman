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
 */
public class HighscoreTulosTest {
    private HighscoreTulos h;
    
    public HighscoreTulosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        h = new HighscoreTulos("010 Jorma");
    }
    
    @Test
    public void sisaltoAsetetaanJaPalautetaanOikein() {
        h.setSisalto("testi");
        assertEquals("testi", h.getSisalto());
    }
    
    @Test
    public void pisteetMuunnetaanOikein() {
        assertEquals(10, h.getPisteet());
    }
    
    @Test
    public void pisteetMuunnetaanOikein2() {
        h.setPisteet(003);
        assertEquals(3, h.getPisteet());
    }
    
    @Test
    public void pisteetMuunnetaanOikein3() {
        h.setPisteet(132);
        assertEquals(132, h.getPisteet());
    }
    
    @Test
    public void vertailuToimiiOikein() {
        HighscoreTulos t = new HighscoreTulos("009 Pena");
        assertEquals(1, t.compareTo(h));
    }
    
    @Test
    public void vertailuToimiiOikein2() {
        HighscoreTulos t = new HighscoreTulos("011 Pena");
        assertEquals(-1, t.compareTo(h));
    }
    
    @Test
    public void vertailuToimiiOikein3() {
        HighscoreTulos t = new HighscoreTulos("010 Pena");
        assertEquals(-1, t.compareTo(h));
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

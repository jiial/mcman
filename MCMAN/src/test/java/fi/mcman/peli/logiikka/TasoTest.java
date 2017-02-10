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
 * Testaa luokan Taso toimintaa.
 * 
 */
public class TasoTest {
    private Taso taso;
    
    public TasoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        taso = new Taso();
    }
    
    @Test
    public void tasonTiiltenArvotOikein() {
        assertEquals(1, taso.palautaArvo(0, 0));
    }
    
    @Test
    public void tasonTiiltenArvotOikein2() {
        assertEquals(3, taso.palautaArvo(24, 24));
    }
    
    @Test
    public void tasonTiiltenArvotOikein3() {
        assertEquals(9, taso.palautaArvo(1, 1));
    }
    
    @Test
    public void tasonTiiltenKoordinaatitOikein() {
        assertEquals(480, taso.palautaTiili(485, 485).getAlkuX());
    }
    
    @Test
    public void tasonTiiltenKoordinaatitOikein2() {
        assertEquals(60, taso.palautaTiili(485, 70).getAlkuY());
    }
    
    @Test
    public void tasonTiiltenKoordinaatitOikein3() {
        assertEquals(9, taso.palautaTiili(298, 300).getArvo());
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

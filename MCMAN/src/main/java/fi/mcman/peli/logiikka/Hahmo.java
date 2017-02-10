
package fi.mcman.peli.logiikka;

/**
 * 
 * @author ljone
 * 
 * Abstrakti luokka jonka perivät pelin hahmot eli Pelaaja ja Vihollinen -luokkien oliot.
 * Luokassa hahmojen yhteisiä muuttujia ja metodeja joita tarvitaan sijainnin selvittämiseen ja liikkumiseen.
 * 
 */

public abstract class Hahmo {
    protected String nimi;
    protected int x, y;
    protected Suunta suunta;
    protected Peli peli;
    
    public abstract void siirra(int x, int y);
    
    public abstract void liiku();
    
}

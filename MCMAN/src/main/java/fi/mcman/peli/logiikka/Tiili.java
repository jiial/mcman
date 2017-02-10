
package fi.mcman.peli.logiikka;

/**
 * 
 * @author ljone
 * 
 * Luokka kuvaa yhtä "palasta" pelissä.
 * Jokaiseen Tiileen liittyy luokalta Taso saatu arvo joka kertoo voiko Tiilen päälle liikkua.
 * @see Taso
 * 
 */

public class Tiili {
    private int leveys;
    private int korkeus;
    private int alkuX;
    private int alkuY;
    private int arvo;
    
    public Tiili(int alkuX, int alkuY, int arvo) {
        this.alkuX = alkuX;
        this.alkuY = alkuY;
        this.korkeus = 20;
        this.leveys = 20;
        this.arvo = arvo;
    }

    public int getAlkuX() {
        return alkuX;
    }

    public int getAlkuY() {
        return alkuY;
    }
    
    public void asetaArvo(int arvo) {
        this.arvo = arvo;
    }
    
    public int getArvo() {
        return this.arvo;
    }
}


package fi.mcman.peli.logiikka;

/**
 * 
 * @author ljone
 * 
 * Tämä luokka kuvaa yksinkertaisesti vain pelialueen kokoa (leveys, korkeus).
 * 
 */

public class Pelialusta {
    private int leveys;
    private int korkeus;

    public Pelialusta(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public int getLeveys() {
        return leveys;
    }
    
    
}

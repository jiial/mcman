package fi.mcman.peli.logiikka;

/**
 * Tämä luokka kuvaa yksinkertaisesti pelialueen kokoa (leveys, korkeus).
 *
 * @author ljone
 */
public class Pelialusta {

    private int leveys;
    private int korkeus;

    /**
     * Luo uuden pelialustan.
     *
     * @param leveys parametrina saatu leveys alustalle
     * @param korkeus parametrina saatu korkeus alustalle
     */
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

package fi.mcman.peli.logiikka;

/**
 * Abstrakti luokka jonka perivät pelin hahmot eli Pelaaja ja Vihollinen
 * -luokkien oliot, joilla yhteisiä muuttujia ja metodeja sijainnin
 * selvittämiseen ja liikkumiseen.
 *
 * @author ljone
 */
public abstract class Hahmo {

    /**
     * Hahmon nimi.
     */
    protected String nimi;
    /**
     * Hahmolla on x- ja y-koordinaatit.
     */
    protected int x, y;
    /**
     * Hahmolla on aina jokin suunta.
     */
    protected Suunta suunta;
    /**
     * Hahmo liittyy Peliin.
     */
    protected Peli peli;

    /**
     * Siirtää Hahmon tiettyyn pisteeseen, metodi helpottaa pelin testaamista.
     *
     * @param x haluttu x-koordinaatti
     * @param y haluttu y-koordinaatti
     */
    public void siirra(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Muuttaa Hahmon x- tai y-koordinaattia haluttuun suuntaan halutun verran.
     */
    public abstract void liiku();

}

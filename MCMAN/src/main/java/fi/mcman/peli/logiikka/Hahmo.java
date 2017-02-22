package fi.mcman.peli.logiikka;

/**
 * Abstrakti luokka jonka perivät pelin hahmot eli Pelaaja ja Vihollinen
 * -luokkien oliot, joilla yhteisiä muuttujia ja metodeja sijainnin
 * selvittämiseen ja liikkumiseen.
 *
 * @author ljone
 */
public abstract class Hahmo {

    protected String nimi;
    protected int x, y;
    protected Suunta suunta;
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

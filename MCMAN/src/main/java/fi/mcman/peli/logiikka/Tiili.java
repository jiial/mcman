package fi.mcman.peli.logiikka;

/**
 * Luokka kuvaa yhtä "palasta" pelissä. Jokaiseen Tiileen liittyy luokalta Taso
 * saatu arvo joka kertoo voiko Tiilen päälle liikkua.
 *
 * @author ljone
 * @see Taso
 */
public class Tiili {

    /**
     * Tiilen leveys.
     */
    private int leveys;
    /**
     * Tiilen korkeus.
     */
    private int korkeus;
    /**
     * Tiilen vasen reuna.
     */
    private int alkuX;
    /**
     * Tiilen yläreuna.
     */
    private int alkuY;
    /**
     * Tiili saa arvon Tasolta.
     */
    private int arvo;

    /**
     * Luo uuden Tiilen parametrina saatuun koordinaattiin.
     *
     * @param alkuX Tiilen vasen reuna
     * @param alkuY Tiilen yläreuna
     * @param arvo Tiileen asetettava arvo
     */
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

    /**
     * Asettaa arvon tiilelle (setArvo).
     *
     * @param arvo Integer-tyyppinen arvo
     */
    public void asetaArvo(int arvo) {
        this.arvo = arvo;
    }

    public int getArvo() {
        return this.arvo;
    }
}

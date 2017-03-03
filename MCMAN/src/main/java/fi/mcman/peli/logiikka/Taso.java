package fi.mcman.peli.logiikka;

/**
 * Luokka sisältää datan ohjelman tason rakenteesta, jota käytetään
 * liikkumisesteiden tunnistamiseen luokkien Pelaaja ja Vihollinen yhteydessä.
 * Luokalla on myös kaksiulotteinen taulukko Tiiliä johon kyseinen data
 * säilötään.
 *
 * @author ljone
 * @see Tiili
 */
public final class Taso {

    /**
     * Muuttujaan säilötään tason tiilet.
     */
    private Tiili[][] tiilet;

    /**
     * Taulukko sisältää tasoon liittyvän datan, jossa arvo kertoo minkälaisesta
     * tiilestä on kyse.
     */
    public static final int[] KENTTA = new int[]{
        1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 2,
        8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 6,
        8, 9, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 6,
        8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 6,
        8, 9, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 9, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 9, 0, 9, 9, 9, 6,
        8, 9, 9, 9, 0, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 0, 9, 0, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 0, 0, 0, 0, 9, 0, 9, 0, 9, 9, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 0, 9, 0, 0, 0, 0, 9, 0, 0, 0, 0, 9, 0, 9, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 9, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 9, 0, 9, 0, 9, 6,
        8, 9, 0, 9, 0, 9, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 9, 0, 9, 6,
        8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 6,
        8, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 9, 6,
        8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 6,
        4, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 3
    };

    /**
     * Luo uuden tason käyttäen metodia luoTaso().
     *
     * @see luoTaso()
     */
    public Taso() {
        luoTaso();
    }

    public int[] getKENTTA() {
        return KENTTA;
    }

    /**
     * Alustaa 25x25 taulukon, johon lisätään Tiiliä jotka saavat muuttujan
     * KENTTA arvot järjestyksessä.
     *
     * @see Tiili
     */
    public void luoTaso() {
        tiilet = new Tiili[25][25];

        int x = 0;
        int y = 0;

        for (int i = 0; i < KENTTA.length; i++) {
            if (x == 25) {
                x = 0;
                y++;
            }
            tiilet[x][y] = new Tiili(x * 20, y * 20, KENTTA[i]);
            x++;
        }
    }

    /**
     * Hakee arvon halutussa kohdassa olevalle tiilelle.
     *
     * @param x tiilen x-koordinaatti
     * @param y tiilen y-koordinaatti
     * @see Tiili
     * @return palauttaa halutun tiilen arvon
     */
    public int palautaArvo(int x, int y) {
        return tiilet[x][y].getArvo();
    }

    /**
     * Hakee annettussa koordinaatissa sijaitsevan tiilen.
     *
     * @param x haluttu x-koordinaatti
     * @param y haluttu y-koordinaatti
     * @return palauttaa viitteen kyseisessä kohdassa sijaitsevaan Tiileen.
     * @see Tiili
     */
    public Tiili palautaTiili(int x, int y) {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                Tiili t = tiilet[i][j];
                if (t.getAlkuX() <= x && x < t.getAlkuX() + 20) {
                    if (t.getAlkuY() <= y && y < t.getAlkuY() + 20) {
                        return t;
                    }
                }
            }
        }
        return null;
    }

}

package fi.mcman.peli.logiikka;

/**
 * Luokka kuvaa burgereita joita pelin pelaaja voi syödä liikkumalla burgerin
 * kohdalle.
 *
 * @author ljone
 */
public class Burgeri {

    private int x;
    private int y;
    private boolean syoty;

    /**
     * Luo uuden burgerin.
     *
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public Burgeri(int x, int y) {
        this.x = x;
        this.y = y;
        this.syoty = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Metodi kertoo onko burgeri jo syöty.
     *
     * @return true jos on syöty, false jos ei ole
     */
    public boolean isSyoty() {
        return syoty;
    }

    public void setSyoty(boolean syoty) {
        this.syoty = syoty;
    }

}

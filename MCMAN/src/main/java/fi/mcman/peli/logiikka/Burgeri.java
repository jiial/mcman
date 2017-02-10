package fi.mcman.peli.logiikka;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author ljone
 * 
 * Luokka kuvaa burgereita joita pelin pelaaja voi syödä liikkumalla burgerin kohdalle.
 * 
 */

public class Burgeri {

    private int x;
    private int y;
    private boolean syoty;

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
     * @return true jos on syöty, false jos ei ole
     */
    
    public boolean isSyoty() {
        return syoty;
    }

    public void setSyoty(boolean syoty) {
        this.syoty = syoty;
    }

    /**
     * Kertoo kuinka burgeri piirretään.
     * @param g javassa piirtämiseen vaadittava parametri
     */
    
    public void piirra(Graphics g) {
        g.setColor(Color.orange);
        if (!syoty) {
            g.fillOval(x + 5, y + 5, 10, 10);
        }
    }

}

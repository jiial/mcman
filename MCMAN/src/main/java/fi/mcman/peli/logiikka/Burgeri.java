package fi.mcman.peli.logiikka;

import java.awt.Color;
import java.awt.Graphics;

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

    public boolean isSyoty() {
        return syoty;
    }

    public void setSyoty(boolean syoty) {
        this.syoty = syoty;
    }

    public void piirra(Graphics g) {
        g.setColor(Color.orange);
        if (!syoty) {
            g.fillOval(x, y, 20, 20);
        } else {
            
        }

    }

}

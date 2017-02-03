
package fi.mcman.peli.logiikka;

import java.awt.Color;
import java.awt.Graphics;


public class Pelaaja extends Hahmo implements Paivitettava {
    
    private boolean elossa;
   
    public Pelaaja(Peli peli) {
        super.nimi = "McMan";
        super.x = 599;
        super.y = 599;
        super.peli = peli;
        this.elossa = true;
    }
    
    @Override
    public void siirra(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    

    public String getNimi() {
        return nimi;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public boolean onElossa() {
        return elossa;
    }

    
    public void setSuunta(Suunta suunta) {
        this.suunta = suunta;
    }
    
    
    public void kuolee() {
        this.elossa = false;
    }
    
    public boolean osuukoBurgeriin(Burgeri b) {
        if (this.x == b.getX() && this.y == b.getY()) {
            return true;
        } 
        return false;
    }
    
    public boolean osuukoViholliseen(Vihollinen v) {
        if (this.x == v.getX() && this.y == v.getY()) {
            kuolee();
            return true;
        } 
        return false;
    }

    @Override
    public void liiku() {
        if (this.suunta == suunta.VASEN) {
            if (this.x > 0) {
                this.x--;
            }
        }
        if (this.suunta == suunta.OIKEA) {
            if (this.x < this.peli.getAlusta().getLeveys() - 1) {
                this.x++;
            }
        }
        if (this.suunta == suunta.ALAS) {
            if (this.y < this.peli.getAlusta().getKorkeus() - 1) {
                this.y++;
            }
        }
        if (this.suunta == suunta.YLOS) {
            if (this.y > 0) {
                this.y--;
            }
        }
    }
    
    public void piirra(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawOval(x, y, 20, 20);
    }

    @Override
    public void paivita() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


package fi.mcman.peli.logiikka;

import java.awt.Color;
import java.awt.Graphics;


public class Pelaaja extends Hahmo {
    
    private boolean elossa;
   
    public Pelaaja(Peli peli) {
        super.nimi = "McMan";
        super.x = 300;
        super.y = 300;
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

    
    public int getX() {
        return x;
    }

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
    
    public boolean osuuBurgeriin(Burgeri b) {
        if (this.x == b.getX() && this.y == b.getY()) {
            return true;
        } 
        return false;
    }
    
    public boolean osuuViholliseen(Vihollinen v) {
        if (this.x == v.getX() && this.y == v.getY()) {
            kuolee();
            return true;
        } 
        return false;
    }

    @Override
    public void liiku() {
        if (this.suunta == suunta.VASEN) {
            if (this.x > 1) {
                this.x -= 2;
            }
        }
        if (this.suunta == suunta.OIKEA) {
            if (this.x < this.peli.getAlusta().getLeveys() - 2) {
                this.x += 2;
            }
        }
        if (this.suunta == suunta.ALAS) {
            if (this.y < this.peli.getAlusta().getKorkeus() - 2) {
                this.y += 2;
            }
        }
        if (this.suunta == suunta.YLOS) {
            if (this.y > 1) {
                this.y -= 2;
            }
        }
    }
    
    public void piirra(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 20, 20);
        
    }

    
}

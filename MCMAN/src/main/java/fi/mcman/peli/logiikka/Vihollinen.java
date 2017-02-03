package fi.mcman.peli.logiikka;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Vihollinen extends Hahmo implements Paivitettava {
  
    private int pieninX, suurinX, pieninY, suurinY;
    private Pelaaja kohde;
    private Random arpoja;

    public Vihollinen(String nimi, int x, int y, Peli peli) {
        super.nimi = nimi;
        super.x = x;
        super.y = y;
        super.peli = peli;
        this.kohde = super.peli.getPelaaja();
        this.arpoja = new Random();
        this.pieninX = 200;
        this.pieninY = 200;
        this.suurinX = 400;
        this.suurinY = 400;
    }
    
    @Override
    public void siirra(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setArpoja(Random arpoja) {
        this.arpoja = arpoja;
    }

    public void setPieninX(int pieninX) {
        this.pieninX = pieninX;
    }

    public void setPieninY(int pieninY) {
        this.pieninY = pieninY;
    }

    public void setSuurinX(int suurinX) {
        this.suurinX = suurinX;
    }

    public void setSuurinY(int suurinY) {
        this.suurinY = suurinY;
    }

    public void setKohde(Pelaaja kohde) {
        this.kohde = kohde;
    }

    public String getNimi() {
        return nimi;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public void liiku() {
        valitseSuunta();
        if (super.suunta == Suunta.VASEN) {
            if (this.x > this.pieninX) {
                this.x--;
            }
        }
        if (super.suunta == Suunta.OIKEA) {
            if (this.x < this.suurinX) {
                this.x++;
            }
        }
        if (super.suunta == Suunta.ALAS) {
            if (this.y < this.suurinY) {
                this.y++;
            }
        }
        if (super.suunta == Suunta.YLOS) {
            if (this.y > this.pieninY) {
                this.y--;
            }
        }
    }

    
    public void valitseSuunta() {

        if (kohde.getX() == this.x) {
            if (kohde.getY() - this.y <= 20 && kohde.getY() - this.y > 0) {
                this.suunta = Suunta.ALAS;
            }
            if (kohde.getY() - this.y < 0 && kohde.getY() - this.y >= -20) {
                this.suunta = Suunta.YLOS;
            }
        } else if (kohde.getY() == this.y) {
            if (kohde.getX() - this.x <= 20 && kohde.getX() - this.x >= 0) {
                this.suunta = Suunta.ALAS;
            }
            if (kohde.getX() - this.x < 0 && kohde.getX() - this.x >= -20) {
                this.suunta = Suunta.YLOS;
            }
        } else if (kohde.getX() - this.x <= 20 && kohde.getX() - this.x > 0) {
            this.suunta = Suunta.OIKEA;
        } else if (kohde.getX() - this.x < 0 && kohde.getX() - this.x >= -20) {
            this.suunta = Suunta.VASEN;
        } else if (kohde.getY() - this.y <= 20 && kohde.getY() - this.y > 0) {
            this.suunta = Suunta.ALAS;
        } else if (kohde.getY() - this.y < 0 && kohde.getY() - this.y >= -20) {
            this.suunta = Suunta.YLOS;
        } else {
            arvoSuunta();
        }
    }

    public void arvoSuunta() {
        int luku = this.arpoja.nextInt(3);
        if (luku == 0) {
            this.suunta = suunta.VASEN;
        }
        if (luku == 1) {
            this.suunta = suunta.OIKEA;
        }
        if (luku == 2) {
            this.suunta = suunta.ALAS;
        }
        if (luku == 3) {
            this.suunta = suunta.YLOS;
        }
    }

    
    public void piirra(Graphics g) {
        g.setColor(Color.red);
//        g.drawRect(x, y, 30, 30);
        g.fill3DRect(x, y, 30, 30, true);
    }

    @Override
    public void paivita() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

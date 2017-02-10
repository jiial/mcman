package fi.mcman.peli.logiikka;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * 
 * @author ljone
 * 
 * Luokkassa pelin vihollisten toiminnallisuus.
 * Luokan tärkein ominaisuus on tekoäly jonka mukaan vihollinen liikkuu.
 * @see liiku() valitseSuunta() arvoSuunta()
 * 
 * Perii abstraktin luokan Hahmo.
 * 
 */

public class Vihollinen extends Hahmo {

    private int pieninX, suurinX, pieninY, suurinY;
    private Pelaaja kohde;
    private Random arpoja;
    private int suunnanMuutos;

    public Vihollinen(String nimi, int x, int y, Peli peli) {
        super.nimi = nimi;
        super.x = x;
        super.y = y;
        super.peli = peli;
        this.kohde = super.peli.getPelaaja();
        this.arpoja = new Random();
        this.pieninX = 0;
        this.pieninY = 0;
        this.suurinX = 480;
        this.suurinY = 480;
        this.suunnanMuutos = 0;
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
        if (suunta == Suunta.VASEN) {
            if (this.x > this.pieninX) {
                this.x--;
            }
        }
        if (suunta == Suunta.OIKEA) {
            if (this.x < this.suurinX) {
                this.x++;
            }
        }
        if (suunta == Suunta.ALAS) {
            if (this.y < this.suurinY) {
                this.y++;
            }
        }
        if (suunta == Suunta.YLOS) {
            if (this.y > this.pieninY) {
                this.y--;
            }
        }
    }

    public void valitseSuunta() {
        
        if (suunnanMuutos > 0) {
            suunnanMuutos--;
            return;
        }
        
        if (kohde.getX() == this.x) {
            if (kohde.getY() - this.y <= 90 && kohde.getY() - this.y > 0) {
                this.suunta = Suunta.ALAS;
            } else if (kohde.getY() - this.y < 0 && kohde.getY() - this.y >= -90) {
                this.suunta = Suunta.YLOS;
            } else {
                arvoSuunta();
            }
        } else if (kohde.getY() == this.y) {
            if (kohde.getX() - this.x <= 90 && kohde.getX() - this.x >= 0) {
                this.suunta = Suunta.OIKEA;
            } else if (kohde.getX() - this.x < 0 && kohde.getX() - this.x >= -90) {
                this.suunta = Suunta.VASEN;
            } else {
                arvoSuunta();
            }
        } else if (kohde.getX() - this.x <= 90 && kohde.getX() - this.x > 0) {
            this.suunta = Suunta.OIKEA;
        } else if (kohde.getX() - this.x < 0 && kohde.getX() - this.x >= -90) {
            this.suunta = Suunta.VASEN;
        } else if (kohde.getY() - this.y <= 90 && kohde.getY() - this.y > 0) {
            this.suunta = Suunta.ALAS;
        } else if (kohde.getY() - this.y < 0 && kohde.getY() - this.y >= -90) {
            this.suunta = Suunta.YLOS;
        } else {
            arvoSuunta();
        }
    }

//    public boolean onTahtaimessa(int a, int b) {
//        if (a - b > -5 && a - b < 5) {
//            return true;
//        }
//        return false;
//
//    }

    public void arvoSuunta() {
        int luku = this.arpoja.nextInt(4);
        if (luku == 0) {
            this.suunta = suunta.VASEN;
            suunnanMuutos = 30;
        }
        if (luku == 1) {
            this.suunta = suunta.OIKEA;
            suunnanMuutos = 30;
        }
        if (luku == 2) {
            this.suunta = suunta.ALAS;
            suunnanMuutos = 30;
        }
        if (luku == 3) {
            this.suunta = suunta.YLOS;
            suunnanMuutos = 30;
        }
    }

    public void piirra(Graphics g) {
        g.setColor(Color.red);
//        g.drawRect(x, y, 30, 30);
        g.fill3DRect(x, y, 20, 20, true);
    }

}

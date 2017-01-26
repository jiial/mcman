package fi.mcman.peli.logiikka;

import java.util.Random;

public class Vihollinen implements Liikkuva, Paivitettava {

    private String nimi;
    private int x, y;
    private int pieninX, suurinX, pieninY, suurinY;
    private Pelaaja kohde;
    private Suunta suunta;
    private Random arpoja;

    public Vihollinen(String nimi, int x, int y) {
        this.nimi = nimi;
        this.x = x;
        this.y = y;

    }
    
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

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void liiku() {
        valitseSuunta();
        if (this.suunta == Suunta.VASEN) {
            if (this.x > this.pieninX) {
                this.x--;
            }
        }
        if (this.suunta == Suunta.OIKEA) {
            if (this.x < this.suurinX) {
                this.x++;
            }
        }
        if (this.suunta == Suunta.ALAS) {
            if (this.y < this.suurinY) {
                this.y++;
            }
        }
        if (this.suunta == Suunta.YLOS) {
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

    @Override
    public void paivita() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}


package fi.mcman.peli.logiikka;


public class Pelaaja implements Liikkuva, Paivitettava {
    private String nimi;
    private int x;
    private int y;
    private boolean elossa;
    private Suunta suunta;
    private Peli peli;
    
    public Pelaaja(Peli peli) {
        this.nimi = "McMan";
        this.elossa = true;
        this.x = 599;
        this.y = 599;
        this.peli = peli;
    }
    
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
    
    public boolean osuuko(Paivitettava p) {
        if (this.x == p.getY() && this.y == p.getY()) {
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

    @Override
    public void paivita() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

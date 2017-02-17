package fi.mcman.peli.logiikka;

/**
 *
 * @author ljone
 *
 * Luokka Pelaaja toteuttaa pelissä ohjattavan hahmon toiminnallisuuden. Luokka
 * perii abstraktin luokan Hahmo.
 * @see Hahmo
 *
 */
public class Pelaaja extends Hahmo {

    private boolean elossa;
    private Taso taso;

    public Pelaaja(Peli peli) {
        super.nimi = "McMan";
        super.x = 340;
        super.y = 380;
        super.peli = peli;
        this.elossa = true;
        this.taso = peli.getTaso();
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

    public Suunta getSuunta() {
        return suunta;
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
//            if (this.x > 1) {
            if (taso.palautaTiili(x - 2, y).getArvo() == 9) {
                if (taso.palautaTiili(x - 2, y + 19).getArvo() == 9) {
                    this.x -= 2;
                }
            }
//            }
        }
        if (this.suunta == suunta.OIKEA) {
//            if (this.x < this.peli.getAlusta().getLeveys() - 20) {
            if (taso.palautaTiili(x + 20, y).getArvo() == 9) {
                if (taso.palautaTiili(x + 20, y + 19).getArvo() == 9) {
                    this.x += 2;
                }
            }
//            }
        }
        if (this.suunta == suunta.ALAS) {
//            if (this.y < this.peli.getAlusta().getKorkeus() - 20) {
            if (taso.palautaTiili(x, y + 20).getArvo() == 9) {
                if (taso.palautaTiili(x + 19, y + 20).getArvo() == 9) {
                    this.y += 2;
                }
            }
//            }
        }
        if (this.suunta == suunta.YLOS) {
//            if (this.y > 1) {
            if (taso.palautaTiili(x, y - 2).getArvo() == 9) {
                if (taso.palautaTiili(x + 19, y - 2).getArvo() == 9) {
                    this.y -= 2;
                }
            }
//            }

        }
    }

    public boolean voiLiikkuaVasemmalle(int i, int j) {
        if (taso.palautaTiili(i - 2, j).getArvo() == 9) {
            if (taso.palautaTiili(i - 2, j + 19).getArvo() == 9) {
                return true;
            }
        }
        return false;
    }

    public boolean voiLiikkuaOikealle(int i, int j) {
        if (taso.palautaTiili(i + 20, j).getArvo() == 9) {
            if (taso.palautaTiili(i + 20, j + 19).getArvo() == 9) {
                return true;
            }
        }
        return false;
    }

    public boolean voiLiikkuaAlas(int i, int j) {
        if (taso.palautaTiili(i, j + 20).getArvo() == 9) {
            if (taso.palautaTiili(i + 19, j + 20).getArvo() == 9) {
                return true;
            }
        }
        return false;
    }

    public boolean voiLiikkuaYlos(int i, int j) {
        if (taso.palautaTiili(i, j - 2).getArvo() == 9) {
            if (taso.palautaTiili(i + 19, j - 2).getArvo() == 9) {
                return true;
            }
        }
        return false;
    }

}

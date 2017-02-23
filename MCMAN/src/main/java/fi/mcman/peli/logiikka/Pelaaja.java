package fi.mcman.peli.logiikka;

/**
 * Luokka Pelaaja toteuttaa pelissä ohjattavan hahmon toiminnallisuuden ja perii
 * abstraktin luokan Hahmo.
 *
 * @author ljone
 * @see Hahmo
 */
public class Pelaaja extends Hahmo {

    /**
     * Kertoo onko pelaaja elossa.
     */
    private boolean elossa;
    /**
     * Pelaajalla on Taso, joka määrää, voiko pelaaja liikkua tiettyyn
     * suuntaan.
     */
    private Taso taso;

    /**
     * Luo uuden Pelaajan.
     *
     * @param peli Pelaaja liittyy Peliin
     */
    public Pelaaja(Peli peli) {
        super.nimi = "McMan";
        super.x = 340;
        super.y = 380;
        super.peli = peli;
        this.elossa = true;
        this.taso = peli.getTaso();
    }

    public void setNimi(String nimi) {
        if (nimi != null) {
            this.nimi = nimi;
        }
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

    /**
     * Tarkistaa onko pelaaja elossa (boolean onElossa).
     *
     * @return true jos on, false jos ei
     */
    public boolean onElossa() {
        return elossa;
    }

    public void setSuunta(Suunta suunta) {
        if (suunta != null) {
            this.suunta = suunta;
        }
    }

    /**
     * Asettaa booleanin onElossa arvoksi false.
     */
    public void kuolee() {
        this.elossa = false;
    }

    /**
     * Tarkastaa osuuko Pelaaja johonkin pelin burgeriin.
     *
     * @param b Parametrina Burgeri-luokan olio
     * @return true jos osuu, false jos ei
     */
    public boolean osuuBurgeriin(Burgeri b) {
        if (this.x == b.getX() && this.y == b.getY()) {
            return true;
        }
        return false;
    }

    /**
     * Tarkastaa osuuko Pelaaja johonkin pelin Viholliseen.
     *
     * @param v Parametrina Vihollinen-luokan ilmentymä
     * @return true jos osuu, false jos ei.
     */
    public boolean osuuViholliseen(Vihollinen v) {
        if (this.x == v.getX() && this.y == v.getY()) {
            kuolee();
            return true;
        }
        return false;
    }

    /**
     * Liikuttaa pelaajaa jos mahdollista.
     */
    @Override
    public void liiku() {
        if (this.suunta == suunta.VASEN) {
            if (voiLiikkuaVasemmalle(x, y)) {
                this.x -= 2;
            }

        }
        if (this.suunta == suunta.OIKEA) {
            if (voiLiikkuaOikealle(x, y)) {
                this.x += 2;
            }
        }

        if (this.suunta == suunta.ALAS) {
            if (voiLiikkuaAlas(x, y)) {
                this.y += 2;
            }
        }

        if (this.suunta == suunta.YLOS) {
            if (voiLiikkuaYlos(x, y)) {
                this.y -= 2;
            }
        }
    }

    /**
     * Tarkastaa voiko pelaaja liikkua vasemmalle, hyödyntää luokan Taso
     * metodia.
     *
     * @param i pelaajan senhetkinen x-koordinaatti
     * @param j pelaajan senhetkinen y-koordinaatti
     * @return true jos voi, false jos ei
     * @see Taso#palautaTiili(int, int)
     */
    public boolean voiLiikkuaVasemmalle(int i, int j) {
        if (taso.palautaTiili(i - 2, j).getArvo() == 9) {
            if (taso.palautaTiili(i - 2, j + 19).getArvo() == 9) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkastaa voiko pelaaja liikkua oikealle, hyödyntää luokan Taso metodia.
     *
     * @param i pelaajan senhetkinen x-koordinaatti
     * @param j pelaajan senhetkinen y-koordinaatti
     * @return true jos voi, false jos ei
     * @see Taso#palautaTiili(int, int)
     */
    public boolean voiLiikkuaOikealle(int i, int j) {
        if (taso.palautaTiili(i + 20, j).getArvo() == 9) {
            if (taso.palautaTiili(i + 20, j + 19).getArvo() == 9) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkastaa voiko pelaaja liikkua alas, hyödyntää luokan Taso metodia.
     *
     * @param i pelaajan senhetkinen x-koordinaatti
     * @param j pelaajan senhetkinen y-koordinaatti
     * @return true jos voi, false jos ei
     * @see Taso#palautaTiili(int, int)
     */
    public boolean voiLiikkuaAlas(int i, int j) {
        if (taso.palautaTiili(i, j + 20).getArvo() == 9) {
            if (taso.palautaTiili(i + 19, j + 20).getArvo() == 9) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkastaa voiko pelaaja liikkua ylös, hyödyntää luokan Taso metodia.
     *
     * @param i pelaajan senhetkinen x-koordinaatti
     * @param j pelaajan senhetkinen y-koordinaatti
     * @return true jos voi, false jos ei
     * @see Taso#palautaTiili(int, int)
     */
    public boolean voiLiikkuaYlos(int i, int j) {
        if (taso.palautaTiili(i, j - 2).getArvo() == 9) {
            if (taso.palautaTiili(i + 19, j - 2).getArvo() == 9) {
                return true;
            }
        }
        return false;
    }

}

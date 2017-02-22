package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Pelaaja;
import fi.mcman.peli.logiikka.Peli;
import fi.mcman.peli.logiikka.Suunta;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Luokkaa tarvitaan pelaajan liikuttamiseen.
 *
 * @author ljone
 * @see logiikka.Pelaaja#liiku()
 */
public class NappaimistonKuuntelija implements KeyListener {

    private Kayttoliittyma kl;
    private Pelaaja pelaaja;
    private Peli peli;

    /**
     * Luo uuden NappaimistonKuuntelijan.
     *
     * @param kl NappaimistonKuuntelija liittyy Kayttoliittymaan
     */
    public NappaimistonKuuntelija(Kayttoliittyma kl) {
        this.kl = kl;
        this.pelaaja = kl.getPeli().getPelaaja();
        peli = kl.getPeli();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Kuuntelee nuolinäppäimiä ja sen perusteella liikuttaa pelaajaa.
     *
     * @param e Nuolinäppäimen painallus
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (peli.jatkuu()) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (pelaaja.voiLiikkuaVasemmalle(pelaaja.getX(), pelaaja.getY())) {
                    pelaaja.setSuunta(Suunta.VASEN);
                }
                pelaaja.liiku();
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (pelaaja.voiLiikkuaOikealle(pelaaja.getX(), pelaaja.getY())) {
                    pelaaja.setSuunta(Suunta.OIKEA);
                }
                pelaaja.liiku();
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (pelaaja.voiLiikkuaYlos(pelaaja.getX(), pelaaja.getY())) {
                    pelaaja.setSuunta(Suunta.YLOS);
                }
                pelaaja.liiku();
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (pelaaja.voiLiikkuaAlas(pelaaja.getX(), pelaaja.getY())) {
                    pelaaja.setSuunta(Suunta.ALAS);
                }
//            } else if  (pelaaja.voiLiikkuaAlas(pelaaja.getX() + 1, pelaaja.getY())) {
//                pelaaja.setVaihdaAlas(1);
//            } else if  (pelaaja.voiLiikkuaAlas(pelaaja.getX() - 1, pelaaja.getY())) {
//                pelaaja.setVaihdaAlas(1);
//            } else if  (pelaaja.voiLiikkuaAlas(pelaaja.getX() + 2, pelaaja.getY())) {
//                pelaaja.setVaihdaAlas(1);
//            } else if  (pelaaja.voiLiikkuaAlas(pelaaja.getX() - 2, pelaaja.getY())) {
//                pelaaja.setVaihdaAlas(1);
//            }
                pelaaja.liiku();
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                peli.aloita();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

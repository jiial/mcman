package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Pelaaja;
import fi.mcman.peli.logiikka.Suunta;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ljone
 *
 * Luokkaa tarvitaan pelaajan liikuttamiseen.
 * @see logiikka.Pelaaja#liiku()
 *
 */
public class NappaimistonKuuntelija implements KeyListener {

    private Kayttoliittyma kl;
    private Pelaaja pelaaja;

    public NappaimistonKuuntelija(Kayttoliittyma kl) {
        this.kl = kl;
        this.pelaaja = kl.getPeli().getPelaaja();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

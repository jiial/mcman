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
 * @see Pelaaja#liiku()
 */
public class NappaimistonKuuntelija implements KeyListener {

    /**
     * Käyttöliittyma johon näppäimistönkuuntelija liittyy.
     */
    private Kayttoliittyma kl;
    /**
     * Pelaajalle annetaan suunta näppäinten painallusten perusteella.
     */
    private Pelaaja pelaaja;
    /**
     * Näppäimistönkuuntelija liittyy Peliin.
     */
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
                pelaaja.liiku();
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int tekstinPituus = peli.getKl().getAlusta().getNimikentta().getText().length();
                if (tekstinPituus > 0 && tekstinPituus < 13) {
                    pelaaja.setNimi(peli.getKl().getAlusta().getNimikentta().getText());
                    peli.aloita();
                } else if (tekstinPituus == 0) {
                    peli.aloita();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setPelaaja(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }
}

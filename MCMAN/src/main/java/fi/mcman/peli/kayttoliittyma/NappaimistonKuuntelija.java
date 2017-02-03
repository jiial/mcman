
package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Pelaaja;
import fi.mcman.peli.logiikka.Suunta;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            pelaaja.setSuunta(Suunta.VASEN);
            pelaaja.liiku();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pelaaja.setSuunta(Suunta.OIKEA);
            pelaaja.liiku();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pelaaja.setSuunta(Suunta.YLOS);
            pelaaja.liiku();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pelaaja.setSuunta(Suunta.ALAS);
            pelaaja.liiku();
        }
        kl.getAlusta().paivita();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}

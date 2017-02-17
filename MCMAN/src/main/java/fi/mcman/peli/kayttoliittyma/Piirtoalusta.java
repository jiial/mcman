package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Burgeri;
import fi.mcman.peli.logiikka.Paivitettava;
import fi.mcman.peli.logiikka.Pelaaja;
import fi.mcman.peli.logiikka.Peli;
import fi.mcman.peli.logiikka.Suunta;
import fi.mcman.peli.logiikka.Taso;
import fi.mcman.peli.logiikka.Vihollinen;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.*;
import javax.imageio.ImageIO;

/**
 *
 * @author ljone
 *
 * Luokka hoitaa kaikkien komponenttien piirtämisen ruudulle.
 *
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli peli;
    private Pelaaja pelaaja;
    private List<Vihollinen> viholliset;
    private JLabel pisteet;
    private Taso taso;
    private boolean onTaso;

    public Piirtoalusta(Peli peli) {
        this.peli = peli;
        onTaso = false;
        this.taso = peli.getTaso();
        this.pelaaja = peli.getPelaaja();
        this.viholliset = peli.getViholliset();
        this.pisteet = new JLabel();
        pisteet.setForeground(Color.WHITE);
        add(pisteet);
        super.setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        try {
            BufferedImage kuva = ImageIO.read(getClass().getResourceAsStream("/bg3.png"));
            g.drawImage(kuva, 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        piirraPelaaja(g);
        piirraViholliset(g);
        piirraBurgerit(g);
        piirraNalkapalkki(g);

        if (!peli.jatkuu()) {
            ilmoitusKunHaviaa(g);
        }

    }

    @Override
    public void paivita() {
        repaint();
        pisteet.setText("Pisteet: " + peli.getPisteet());
    }

    public void piirraPelaaja(Graphics g) {
        try {
            BufferedImage kuvaV = ImageIO.read(getClass().getResourceAsStream("/pv.png"));
            BufferedImage kuvaO = ImageIO.read(getClass().getResourceAsStream("/po.png"));
            if (pelaaja.getSuunta() == Suunta.VASEN) {
                g.drawImage(kuvaV, pelaaja.getX(), pelaaja.getY(), null);
            } else {
                g.drawImage(kuvaO, pelaaja.getX(), pelaaja.getY(), null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        g.setColor(Color.YELLOW);
//        g.fillOval(pelaaja.getX(), pelaaja.getY(), 20, 20);
    }

    public void piirraViholliset(Graphics g) {
        g.setColor(Color.red);
        for (Vihollinen v : peli.getViholliset()) {
            g.fill3DRect(v.getX(), v.getY(), 20, 20, true);
        }
    }

    public void piirraBurgerit(Graphics g) {
        try {
            BufferedImage kuva = ImageIO.read(getClass().getResourceAsStream("/burgeri.png"));
            for (Burgeri b : peli.getBurgerit()) {
                if (!b.isSyoty()) {
                    g.drawImage(kuva, b.getX(), b.getY(), null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        g.setColor(Color.orange);
//        for (Burgeri b : peli.getBurgerit()) {
//            if (!b.isSyoty()) {
//                g.fillOval(b.getX() + 5, b.getY() + 5, 10, 10);
//            }
//
//        }
    }

    public void piirraNalkapalkki(Graphics g) {
        g.setColor(Color.WHITE);
        int alkuX = 30;
        int alkuY = 530;
        g.drawRect(alkuX - 2, alkuY - 2, 388, 19);
        if (peli.getNalka() < 500) {
            g.setColor(Color.GREEN);
        } else if (peli.getNalka() < 1000) {
            g.setColor(Color.orange);
        } else {
            g.setColor(Color.RED);
        }
        int neliot = peli.getNalka() / 20;
        if (peli.getNalka() < 5) {
            g.fillRect(alkuX, alkuY, 1, 15);
        } else {
            for (int i = 0; i < neliot; i++) {
                g.fillRect(alkuX, alkuY, 15, 15);
                alkuX += 5;
            }
        }
        g.setColor(Color.WHITE);
        Font fontti = new Font("Arial", Font.PLAIN, 20);
        g.setFont(fontti);
        g.drawString("Nälkä", 30, 525);
    }

    public void ilmoitusKunHaviaa(Graphics g) {
        Font ripFontti = new Font("Century Gothic", Font.PLAIN, 90);
        g.setFont(ripFontti);
        g.drawString("R I P", 150, 310);
    }

}

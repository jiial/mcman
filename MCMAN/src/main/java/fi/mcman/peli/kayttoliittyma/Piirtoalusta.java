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
 * Luokka hoitaa kaikkien komponenttien piirtämisen ruudulle.
 *
 * @author ljone
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli peli;
    private Pelaaja pelaaja;
    private List<Vihollinen> viholliset;
    private JLabel pisteet;
    private Taso taso;
    private boolean onTaso;

    /**
     * Luo uuden Piirtoalustan.
     *
     * @param peli Piirtoalusta liittyy peliin.
     */
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

    /**
     * Piirtää pelin komponentit hyödyntäen eri komponenttien piirtämismetodeja.
     *
     * @param g -
     * @see piirraPelaaja(Graphics g), piirraViholliset(Graphics g),
     * piirraBurgerit(Graphics g), piirraNalkapalkki(Graphics g)
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!peli.jatkuu()) {
            pisteet.setVisible(false);
            tulostaHighscoret(g);
        } else {
            if (peli.onAloitettu()) {
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
                    if (peli.voittiko()) {
                        ilmoitusKunVoittaa(g);
                    } else {
                        ilmoitusKunHaviaa(g);
                    }
                }
            } else {
                piirraAlkunaytto(g);
            }
        }
    }

    /**
     * Piirtää pelin komponentit uudelleen sekä päivittää JLabel:in sisällön
     * (pisteet).
     *
     * @see paintComponent(Graphics g)
     */
    @Override
    public void paivita() {
        repaint();
        pisteet.setText("Pisteet: " + peli.getPisteet());
    }

    /**
     * Piirtää pelaajan suunnan perusteella (oikea, vasen, alas, ylös)
     * koordinaattien osoittamaan paikkaan.
     *
     * @param g -
     * @see Graphics#drawImage(java.awt.Image, int, int,
     * java.awt.image.ImageObserver)
     */
    public void piirraPelaaja(Graphics g) {
        try {
            BufferedImage kuvaV = ImageIO.read(getClass().getResourceAsStream("/pv.png"));
            BufferedImage kuvaO = ImageIO.read(getClass().getResourceAsStream("/po.png"));
            BufferedImage kuvaA = ImageIO.read(getClass().getResourceAsStream("/pa.png"));
            BufferedImage kuvaY = ImageIO.read(getClass().getResourceAsStream("/py.png"));
            if (pelaaja.getSuunta() == Suunta.VASEN) {
                g.drawImage(kuvaV, pelaaja.getX(), pelaaja.getY(), null);
            } else if (pelaaja.getSuunta() == Suunta.OIKEA) {
                g.drawImage(kuvaO, pelaaja.getX(), pelaaja.getY(), null);
            } else if (pelaaja.getSuunta() == Suunta.ALAS) {
                g.drawImage(kuvaA, pelaaja.getX(), pelaaja.getY(), null);
            } else {
                g.drawImage(kuvaY, pelaaja.getX(), pelaaja.getY(), null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        g.setColor(Color.YELLOW);
//        g.fillOval(pelaaja.getX(), pelaaja.getY(), 20, 20);
    }

    /**
     * Piirtää pelin viholliset koordinaattien osoittamaan paikkaan.
     *
     * @param g -
     */
    public void piirraViholliset(Graphics g) {
        g.setColor(Color.red);
        for (Vihollinen v : peli.getViholliset()) {
            g.fill3DRect(v.getX(), v.getY(), 20, 20, true);
        }
    }

    /**
     * Piirtää pelin burgerit niiden koordinaattien osoittamaan paikkaan.
     *
     * @param g -
     */
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

    /**
     * Piirtää nälkäpalkin, jonka pituus määräytyy Pelaajan senhetkisen nälän
     * mukaan.
     *
     * @param g -
     */
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

    /**
     * Piirtää ilmoituksen ruudulle kun Pelaaja häviää pelin.
     *
     * @param g -
     */
    public void ilmoitusKunHaviaa(Graphics g) {
        Font ripFontti = new Font("Century Gothic", Font.PLAIN, 90);
        g.setFont(ripFontti);
        g.drawString("R I P", 150, 310);
    }

    /**
     * Piirtää ilmoituksen ruudulle kun Pelaaja voittaa pelin.
     *
     * @param g -
     */
    public void ilmoitusKunVoittaa(Graphics g) {
        Font ripFontti = new Font("Century Gothic", Font.PLAIN, 90);
        g.setFont(ripFontti);
        g.drawString("VOITIT PELIN!", 150, 310);
    }

    public void piirraAlkunaytto(Graphics g) {
        Font ripFontti = new Font("Arial", Font.PLAIN, 50);
        g.setFont(ripFontti);
        g.drawString("Paina ENTER", 120, 250);
        g.drawString("aloittaaksesi pelin", 65, 310);
    }

    public void tulostaHighscoret(Graphics g) {
        String[] t = peli.annaHighscoret();
        Font ripFontti = new Font("Arial", Font.PLAIN, 28);
        g.setFont(ripFontti);
        g.setColor(Color.WHITE);
        int x = 220;
        int y = 100;
        for (String s : t) {
            if (s != null) {
                g.drawString(s, x, y);
            }
            y += 50;
        }
    }
}

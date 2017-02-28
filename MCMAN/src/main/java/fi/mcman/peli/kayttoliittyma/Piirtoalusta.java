package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Burgeri;
import fi.mcman.peli.logiikka.HighscoreTulos;
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
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JTextField;

/**
 * Luokka hoitaa kaikkien komponenttien piirtämisen ruudulle.
 *
 * @author ljone
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    /**
     * Piirtoalustaan liittyvä Peli.
     */
    private Peli peli;
    /**
     * Piirtoalusta tuntee Pelaajan voidakseen piirtää sen.
     */
    private Pelaaja pelaaja;
    /**
     * Piirtoalusta tuntee Viholliset voidakseen piirtää ne.
     */
    private List<Vihollinen> viholliset;
    /**
     * JTextField johon syötetään nimi ennen pelin alkua.
     */
    private JTextField nimikentta;

    /**
     * Luo uuden Piirtoalustan.
     *
     * @param peli Piirtoalusta liittyy peliin.
     */
    public Piirtoalusta(Peli peli) {
        this.peli = peli;
        this.pelaaja = peli.getPelaaja();
        this.viholliset = peli.getViholliset();
        this.nimikentta = new JTextField();
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
            tulostaHighscoret(g);
        } else {
            if (peli.onAloitettu()) {
                nimikentta.setEnabled(false);
                nimikentta.setVisible(false);
                remove(nimikentta);
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
                g.setFont(new Font("Arial", Font.PLAIN, 18));
                g.drawString("Pisteet: " + peli.getPisteet(), 205, 16);
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
    public void paivita() {;
        repaint();
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

    /**
     * Piirtää alkunäytön/valikon ennen varsinaisen pelin alkua.
     *
     * @param g -
     */
    public void piirraAlkunaytto(Graphics g) {
        try {
            BufferedImage kuva = ImageIO.read(getClass().getResourceAsStream("/mcman.png"));
            g.drawImage(kuva, 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Font f = new Font("Arial", Font.BOLD, 30);
        g.setFont(f);
        g.setColor(Color.white);
        g.drawString("Anna nimesi:", 140, 450);
        g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
        g.drawString("Nimimerkin tulee olla 0-12 merkkiä pitkä", 60, 470);
        g.setFont(f);
        g.drawString("Paina ENTER", 140, 530);
        g.drawString("aloittaaksesi pelin", 90, 555);
        nimikentta.setBounds(140, 475, 220, 30);
        nimikentta.setBackground(Color.getHSBColor(9, 58, 76));
        nimikentta.setFont(f);
        nimikentta.addKeyListener(peli.getKl().getK());
        add(nimikentta);
    }

    /**
     * Tulostaa Highscoret ruudulle pelin päätyttyä.
     *
     * @param g -
     */
    public void tulostaHighscoret(Graphics g) {
        ArrayList<HighscoreTulos> t = peli.annaHighscoret();
        Font f = new Font("Arial", Font.PLAIN, 28);
        g.setFont(f);
        g.setColor(Color.WHITE);
        int x = 150;
        int y = 120;
        if (!t.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                if (t.size() >= i + 1) {
                    System.out.println(t.get(i).getPisteet());
                    g.drawString((i + 1) + ". " + t.get(i).getSisalto(), x, y);
                }
                y += 50;
            }
        }
    }

    public JTextField getNimikentta() {
        return this.nimikentta;
    }
}

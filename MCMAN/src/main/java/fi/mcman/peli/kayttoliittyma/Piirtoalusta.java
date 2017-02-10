package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Burgeri;
import fi.mcman.peli.logiikka.Paivitettava;
import fi.mcman.peli.logiikka.Pelaaja;
import fi.mcman.peli.logiikka.Peli;
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
//        if (onTaso == false) {
//            taso.piirra(g);
//        }
//        onTaso = true;
        try {
            BufferedImage kuva = ImageIO.read(getClass().getResourceAsStream("/bg2.png"));
            g.drawImage(kuva, 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pelaaja.piirra(g);
        for (Vihollinen v : viholliset) {
            v.piirra(g);
        }
        for (Burgeri b : peli.getBurgerit()) {
            b.piirra(g);
        }
        if (peli.getNalka() < 200) {
            g.setColor(Color.GREEN);
        } else if (peli.getNalka() < 375) {
            g.setColor(Color.orange);
        } else {
            g.setColor(Color.RED);
        }
        int neliot = peli.getNalka() / 10;
        int alkuX = 30;
        int alkuY = 530;
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
        g.drawString("Nälkä", 30, 520);

        if (!peli.jatkuu()) {
            Font ripFontti = new Font("Century Gothic", Font.PLAIN, 90);
            g.setFont(ripFontti);
            g.drawString("R I P", 210, 270);
        }

    }

    @Override
    public void paivita() {
        repaint();
        pisteet.setText("Pisteet: " + peli.getPisteet());
    }

}

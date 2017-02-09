package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Burgeri;
import fi.mcman.peli.logiikka.Paivitettava;
import fi.mcman.peli.logiikka.Pelaaja;
import fi.mcman.peli.logiikka.Peli;
import fi.mcman.peli.logiikka.Vihollinen;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli peli;
    private Pelaaja pelaaja;
    private List<Vihollinen> viholliset;
    private JLabel pisteet;

    public Piirtoalusta(Peli peli) {
        this.peli = peli;
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
        if (peli.getNalka() < 5) {

        } else {
            int alkuX = 30;
            int alkuY = 530;
            for (int i = 0; i < neliot; i++) {
                g.fillRect(alkuX, alkuY, 15, 15);
                alkuX += 5;
            }
        }

    }

    @Override
    public void paivita() {
        repaint();
        pisteet.setText("Pisteet: " + peli.getPisteet());
    }

}

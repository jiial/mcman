package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Peli;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * 
 * @author ljone
 * 
 * Toimii päärunkona graafiselle käyttöliittymälle.
 * Luo tarvittavat komponentit ja hoitaa niiden asetukset ja yhteydet kuntoon.
 * 
 */

public class Kayttoliittyma implements Runnable {

    private Peli peli;
    private Piirtoalusta alusta;
    private JFrame frame;

    public Kayttoliittyma(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void run() {
        frame = new JFrame("MCMAN");
        frame.setPreferredSize(new Dimension(600, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        this.alusta = new Piirtoalusta(peli);
        this.peli.setPaivitettava(alusta);
        container.add(alusta);
        
        frame.addKeyListener(new NappaimistonKuuntelija(this));

    }

    public JFrame getFrame() {
        return frame;
    }

    public Piirtoalusta getAlusta() {
        return alusta;
    }

    public Peli getPeli() {
        return peli;
    }

}

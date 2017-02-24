package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Peli;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Toimii päärunkona graafiselle käyttöliittymälle sekä luo tarvittavat
 * komponentit ja hoitaa niiden asetukset ja yhteydet kuntoon.
 *
 * @author ljone
 */
public class Kayttoliittyma implements Runnable {

    /**
     * Peli johon käyttöliittyma liittyy.
     */
    private Peli peli;
    /**
     * Piirtoalusta joka piirtää käyttöliittymän komponentit.
     */
    private Piirtoalusta alusta;
    /**
     * Pelin frame.
     */
    private JFrame frame;
    /**
     * Kuuntelee näppäimistöä.
     */
    private NappaimistonKuuntelija k;
    
    /**
     * Luo uuden Kayttoliittyman.
     *
     * @param peli Kayttoliittyma liittyy peliin.
     */
    public Kayttoliittyma(Peli peli) {
        this.peli = peli;
    }

    /**
     * Luo JFramen ja liittää siihen tarvittavat komponentit metodilla
     * luoKomponentit.
     *
     * @see luoKomponentit(Container container)
     */
    @Override
    public void run() {
        frame = new JFrame("MCMAN");
        frame.setPreferredSize(new Dimension(511, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo Piirtoalustan ja NappaimistonKuuntelijan ja lisää ne käyttöliittymän
     * runkoon.
     *
     * @param container Kayttoliittyman JFrame
     */
    public void luoKomponentit(Container container) {
        this.alusta = new Piirtoalusta(peli);
        this.peli.setPaivitettava(alusta);
        container.add(alusta);
        this.k = new NappaimistonKuuntelija(this);
        frame.addKeyListener(k);
        frame.setFocusable(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public NappaimistonKuuntelija getK() {
        return k;
    }
    
    public Piirtoalusta getAlusta() {
        return alusta;
    }

    public Peli getPeli() {
        return peli;
    }

}

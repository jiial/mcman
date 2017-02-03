package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Paivitettava;
import fi.mcman.peli.logiikka.Pelaaja;
import fi.mcman.peli.logiikka.Peli;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable, Paivitettava {

    private Peli peli;
    private Piirtoalusta alusta;
    private JFrame frame;
    private Pelaaja pelaaja;

    public Kayttoliittyma(Peli peli) {
        this.peli = peli;
        this.pelaaja = peli.getPelaaja();
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
        this.alusta = new Piirtoalusta(peli, peli.getViholliset().get(0));
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

    @Override
    public void paivita() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    

}

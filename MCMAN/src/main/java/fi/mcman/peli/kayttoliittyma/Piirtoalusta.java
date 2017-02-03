
package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Paivitettava;
import fi.mcman.peli.logiikka.Pelaaja;
import fi.mcman.peli.logiikka.Peli;
import fi.mcman.peli.logiikka.Vihollinen;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel implements Paivitettava {
    
    private Peli peli;
    private Pelaaja pelaaja;
    private Vihollinen vihollinen;
    
    public Piirtoalusta(Peli peli, Vihollinen vihu) {
        this.peli = peli;
        this.pelaaja = peli.getPelaaja();
        this.vihollinen = vihu;
        super.setBackground(Color.BLACK);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pelaaja.piirra(g);
        vihollinen.piirra(g);
        peli.getBurgerit().get(0).piirra(g);
    }
    

    @Override
    public void paivita() {
        repaint();
    }
    
    public void paivitaPelaaja(Graphics g) {
         pelaaja.piirra(g);
    }
    
    @Override
    public void paint(Graphics g) {
        paintComponent(g);
    }

    @Override
    public Graphics getGraphics() {
        return super.getGraphics(); //To change body of generated methods, choose Tools | Templates.
    }
 
    
    
}

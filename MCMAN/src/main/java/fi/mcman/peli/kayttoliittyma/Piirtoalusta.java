
package fi.mcman.peli.kayttoliittyma;

import fi.mcman.peli.logiikka.Paivitettava;
import fi.mcman.peli.logiikka.Pelaaja;
import fi.mcman.peli.logiikka.Peli;
import fi.mcman.peli.logiikka.Vihollinen;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel implements Paivitettava {
    
    private Peli peli;
    private Pelaaja pelaaja;
    private List<Vihollinen> viholliset;
    
    public Piirtoalusta(Peli peli) {
        this.peli = peli;
        this.pelaaja = peli.getPelaaja();
        this.viholliset = peli.getViholliset();
        super.setBackground(Color.BLACK);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pelaaja.piirra(g);
        peli.getBurgerit().get(0).piirra(g);
        for (Vihollinen v : viholliset) {
            v.piirra(g);
        }
    }
    

    @Override
    public void paivita() {
        repaint();
    }
   
}

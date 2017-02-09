package fi.mcman.peli;

import fi.mcman.peli.kayttoliittyma.Kayttoliittyma;
import fi.mcman.peli.logiikka.Peli;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Peli peli = new Peli();
        
        Kayttoliittyma liittyma = new Kayttoliittyma(peli);
        SwingUtilities.invokeLater(liittyma);
        
        while(liittyma.getAlusta() == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu");
            }
        }

        peli.setPaivitettava(liittyma.getAlusta());
        peli.start();
        
    }

}

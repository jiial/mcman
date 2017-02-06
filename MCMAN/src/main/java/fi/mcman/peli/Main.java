package fi.mcman.peli;

import fi.mcman.peli.kayttoliittyma.Kayttoliittyma;
import fi.mcman.peli.logiikka.Peli;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Peli peli = new Peli();
        
        Kayttoliittyma liittyma = new Kayttoliittyma(peli);
        peli.setPaivitettava(liittyma.getAlusta());
        SwingUtilities.invokeLater(liittyma);
//        liittyma.run();

        peli.start();
        
    }

}

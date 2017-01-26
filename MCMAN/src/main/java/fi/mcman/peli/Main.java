package fi.mcman.peli;

import fi.mcman.peli.logiikka.Pelaaja;
import fi.mcman.peli.logiikka.Peli;

public class Main {

    public static void main(String[] args) {
        Peli peli = new Peli();
        Pelaaja pelaaja = peli.getPelaaja();
        if (pelaaja.onElossa()) {
            System.out.println("Elossa!");
        }
    }

}

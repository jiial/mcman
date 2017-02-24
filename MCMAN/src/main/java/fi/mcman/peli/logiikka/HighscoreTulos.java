/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.mcman.peli.logiikka;

/**
 * Kuvaa Highscores-tiedostoon tallennettavaa riviä, jossa pelaajan nimi ja
 * pisteet.
 *
 * @author ljone
 */
public class HighscoreTulos implements Comparable<HighscoreTulos> {

    /**
     * Tulosrivin sisältö Stringinä.
     */
    private String sisalto;
    /**
     * HighscoreTulokseen liittyvät pisteet.
     */
    private int pisteet;

    /**
     * Luo uuden HighscoreTuloksen.
     * @param s parametrina Pelin lukijalta saatu Highscores-tiedoston rivi.
     */
    public HighscoreTulos(String s) {
        this.sisalto = s;
        this.pisteet = Integer.parseInt(s.substring(0, 3));
    }

    public String getSisalto() {
        return sisalto;
    }

    public int getPisteet() {
        return pisteet;
    }

    public void setPisteet(int pisteet) {
        this.pisteet = pisteet;
    }

    public void setSisalto(String sisalto) {
        this.sisalto = sisalto;
    }
    
    @Override
    public int compareTo(HighscoreTulos h) {
        if (pisteet >= h.getPisteet()) {
            return -1;
        }
        return 1;
    }

}

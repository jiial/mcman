package fi.mcman.peli.logiikka;

import fi.mcman.peli.kayttoliittyma.Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.Timer;

/**
 * Tämä luokka sisältää suuren osan ohjelman peruslogiikasta ja
 * toiminnallisuudesta.
 *
 * @author ljone
 */
public class Peli extends Timer implements ActionListener {

    /**
     * Peli tallentaa burgerit listaan.
     */
    private List<Burgeri> burgerit;
    /**
     * Peli tallentaa viholliset listaan.
     */
    private List<Vihollinen> viholliset;
    /**
     * Pelillä on aina yksi pelaaja.
     */
    private Pelaaja pelaaja;
    /**
     * Pelialusta määrittää pelialueen koon.
     */
    private Pelialusta alusta;
    /**
     * Kertoo voittiko pelaaja pelin eli keräsikö kaikki pisteet.
     */
    private boolean voittiko;
    /**
     * Peli päivittää Paivitettavaa 18 millisekunnin välein.
     */
    private Paivitettava paivitettava;
    /**
     * Peliin liittyvä käyttöliittymä.
     */
    private Kayttoliittyma kl;
    /**
     * Muuttuja pitää kirjaa syötyjen burgereiden määrästä.
     */
    private int pisteet;
    /**
     * Peli kasvattaa pelaajan nälkää joka päivityskerralla yhdellä. Burgerin
     * syöminen vähentää nälkää.
     */
    private int nalka;
    /**
     * Peliin liittyy Taso, jossa kentän oleellinen data.
     */
    private Taso taso;
    /**
     * Kertoo onko peli aloitettu (vai ollaanko vielä aloitusvalikossa).
     */
    private boolean aloitettu;
    /**
     * Kirjoittaa Highscoret ylös Highscores.txt-tekstitiedostoon.
     */
    private FileWriter kirjoittaja;
    /**
     * Lukee Highscoreja.
     */
    private Scanner lukija;
    /**
     * Sisältää highscoret.
     */
    private ArrayList<HighscoreTulos> hs;

    /**
     * Luo uuden pelin ja samalla siihen uuden tason, pelialustan, pelaajan,
     * viholliset ja burgerit, sekä alustaa loput luokan Peli muuttujat.
     */
    public Peli() {
        super(18, null);
        this.taso = new Taso();
        this.alusta = new Pelialusta(500, 500);
        this.pelaaja = new Pelaaja(this);
        this.viholliset = new ArrayList<>();
        this.burgerit = new ArrayList<>();
        this.voittiko = false;
        this.pisteet = 0;
        this.nalka = 0;
        aloitettu = false;
        this.hs = new ArrayList<>();

        addActionListener(this);
        luoViholliset();
        luoBurgerit();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("Highscores.txt");
            File f = new File("src/main/resources/Highscores.txt");
            kirjoittaja = new FileWriter(f, true);
            lukija = new Scanner(is);
        } catch (Exception e) {
            System.out.println("Highscoreja ei löydy.");
        }

    }

    public void setPaivitettava(Paivitettava p) {
        this.paivitettava = p;
    }

    public void setNalka(int nalka) {
        this.nalka = nalka;
    }

    public void setKl(Kayttoliittyma kl) {
        this.kl = kl;
    }

    public Pelialusta getAlusta() {
        return alusta;
    }

    public Paivitettava getPaivitettava() {
        return paivitettava;
    }

    public Taso getTaso() {
        return taso;
    }

    public void aloita() {
        aloitettu = true;
    }

    public boolean voittiko() {
        return voittiko;
    }

    public boolean onAloitettu() {
        return aloitettu;
    }

    public List<Burgeri> getBurgerit() {
        return burgerit;
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public List<Vihollinen> getViholliset() {
        return viholliset;
    }

    public int getPisteet() {
        return pisteet;
    }

    public Kayttoliittyma getKl() {
        return kl;
    }

    /**
     * Metodi jatkuu() tarkistaa ensin onko pelaaja elossa. Sitten tarkistetaan
     * onko pelissä vielä burgereita jäljellä. Lisäksi tarkastetaan ettei nälkä
     * ole kasvanut liian suureksi.
     *
     * @see Pelaaja#onElossa()
     * @see onBurgereita()
     * @see this.nalka
     * @return Jos edellä olevat ehdot menevät läpi peli jatkuu eli palautetaan
     * true. Jos ensimmäinen ehto menee läpi mutta seuraava ei, asetetaan
     * muuttujan voittiko arvoksi true ja palautetaan false. Peli ei siis jatku
     * mutta tiedämme että pelaaja voitti koska oli vielä elossa. Muulloin
     * palautetaan false (pelaaja häviää).
     */
    public boolean jatkuu() {
        if (this.pelaaja.onElossa()) {
            if (onBurgereita()) {
                if (nalka < 1500) {
                    return true;
                }
            } else {
                voittiko = true;
                return false;
            }
        }
        return false;
    }

    /**
     * Metodi onBurgereita() käy pelin burgerit läpi ja tarkistaa onko vielä
     * syömättömiä.
     *
     * @see Burgeri
     * @return true jos on, false jos ei ole
     */
    public boolean onBurgereita() {
        for (Burgeri b : this.burgerit) {
            if (!b.isSyoty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi luoViholliset() nimensä mukaan alustaa pelin viholliset.
     *
     * @see Vihollinen
     */
    public void luoViholliset() {
        this.viholliset.add(new Vihollinen("1", 20, 20, this));
        this.viholliset.add(new Vihollinen("2", 20, 460, this));
        this.viholliset.add(new Vihollinen("3", 460, 20, this));
        this.viholliset.add(new Vihollinen("4", 460, 460, this));
        this.viholliset.add(new Vihollinen("5", 140, 200, this));
    }

    /**
     * Metodi luoBurgerit() alustaa pelin burgerit.
     *
     * @see Burgeri
     */
    public void luoBurgerit() {
        int[] kentta = taso.getKENTTA();
        int jakaja = 1;
        int y = 0;
        int x = 0;
        for (int i = 0; i < kentta.length; i++) {
            if (x == 25) {
                x = 0;
                y++;
            }
            if (kentta[i] == 9) {
                if (jakaja >= 1) {
                    if (y % 2 != 0) {
                        this.burgerit.add(new Burgeri(20 * x, 20 * y));
                        jakaja = -1;
                    }
                }
            }
            x++;
            jakaja++;
        }
    }

    public int getNalka() {
        return nalka;
    }

//    public int getPaivitaKertaalleen() {
//        return paivitaKertaalleen;
//    }
    /**
     * Metodi päivittää peliä ajastimen mukaan. Peliä päivitetään 30
     * millisekunnin välein. Jos metodi jatkuu() palauttaa false, peli loppuu
     * (kutsu return). Nalka kasvaa jokaisessa päivityksessä yhdellä (täydet
     * 1500 kestää siis 27 sekuntia). Tarkistetaan osuuko pelaaja johonkin
     * burgeriin (jota ei ole vielä syöty). Jos osuu, kasvatetaan pisteitä
     * yhdellä ja vähennetään nälkää 60:llä. Tarkistetaan osuuko pelaaja
     * johonkin viholliseen. Jos osuu, pelaaja kuolee (metodi kuolee()) ja peli
     * loppuu seuraavan päivityksen alussa. Kutsutaan kaikille pelin
     * vihollisille metodia liiku(). Lopuksi kutsutaan paivitettavalle
     * (Piirtoalusta luokan olio joka piirtää pelin komponentit) metodia
     * paivita(), joka piirtaa uuden tilanteen.
     *
     * @param e parametri saadaan perittyjen luokkien Timer ja ActionListener
     * avulla
     * @see Vihollinen#liiku()
     * @see jatkuu()
     * @see Paivitettava
     * @see Piirtoalusta#paivita()
     * @see Pelaaja#kuolee()
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (aloitettu) {
            if (!jatkuu()) {
                paivitaHighscoret();
                try {
                    long startTime = System.currentTimeMillis();
                    long elapsedTime = 0L;
                    while (elapsedTime < 1500) {
                        elapsedTime = (new Date()).getTime() - startTime;
                    }
                    paivitettava.paivita();
                } catch (Exception ex) {
                    System.out.println("Viiven asetus ei onnistu.");
                }
                stop();
                return;
            }
            nalka++;
            for (Burgeri b : burgerit) {
                if (pelaaja.osuuBurgeriin(b)) {
                    if (!b.isSyoty()) {
                        b.setSyoty(true);
                        pisteet++;
                        nalka -= 60;
                        if (nalka < 0) {
                            nalka = 0;
                        }
                    }
                }
            }
            for (Vihollinen v : viholliset) {
                if (pelaaja.osuuViholliseen(v)) {
                    pelaaja.kuolee();
                }
            }
            for (Vihollinen v : this.viholliset) {
                v.liiku();
            }
            if (jatkuu()) {
                this.paivitettava.paivita();
            }
        }
    }

    public void setPisteet(int pisteet) {
        this.pisteet = pisteet;
    }

    /**
     * Päivittää pelin tuloksen Highscores-tiedostoon.
     */
    public void paivitaHighscoret() {
        if (!lukija.hasNextLine()) {
            kirjoitaTiedostoon();
        } else {
            ArrayList<HighscoreTulos> lista = annaHighscoret();
            if (lista.size() < 5) {
                kirjoitaTiedostoon();
                lisaaNykyinenTulos();
            } else {
                for (HighscoreTulos t : lista) {
                    if (pisteet > t.getPisteet()) {
                        kirjoitaTiedostoon();
                        lisaaNykyinenTulos();
                        break;
                    }
                }
            }
        }
        try {
            kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("Kirjoittajan sulkemisessa virhe.");
        }
    }

    /**
     * Päivittää uusimman tuloksen listalle.
     */
    public void lisaaNykyinenTulos() {
        if (pisteet >= 100) {
            hs.add(new HighscoreTulos(pisteet + " " + pelaaja.getNimi() + "\n"));
        } else if (pisteet >= 10) {
            hs.add(new HighscoreTulos("0" + pisteet + " " + pelaaja.getNimi() + "\n"));
        } else {
            hs.add(new HighscoreTulos("00" + pisteet + " " + pelaaja.getNimi() + "\n"));
        }
    }

    /**
     * Hakee viisi parhainta tulosta Highscoreista.
     *
     * @return palauttaa viisi parasta tulosta ArrayListina.
     */
    public ArrayList<HighscoreTulos> annaHighscoret() {
        while (lukija.hasNextLine()) {
            String s = lukija.nextLine();
            if (!s.isEmpty()) {
                hs.add(new HighscoreTulos(s));
            }
        }
        Collections.sort(hs);
        ArrayList<HighscoreTulos> a = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (hs.size() >= i + 1) {
                a.add(hs.get(i));
            }
        }
        return a;
    }

    /**
     * Kirjoittaa uuden Highscore-tuloksen tiedostoon.
     */
    public void kirjoitaTiedostoon() {
        System.out.println("joira");
        try {
            if (pisteet >= 100) {
                kirjoittaja.write(pisteet + " " + pelaaja.getNimi() + "\n");
            } else if (pisteet >= 10) {
                kirjoittaja.write("0" + pisteet + " " + pelaaja.getNimi() + "\n");
            } else {
                kirjoittaja.write("00" + pisteet + " " + pelaaja.getNimi() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Tiedostoon kirjoittaminen ei onnistunut.");
            e.printStackTrace();
        }
    }

}

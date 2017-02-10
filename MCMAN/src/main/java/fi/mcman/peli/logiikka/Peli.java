package fi.mcman.peli.logiikka;

import fi.mcman.peli.kayttoliittyma.Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

/**
 * 
 * @author ljone
 * 
 * Tämä luokka sisältää suuren osan ohjelman peruslogiikasta ja toiminnallisuudesta.
 * 
 */

public class Peli extends Timer implements ActionListener {

    private List<Burgeri> burgerit;
    private List<Vihollinen> viholliset;
    private Pelaaja pelaaja;
    private Pelialusta alusta;
    private boolean voittiko;
    private Paivitettava paivitettava;
    private Kayttoliittyma kl;
    private int pisteet;
    private int nalka;
    private Taso taso;

    public Peli() {
        super(30, null);
        this.taso = new Taso();
        this.alusta = new Pelialusta(500, 500);
        this.pelaaja = new Pelaaja(this);
        this.viholliset = new ArrayList<>();
        this.burgerit = new ArrayList<>();
        this.voittiko = false;
        this.pisteet = 0;
        this.nalka = 0;

        addActionListener(this);
        luoViholliset();
        luoBurgerit();

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

    public Taso getTaso() {
        return taso;
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

    /**
     * Metodi jatkuu() tarkistaa ensin onko pelaaja elossa.
     * @see Pelaaja#onElossa() 
     * 
     * Sitten tarkistetaan onko pelissä vielä burgereita jäljellä.
     * @see onBurgereita()
     * 
     * Lisäksi tarkastetaan vielä ettei nälkä ole kasvanut liian suureksi.
     * @see this.nalka
     * 
     * Jos edellä olevat ehdot menevät läpi peli jatkuu eli palautetaan true.
     * Jos ensimmäinen ehto menee läpi mutta seuraava ei, asetetaan muuttujan voittiko arvoksi true ja palautetaan false.
     * Peli ei siis jatku mutta tiedämme että pelaaja voitti koska oli vielä elossa.
     * Muulloin palautetaan false (pelaaja häviää).
     * @return returnit selitetty yllä
     */
    
    public boolean jatkuu() {
        if (this.pelaaja.onElossa()) {
            if (onBurgereita()) {
                if (nalka < 500) {
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
     * Metodi onBurgereita() käy pelin burgerit läpi ja tarkistaa onko vielä syömättömiä.
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
     * @see Vihollinen
     */

    public void luoViholliset() {
        this.viholliset.add(new Vihollinen("1", 100, 100, this));
        this.viholliset.add(new Vihollinen("2", 400, 100, this));
        this.viholliset.add(new Vihollinen("3", 100, 400, this));
        this.viholliset.add(new Vihollinen("4", 400, 400, this));
    }
    
    /**
     * Metodi luoBurgerit() alustaa pelin burgerit.
     * @see Burgeri
     */

    public void luoBurgerit() {
        for (int x = 50; x < 500; x += 50) {
            for (int y = 50; y < 500; y += 50) {
                this.burgerit.add(new Burgeri(x, y));
            }
        }
    }

    public int getNalka() {
        return nalka;
    }
    
    /**
     * Metodi päivittää peliä ajastimen mukaan.
     * 
     * @param e parametri saadaan perittyjen luokkien Timer ja ActionListener avulla.
     * 
     * Peliä päivitetään 30 millisekunnin välein.
     * Jos metodi jatkuu() palauttaa false, peli loppuu (kutsu return).
     * @see jatkuu()
     * 
     * Nalka kasvaa 0,03 sekunnin välein yhdellä (täydet 500 kestää siis 15 sekuntia).
     * Tarkistetaan osuuko pelaaja johonkin burgeriin (jota ei ole vielä syöty).
     * Jos osuu, kasvatetaan pisteitä yhdellä ja vähennetään nälkää 50:llä.
     * Tarkistetaan osuuko pelaaja johonkin viholliseen.
     * Jos osuu, pelaaja kuolee (metodi kuolee()) ja peli loppuu seuraavan päivityksen alussa.
     * Kutsutaan kaikille pelin vihollisille metodia liiku().
     * @see Vihollinen#liiku()
     * 
     * Kutsutaan paivitettavalle (Piirtoalusta luokan olio joka piirtää pelin komponentit) metodia paivita(), joka piirtaa uuden tilanteen.
     * @see Paivitettava, kayttoliittyma.Piirtoalusta#paivita()
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jatkuu()) {
            return;
        }
        nalka++;
        for (Burgeri b : burgerit) {
            if (pelaaja.osuuBurgeriin(b)) {
                if (!b.isSyoty()) {
                    b.setSyoty(true);
                    pisteet++;
                    if (pisteet < 0) {
                        pisteet = 0;
                    }
                    nalka -= 50;
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
        this.paivitettava.paivita();
    }

}

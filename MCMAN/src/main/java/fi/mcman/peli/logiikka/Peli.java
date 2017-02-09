package fi.mcman.peli.logiikka;

import fi.mcman.peli.kayttoliittyma.Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

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

    public Peli() {
        super(30, null);
        this.alusta = new Pelialusta(500, 500);
        this.pelaaja = new Pelaaja(this);
        this.viholliset = new ArrayList<>();
        this.burgerit = new ArrayList<>();
        this.voittiko = false;
        this.pisteet = 0;

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

    public boolean onBurgereita() {
        for (Burgeri b : this.burgerit) {
            if (!b.isSyoty()) {
                return true;
            }
        }
        return false;
    }

    public void luoViholliset() {
        this.viholliset.add(new Vihollinen("1", 100, 100, this));
        this.viholliset.add(new Vihollinen("2", 400, 100, this));
        this.viholliset.add(new Vihollinen("3", 100, 400, this));
        this.viholliset.add(new Vihollinen("4", 400, 400, this));
    }

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

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

    public Peli() {
        super(40, null);
        this.alusta = new Pelialusta(500, 500);
        this.pelaaja = new Pelaaja(this);
        this.voittiko = false;
        this.viholliset = new ArrayList<>();
        this.burgerit = new ArrayList<>();
        burgerit.add(new Burgeri(100, 100));

        addActionListener(this);
        luoViholliset();

    }

    public void setPaivitettava(Paivitettava p) {
        this.paivitettava = p;
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

    public boolean jatkuuko() {
        if (this.pelaaja.onElossa()) {
            if (onBurgereita()) {
                return true;
            } else {
                voittiko = true;
                return false;
            }
        } else {
            return false;
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jatkuuko()) {
            return;
        }
        for (Vihollinen v : this.viholliset) {
            v.liiku();
        }
        this.paivitettava.paivita();
    }

}

package fi.mcman.peli.logiikka;

import fi.mcman.peli.kayttoliittyma.Kayttoliittyma;
import java.util.*;

public class Peli {

    private List<Burgeri> burgerit;
    private List<Vihollinen> viholliset;
    private Pelaaja pelaaja;
    private Pelialusta alusta;
    private boolean voittiko;
    private Kayttoliittyma kl;

    public Peli() {
        this.alusta = new Pelialusta(600, 600);
        this.pelaaja = new Pelaaja(this);
        this.voittiko = false;
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

    public void setPelaaja(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }

    public void setViholliset(List<Vihollinen> viholliset) {
        this.viholliset = viholliset;
    }

    public void setBurgerit(List<Burgeri> burgerit) {
        this.burgerit = burgerit;
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
    
    public void kaynnista() {
        
    }

}

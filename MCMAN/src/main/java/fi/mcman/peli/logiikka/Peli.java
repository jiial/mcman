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
        super(100, null);
        this.alusta = new Pelialusta(600, 600);
        this.pelaaja = new Pelaaja(this);
        this.voittiko = false;
        this.viholliset = new ArrayList<>();
        this.burgerit = new ArrayList<>();
        burgerit.add(new Burgeri(100, 100));
        
        addActionListener(this);
        
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
    
    
    public void lisaaVihollinen(String nimi, int x, int y, Peli peli) {
        this.viholliset.add(new Vihollinen(nimi, x, y, peli));
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

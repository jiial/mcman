package fi.mcman.peli.logiikka;

import java.util.Random;

/**
 * Luokkassa pelin vihollisten toiminnallisuus. Luokan tärkein ominaisuus on
 * tekoäly jonka mukaan vihollinen liikkuu. Perii abstraktin luokan Hahmo
 *
 * @author ljone
 * @see liiku()
 * @see valitseSuunta()
 * @see arvoSuunta()
 */
public class Vihollinen extends Hahmo {

    /**
     * Asettavat rajat vihollisen liikkumiselle.
     */
    private int pieninX, suurinX, pieninY, suurinY;
    /**
     * Vihollisella on kohteena pelaaja.
     */
    private Pelaaja kohde;
    /**
     * Arpoo lukuja joiden perusteella vihollinen valitsee suunnan.
     */
    private Random arpoja;
    /**
     * Määrittää kuinka pitkään vihollinen liikkuu valittuun suuntaan.
     */
    private int suunnanMuutos;
    /**
     * Tason avulla tarkistetaan voidaanko valittuun suuntaan liikkua.
     */
    private Taso taso;

    /**
     * Luo uuden vihollisen saamiensa parametrien mukaan ja alustaa muuttujat.
     *
     * @param nimi Vihollisen nimi, yksilöi vihollisen
     * @param x Vihollisen x-koordinaatti alussa
     * @param y Vihollisen y-koordinaatti alussa
     * @param peli Parametrina peli johon vihollinen liittyy.
     */
    public Vihollinen(String nimi, int x, int y, Peli peli) {
        super.nimi = nimi;
        super.x = x;
        super.y = y;
        super.peli = peli;
        this.kohde = super.peli.getPelaaja();
        this.arpoja = new Random();
        this.pieninX = 20;
        this.pieninY = 20;
        this.suurinX = 480;
        this.suurinY = 480;
        this.suunnanMuutos = 0;
        taso = peli.getTaso();
    }

    public void setArpoja(Random arpoja) {
        this.arpoja = arpoja;
    }

    public void setPieninX(int pieninX) {
        this.pieninX = pieninX;
    }

    public void setPieninY(int pieninY) {
        this.pieninY = pieninY;
    }

    public void setSuurinX(int suurinX) {
        this.suurinX = suurinX;
    }

    public void setSuurinY(int suurinY) {
        this.suurinY = suurinY;
    }

    public void setKohde(Pelaaja kohde) {
        this.kohde = kohde;
    }

    public void setSuunta(Suunta suunta) {
        this.suunta = suunta;
    }

    public void setSuunnanMuutos(int suunnanMuutos) {
        this.suunnanMuutos = suunnanMuutos;
    }

    public int getSuunnanMuutos() {
        return suunnanMuutos;
    }

    public String getNimi() {
        return nimi;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Suunta getSuunta() {
        return suunta;
    }

    /**
     * Valitsee ensin suunnan joko arpomalla tai pelaajan mukaan jos riittävän
     * lähellä pelaajaa. Sitten liikkuu valittuun suuntaan jos mahdollista.
     *
     * @see Suunta
     * @see valitseSuunta()
     */
    @Override
    public void liiku() {
        valitseSuunta();
        if (suunta == Suunta.VASEN) {
            if (this.x > this.pieninX) {
                if (voiLiikkua()) {
                    this.x--;
                } else {
                    voiMuuttaaAlasTaiYlos();
                }
            }
        }
        if (suunta == Suunta.OIKEA) {
            if (this.x < this.suurinX) {
                if (voiLiikkua()) {
                    this.x++;
                } else {
                    voiMuuttaaAlasTaiYlos();
                }
            }
        }
        if (suunta == Suunta.ALAS) {
            if (this.y < this.suurinY) {
                if (voiLiikkua()) {
                    this.y++;
                } else {
                    voiMuuttaaVasemmalleTaiOikealle();
                }
            }
        }
        if (suunta == Suunta.YLOS) {
            if (this.y > this.pieninY) {
                if (voiLiikkua()) {
                    this.y--;
                } else {
                    voiMuuttaaVasemmalleTaiOikealle();
                }
            }
        }
    }

    /**
     * Valitsee suunnan johon vihollinen liikkuu. Hyödyntää useita muita
     * metodeja päätöksenteossa.
     *
     * @see arvoSuunta()
     * @see valitseSuuntaJosPelaajaTahtaimessa()
     * @see valitseSuuntaJosPelaajaLahettyvilla()
     */
    public void valitseSuunta() {
        if (kohde.getX() == this.x || kohde.getY() == this.y) {
            if (suunnanMuutos > 0) {
                suunnanMuutos--;
            } else {
                valitseSuuntaJosPelaajaTahtaimessa();
            }
        } else {
            if (!voiLiikkua()) {
                arvoSuunta();
            } else if (suunnanMuutos > 0) {
                if (suunta == suunta.ALAS) {
                    if (!voiMuuttaaVasemmalleTaiOikealle()) {
                        suunta = suunta.ALAS;
                        suunnanMuutos--;
                        return;
                    }
                } else if (suunta == suunta.YLOS) {
                    if (!voiMuuttaaVasemmalleTaiOikealle()) {
                        suunta = suunta.YLOS;
                        suunnanMuutos--;
                        return;
                    }
                } else if (suunta == suunta.VASEN) {
                    if (!voiMuuttaaAlasTaiYlos()) {
                        suunta = suunta.VASEN;
                        suunnanMuutos--;
                        return;
                    }
                } else if (suunta == suunta.OIKEA) {
                    if (!voiMuuttaaAlasTaiYlos()) {
                        suunta = suunta.OIKEA;
                        suunnanMuutos--;
                        return;
                    }
                } else {
                    valitseSuuntaJosPelaajaLahettyvilla();
                }
            }
        }
    }

    /**
     * Vihollisen suunta valitaan tämän metodin mukaan jos pelaaja on lähellä
     * vihollista muttei kuitenkaan samassa x- tai y-koordinaatissa.
     *
     * @see arvoSuunta()
     */
    public void valitseSuuntaJosPelaajaLahettyvilla() {
        if (kohde.getX() - this.x <= 180 && kohde.getX() - this.x > 0) {
            this.suunta = Suunta.OIKEA;
            if (voiLiikkua() == false) {
                arvoSuunta();
            }
        } else if (kohde.getX() - this.x < 0 && kohde.getX() - this.x >= -180) {
            this.suunta = Suunta.VASEN;
            if (voiLiikkua() == false) {
                arvoSuunta();
            }
        } else if (kohde.getY() - this.y <= 180 && kohde.getY() - this.y > 0) {
            this.suunta = Suunta.ALAS;
            if (voiLiikkua() == false) {
                arvoSuunta();
            }
        } else if (kohde.getY() - this.y < 0 && kohde.getY() - this.y >= -180) {
            this.suunta = Suunta.YLOS;
            if (voiLiikkua() == false) {
                arvoSuunta();
            }
        } else {
            arvoSuunta();
        }
    }

    /**
     * Vihollisen suunta valitaan tämän metodin perusteella jos vihollisen ja
     * pelaajan x- tai y-koordinaatti on sama.
     *
     * @see arvoSuunta()
     */
    public void valitseSuuntaJosPelaajaTahtaimessa() {
        if (kohde.getX() == this.x) {
            if (kohde.getY() - this.y <= 200 && kohde.getY() - this.y > 0) {
                this.suunta = Suunta.ALAS;
                if (voiLiikkua() == false) {
                    this.suunta = suunta.OIKEA;
                    if (!voiLiikkua()) {
                        suunta = suunta.VASEN;
                    }
                    suunnanMuutos = 160;
                }
            } else if (kohde.getY() - this.y < 0 && kohde.getY() - this.y >= -200) {
                this.suunta = Suunta.YLOS;
                if (voiLiikkua() == false) {
                    this.suunta = suunta.VASEN;
                    if (!voiLiikkua()) {
                        suunta = suunta.OIKEA;
                    }
                    suunnanMuutos = 160;
                }
            } else {
                arvoSuunta();
            }
        } else if (kohde.getY() == this.y) {
            if (kohde.getX() - this.x <= 200 && kohde.getX() - this.x >= 0) {
                this.suunta = Suunta.OIKEA;
                if (voiLiikkua() == false) {
                    this.suunta = suunta.ALAS;
                    if (!voiLiikkua()) {
                        suunta = suunta.YLOS;
                    }
                    suunnanMuutos = 160;
                }
            } else if (kohde.getX() - this.x < 0 && kohde.getX() - this.x >= -200) {
                this.suunta = Suunta.VASEN;
                if (!voiLiikkua()) {
                    this.suunta = suunta.YLOS;
                    if (!voiLiikkua()) {
                        suunta = suunta.ALAS;
                    }
                    suunnanMuutos = 160;
                }
            } else {
                arvoSuunta();
            }
        } else {
            arvoSuunta();
        }
    }

    /**
     * Arpoo suunnan viholliselle. Arvontoja suoritetaan niin kauan kunnes
     * löydetään suunta johon voi liikkua.
     */
    public void arvoSuunta() {
        while (true) {
            int luku = this.arpoja.nextInt(4);
            if (luku == 0) {
                this.suunta = suunta.VASEN;
                suunnanMuutos = 160;
            }
            if (luku == 1) {
                this.suunta = suunta.OIKEA;
                suunnanMuutos = 160;
            }
            if (luku == 2) {
                this.suunta = suunta.ALAS;
                suunnanMuutos = 160;
            }
            if (luku == 3) {
                this.suunta = suunta.YLOS;
                suunnanMuutos = 160;
            }
            if (voiLiikkua()) {
                break;
            }
        }
    }

    /**
     * Tarkastaa voiko vihollinen liikkua senhetkiseen suuntaansa.
     *
     * @return true jos voi, false jos ei
     */
    public boolean voiLiikkua() {
        if (this.suunta == suunta.VASEN) {
            if (taso.palautaTiili(x - 1, y).getArvo() == 9) {
                if (taso.palautaTiili(x - 1, y + 19).getArvo() == 9) {
                    return true;
                }
            }
        } else if (this.suunta == suunta.OIKEA) {
            if (taso.palautaTiili(x + 20, y).getArvo() == 9) {
                if (taso.palautaTiili(x + 20, y + 19).getArvo() == 9) {
                    return true;
                }
            }
        } else if (this.suunta == suunta.ALAS) {
            if (taso.palautaTiili(x, y + 20).getArvo() == 9) {
                if (taso.palautaTiili(x + 19, y + 20).getArvo() == 9) {
                    return true;
                }
            }
        } else if (this.suunta == suunta.YLOS) {
            if (taso.palautaTiili(x, y - 1).getArvo() == 9) {
                if (taso.palautaTiili(x + 19, y - 1).getArvo() == 9) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Tarkastaa, voiko suuntaa vaihtaa alas tai ylös. Tarkoituksena saada
     * vihollinen kääntyilemään useammin ja näin liikkumaan kattavammin ja
     * epäsäännöllisemmin pitkin pelialuetta. Hyödyntää metodia voiLiikkua().
     *
     * @return true jos voi, false jos ei
     * @see voiLiikkua()
     */
    public boolean voiMuuttaaAlasTaiYlos() {
        suunta = suunta.ALAS;
        if (voiLiikkua()) {
            return true;
        }
        suunta = suunta.YLOS;
        if (voiLiikkua()) {
            return true;
        }
        return false;
    }

    /**
     * Tarkastaa, voiko suuntaa vaihtaa vasemmalle tai oikealle. Tarkoituksena
     * saada vihollinen kääntyilemään useammin ja näin liikkumaan kattavammin ja
     * epäsäännöllisemmin pitkin pelialuetta. Hyödyntää metodia voiLiikkua().
     *
     * @return true jos voi, false jos ei
     * @see voiLiikkua()
     */
    public boolean voiMuuttaaVasemmalleTaiOikealle() {
        suunta = suunta.OIKEA;
        if (voiLiikkua()) {
            return true;
        }
        suunta = suunta.VASEN;
        if (voiLiikkua()) {
            return true;
        }
        return false;
    }
}

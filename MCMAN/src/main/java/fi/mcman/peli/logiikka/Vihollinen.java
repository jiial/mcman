package fi.mcman.peli.logiikka;

import java.util.Random;

/**
 *
 * @author ljone
 *
 * Luokkassa pelin vihollisten toiminnallisuus. Luokan tärkein ominaisuus on
 * tekoäly jonka mukaan vihollinen liikkuu.
 * @see liiku() valitseSuunta() arvoSuunta()
 *
 * Perii abstraktin luokan Hahmo.
 *
 */
public class Vihollinen extends Hahmo {

    private int pieninX, suurinX, pieninY, suurinY;
    private Pelaaja kohde;
    private Random arpoja;
    private int suunnanMuutos;
    private Taso taso;

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

    @Override
    public void siirra(int x, int y) {
        this.x = x;
        this.y = y;
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

    public String getNimi() {
        return nimi;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public void liiku() {
        valitseSuunta();
        if (suunta == Suunta.VASEN) {
            if (this.x > this.pieninX) {
                if (voiLiikkua()) {
                    this.x--;
                }
            }
        }
        if (suunta == Suunta.OIKEA) {
            if (this.x < this.suurinX) {
                if (voiLiikkua()) {
                    this.x++;
                }
            }
        }
        if (suunta == Suunta.ALAS) {
            if (this.y < this.suurinY) {
                if (voiLiikkua()) {
                    this.y++;
                }
            }
        }
        if (suunta == Suunta.YLOS) {
            if (this.y > this.pieninY) {
                if (voiLiikkua()) {
                    this.y--;
                }
            }
        }
    }

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

    public void valitseSuuntaJosPelaajaTahtaimessa() {
        if (kohde.getX() == this.x) {
            if (kohde.getY() - this.y <= 100 && kohde.getY() - this.y > 0) {
                this.suunta = Suunta.ALAS;
                if (voiLiikkua() == false) {
                    this.suunta = suunta.OIKEA;
                    if (!voiLiikkua()) {
                        suunta = suunta.VASEN;
                    }
                    suunnanMuutos = 160;
                }
            } else if (kohde.getY() - this.y < 0 && kohde.getY() - this.y >= -100) {
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
            if (kohde.getX() - this.x <= 100 && kohde.getX() - this.x >= 0) {
                this.suunta = Suunta.OIKEA;
                if (voiLiikkua() == false) {
                    this.suunta = suunta.ALAS;
                    if (!voiLiikkua()) {
                        suunta = suunta.YLOS;
                    }
                    suunnanMuutos = 160;
                }
            } else if (kohde.getX() - this.x < 0 && kohde.getX() - this.x >= -100) {
                this.suunta = Suunta.VASEN;
                if (voiLiikkua() == false) {
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

//    public boolean onTahtaimessa(int a, int b) {
//        if (a - b > -5 && a - b < 5) {
//            return true;
//        }
//        return false;
//
//    }
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

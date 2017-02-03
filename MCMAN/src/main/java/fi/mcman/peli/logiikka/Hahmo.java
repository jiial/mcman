
package fi.mcman.peli.logiikka;


public abstract class Hahmo {
    protected String nimi;
    protected int x, y;
    protected Suunta suunta;
    protected Peli peli;
    
    public abstract void siirra(int x, int y);
    
    public abstract void liiku();
    
}

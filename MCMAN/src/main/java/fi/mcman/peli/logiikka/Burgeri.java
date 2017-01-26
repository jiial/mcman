
package fi.mcman.peli.logiikka;

public class Burgeri implements Paivitettava {
    private int x;
    private int y;
    private boolean syoty;

    public Burgeri(int x, int y) {
        this.x = x;
        this.y = y;
        this.syoty = false;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public boolean isSyoty() {
        return syoty;
    }

    public void setSyoty(boolean syoty) {
        this.syoty = syoty;
    }

    @Override
    public void paivita() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    
    
}

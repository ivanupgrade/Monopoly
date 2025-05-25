package CLASES;

public abstract class Casilla implements Efectos{

    private int posicion;

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Casilla(int posicion) {
        this.posicion = posicion;
    }
}

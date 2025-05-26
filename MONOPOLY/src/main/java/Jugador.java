import java.util.ArrayList;

public class Jugador {
    private int id;
    private int p_ganadas;
    private String nombre;
    private int posicion;
    private int dinero;
    private ArrayList<Calle> calles;
    private boolean encarcelado;

    public Jugador(){}

    public Jugador(String nombre) {
        this.nombre = nombre;
        calles = new ArrayList<>();
        encarcelado = false;
        dinero = 1500;
    }

    public Jugador(int id, int p_ganadas, String nombre, int posicion, int dinero, ArrayList<Calle> calles, boolean encarcelado) {
        this.id = id;
        this.p_ganadas = p_ganadas;
        this.nombre = nombre;
        this.posicion = posicion;
        this.dinero = dinero;
        this.calles = calles;
        this.encarcelado = encarcelado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getP_ganadas() {
        return p_ganadas;
    }

    public void setP_ganadas(int p_ganadas) {
        this.p_ganadas = p_ganadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public ArrayList<Calle> getCalles() {
        return calles;
    }

    public void setCalles(ArrayList<Calle> calles) {
        this.calles = calles;
    }

    public boolean isEncarcelado() {
        return encarcelado;
    }

    public void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }

    public void mover (int num){
        int casilla_fin = posicion + num;

        if (casilla_fin < 28){
            posicion = casilla_fin;
        } else {
            casilla_fin -= 28;
            posicion = casilla_fin;
            System.out.println("Has pasado por la casilla de salida: Recibes 200€");
            dinero += 200;
        }
    }

}

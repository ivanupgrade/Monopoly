import java.util.ArrayList;

public class Jugador {
    private int id;
    private int p_ganadas;
    private String nombre;
    private int posicion;
    private int dinero;
    private ArrayList<Calle> calles;
    private boolean encarcelado;

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

    public void mover (int num){
        int casilla_fin = posicion + num;

        if (casilla_fin < 28){
            posicion = casilla_fin;
        } else {
            casilla_fin -= 28;
            posicion = casilla_fin;
            System.out.println("Has pasado por la casilla de salida: Recibes 200$");
            dinero += 200;
        }
    }
}

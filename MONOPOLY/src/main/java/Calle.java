public abstract class Calle extends Casilla{

    private String nombre;
    private int alquiler;
    private int precio;

    public Calle(int posicion, String nombre, int alquiler, int precio) {
        super(posicion);
        this.nombre = nombre;
        this.alquiler = alquiler;
        this.precio = precio;
    }

    public abstract void modificarAlquiler(Jugador jugador);
}

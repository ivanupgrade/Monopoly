package CLASES;

/**
 * Class representing a station square in the game.
 */
public class Estacion extends Calle{

    /**
     * Constructor for the Estacion class.
     * @param posicion position of the station on the board
     * @param nombre name of the station
     * @param precio price of the station
     * @param dueño owner of the station
     */
    public Estacion(int posicion, String nombre, int precio, Jugador dueño) {
        super(posicion, nombre, 25, precio, dueño);
    }

    /**
     * method to modify the rent of the station based on the number of stations owned by the player.
     * @param jugador the player who owns the property
     */
    @Override
    public void modificarAlquiler(Jugador jugador) {
        setAlquiler(25);
        for (Calle calle : jugador.getCalles()) {
            if (calle instanceof Estacion) {
                if (calle != this) {
                    setAlquiler(getAlquiler() * 2);
                }
            }
        }
    }
}

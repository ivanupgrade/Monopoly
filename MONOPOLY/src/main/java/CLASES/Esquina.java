package CLASES;

/**
 * Class representing a corner square in the game.
 */
public class Esquina extends Casilla {

    private String tipo;

    /**
     * Constructor for the Esquina class.
     * @param posicion position of the corner square on the board
     * @param tipo type of the corner square (e.g., "ve_carcel")
     */
    public Esquina(int posicion, String tipo) {
        super(posicion);
        this.tipo = tipo;
    }

    /**
     * Default constructor for the Esquina class.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the type of the corner square.
     * @param tipo type of the corner square
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Applies the effect of the corner square on the player.
     * If the type is "ve_carcel", it sets the player's position to 7 and marks them as imprisoned.
     * @param jugador the player affected by the corner square
     * @param partida the game instance
     */
    @Override
    public void aplicarEfecto(Jugador jugador, Partida partida) {
        if (tipo.equalsIgnoreCase("ve_carcel")){
            jugador.setPosicion(7);
            jugador.setEncarcelado(true);
        }
    }

    /**
     * Returns a string representation of the Esquina object.
     * @return a string containing the position and type of the corner square
     */
    @Override
    public String toString() {
        return "Esquina{" +
                super.getPosicion()+
                ", tipo='" + tipo + '\'' +
                '}';
    }
}

package CLASES;

/**
 * Class representing a corner square in the game.
 */
public class Suerte extends Casilla {

    /**
     * Constructor for the Suerte class.
     * @param posicion position of the corner square on the board
     */
    public Suerte(int posicion) {
        super(posicion);
    }

    /**
     * Applies the effect of the Suerte square on the player.
     * @param jugador the player on whom the effect is applied
     * @param partida the current game instance
     */
    @Override
    public void aplicarEfecto(Jugador jugador, Partida partida) {
        CartaSuerte carta = partida.getMazoRobo().robarCarta();
        carta.aplicarEfecto(jugador, partida);
        partida.getMazoDescarte().getBaraja().add(carta);
    }

}

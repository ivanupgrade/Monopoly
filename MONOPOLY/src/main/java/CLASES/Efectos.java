package CLASES;

/**
 * interface Efectos that defines the method to apply effects on a player during the game.
 */
public interface Efectos {

    /**
     * Method to apply an effect on a player during the game.
     * @param jugador the player on whom the effect is applied
     * @param partida the current game instance
     */
    void aplicarEfecto(Jugador jugador, Partida partida);
}

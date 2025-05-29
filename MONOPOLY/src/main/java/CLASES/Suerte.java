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

    @Override
    public void aplicarEfecto(Jugador jugador, Partida partida) {
        if (partida.getMazoRobo().getBaraja().isEmpty()) {
            partida.getMazoRobo().reponerBaraja(partida.getMazoDescarte());
        }
        CartaSuerte carta = partida.getMazoRobo().robarCarta();
        carta.aplicarEfecto(jugador, partida);
        partida.getMazoDescarte().getBaraja().add(carta);
    }

}

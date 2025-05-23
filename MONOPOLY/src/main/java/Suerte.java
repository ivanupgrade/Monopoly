public class Suerte extends Casilla {

    public Suerte(int posicion) {
        super(posicion);
    }

    @Override
    public void aplicarEfecto(Jugador jugador, Partida partida) {
        CartaSuerte carta = partida.getMazoRobo().robarCarta();
        carta.aplicarEfecto(jugador, partida);
        partida.getMazoDescarte().getBaraja().add(carta);
    }
}

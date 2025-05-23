public class Esquina extends Casilla {

    private String tipo;

    public Esquina(int posicion, String tipo) {
        super(posicion);
        this.tipo = tipo;
    }

    @Override
    public void aplicarEfecto(Jugador jugador, Partida partida) {

    }
}

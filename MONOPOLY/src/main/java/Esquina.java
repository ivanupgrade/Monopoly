public class Esquina extends Casilla implements Efectos{

    private String tipo;

    public Esquina(int posicion, String tipo) {
        super(posicion);
        this.tipo = tipo;
    }

    @Override
    public void aplicarEfecto(Partida partida) {

    }
}

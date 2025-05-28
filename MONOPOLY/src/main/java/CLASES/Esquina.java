package CLASES;

public class Esquina extends Casilla {

    private String tipo;

    public Esquina(int posicion, String tipo) {
        super(posicion);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void aplicarEfecto(Jugador jugador, Partida partida) {
        if (tipo.equals("ve_carcel")){
            jugador.setPosicion(7);
            jugador.setEncarcelado(true);
        }
    }

    @Override
    public String toString() {
        return "Esquina{" +
                super.getPosicion()+
                ", tipo='" + tipo + '\'' +
                '}';
    }
}

package CLASES;

public class Estacion extends Calle{

    public Estacion(int posicion, String nombre, int precio, Jugador dueño) {
        super(posicion, nombre, 25, precio, dueño);
    }

    @Override
    public void modificarAlquiler(Jugador jugador) {
        setAlquiler(25);
        for (Calle calle : jugador.getCalles()){
            if (calle instanceof Estacion){
                setAlquiler(getAlquiler()*2);
            }
        }
    }
}

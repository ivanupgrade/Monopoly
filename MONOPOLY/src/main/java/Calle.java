import java.util.Scanner;

public abstract class Calle extends Casilla{

    private String nombre;
    private int alquiler;
    private int precio;
    private Jugador dueño;

    public Calle(int posicion, String nombre, int alquiler, int precio) {
        super(posicion);
        this.nombre = nombre;
        this.alquiler = alquiler;
        this.precio = precio;
    }

    @Override
    public void aplicarEfecto(Jugador jugador, Partida partida) {
        if (!jugador.getCalles().contains(this)){
            Scanner scanner = new Scanner(System.in);
            if (dueño == null){
                System.out.println("¿Quieres comprar esta propiedad? (s/n)");
                String respuesta = scanner.nextLine().toLowerCase();
                if (respuesta.equals("s")){
                    if (jugador.getDinero()>precio){
                        jugador.setDinero(jugador.getDinero()-precio);
                        jugador.getCalles().add(this);
                        dueño = jugador;

                    } else {
                        System.out.println("No puedes pagar esta propiedad");
                    }
                }

            } else {
                jugador.setDinero(jugador.getDinero()-alquiler);
                dueño.setDinero(dueño.getDinero()+alquiler);
            }
        }
    }

    public abstract void modificarAlquiler(Jugador jugador);
}

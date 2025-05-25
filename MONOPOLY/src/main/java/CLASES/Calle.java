package CLASES;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Jugador getDueño() {
        return dueño;
    }

    public void setDueño(Jugador dueño) {
        this.dueño = dueño;
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
                modificarAlquiler(dueño);
                jugador.setDinero(jugador.getDinero()-alquiler);
                dueño.setDinero(dueño.getDinero()+alquiler);
            }
        }
    }

    public abstract void modificarAlquiler(Jugador jugador);
}

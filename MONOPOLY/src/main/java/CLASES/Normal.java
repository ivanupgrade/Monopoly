package CLASES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Normal extends Calle{

    private String color;
    private int numCasas;
    private int precioEdificio;
    private Integer[] tablaPrecios;

    public Normal(int posicion, String nombre, int precio, String color, int numCasas, int precioEdificio, Integer[] tablaPrecios, Jugador dueño) {
        super(posicion, nombre, tablaPrecios[numCasas], precio, dueño);
        this.color = color;
        this.numCasas = numCasas;
        this.precioEdificio = precioEdificio;
        this.tablaPrecios = tablaPrecios;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumCasas() {
        return numCasas;
    }

    public void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }

    public int getPrecioEdificio() {
        return precioEdificio;
    }

    public void setPrecioEdificio(int precioEdificio) {
        this.precioEdificio = precioEdificio;
    }

    public ArrayList<Integer> getTablaPrecios() {
        return new ArrayList<>(Arrays.asList(tablaPrecios));

    }

    public void setTablaPrecios(Integer[] tablaPrecios) {
        this.tablaPrecios = tablaPrecios;
    }

    @Override
    public void modificarAlquiler(Jugador jugador) {
        setAlquiler(tablaPrecios[numCasas]);
    }

    public void construirCasa (Jugador jugador){
        int contador=0;
        for (int i = 0; i < jugador.getCalles().size(); i++) {
            if (color.equalsIgnoreCase(((Normal) jugador.getCalles().get(i)).getColor())) {
                contador++;

            }
        }
        if (contador==2) {
            if (numCasas < 5) {
                if (jugador.getDinero() > precioEdificio) {
                    jugador.setDinero(jugador.getDinero()-precioEdificio);
                    numCasas++;
                    modificarAlquiler(jugador);
                }else {
                    System.out.println("Dinero insuficiente");
                }

            }else {
                System.out.println("Has alcanzado el numero máximo de casas");
            }
        }else {
            System.out.println("No tienes todas las propiedades de este color");
        }

    }

    @Override
    public String toString() {
        return "Normal{" +
                super.getNombre()+
                ", color='" + color + '\'' +
                '}';
    }
}

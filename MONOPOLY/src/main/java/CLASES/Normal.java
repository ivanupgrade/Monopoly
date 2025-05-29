package CLASES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The Normal class represents a normal property in the game.
 * It extends the Calle class and includes additional attributes such as color, number of houses, building price, and a price table.
 * It provides methods to modify rent, build houses, and display property information.
 */
public class Normal extends Calle{

    private String color;
    private int numCasas;
    private int precioEdificio;
    private Integer[] tablaPrecios;

    /**
     * Constructor for the Normal class.
     * @param posicion position of the property on the board
     * @param nombre name of the property
     * @param precio price of the property
     * @param color color of the property
     * @param numCasas number of houses on the property
     * @param precioEdificio price to build a house on the property
     * @param tablaPrecios array containing rent prices based on the number of houses
     * @param dueño owner of the property
     */
    public Normal(int posicion, String nombre, int precio, String color, int numCasas, int precioEdificio, Integer[] tablaPrecios, Jugador dueño) {
        super(posicion, nombre, tablaPrecios[numCasas], precio, dueño);
        this.color = color;
        this.numCasas = numCasas;
        this.precioEdificio = precioEdificio;
        this.tablaPrecios = tablaPrecios;
    }

    /**
     * returns the color of the property.
     * @return color of the property
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the property.
     * @param color color of the property
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * returns the number of houses on the property.
     * @return number of houses
     */
    public int getNumCasas() {
        return numCasas;
    }

    /**
     * Sets the number of houses on the property.
     * @param numCasas number of houses
     */
    public void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }

    /**
     * returns the price to build a house on the property.
     * @return price of building a house
     */
    public int getPrecioEdificio() {
        return precioEdificio;
    }

    /**
     * Sets the price to build a house on the property.
     * @param precioEdificio price of building a house
     */
    public void setPrecioEdificio(int precioEdificio) {
        this.precioEdificio = precioEdificio;
    }

    /**
     * returns the price table for the property.
     * @return an ArrayList containing the rent prices based on the number of houses
     */
    public ArrayList<Integer> getTablaPrecios() {
        return new ArrayList<>(Arrays.asList(tablaPrecios));

    }

    /**
     * Sets the price table for the property.
     * @param tablaPrecios an array containing the rent prices based on the number of houses
     */
    public void setTablaPrecios(Integer[] tablaPrecios) {
        this.tablaPrecios = tablaPrecios;
    }

    /**
     * Modifies the rent of the property based on the number of houses.
     * This method updates the rent according to the price table defined for the property.
     * @param jugador the player who owns the property
     */
    @Override
    public void modificarAlquiler(Jugador jugador) {
        setAlquiler(tablaPrecios[numCasas]);
    }

    /**
     * Builds a house on the property if the player owns all properties of the same color and has enough money.
     * It checks if the player has two properties of the same color before allowing them to build a house.
     * If successful, it updates the player's money and the number of houses on the property.
     * @param jugador the player who wants to build a house
     */
    public void construirCasa (Jugador jugador){
        int contador=0;
        for (int i = 0; i < jugador.getCalles().size(); i++) {
            if (jugador.getCalles().get(i) instanceof Normal){
                if (color.equalsIgnoreCase(((Normal) jugador.getCalles().get(i)).getColor())) {
                    contador++;
                }
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

    /**
     * Returns a string representation of the Normal object.
     * @return A string containing the property name, color, and other details.
     */
    @Override
    public String toString() {
        return "Normal{" +
                super.toString()+
                ", color='" + color + '\'' +
                '}';
    }
}

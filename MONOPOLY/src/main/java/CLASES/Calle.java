package CLASES;

import java.util.Scanner;

/**
 * Abstract class representing a property in the game.
 * It contains common attributes and methods for properties such as name, rent, price, and owner.
 * The class also defines the effect of landing on the property, which can either be buying it or paying rent.
 */
public abstract class Calle extends Casilla{

    private String nombre;
    private int alquiler;
    private int precio;
    private Jugador dueño;

    /**
     * Constructor for the Calle class.
     * @param posicion position of the property on the board
     * @param nombre name of the property
     * @param alquiler rent amount for the property
     * @param precio price of the property
     * @param dueño owner of the property
     */
    public Calle(int posicion, String nombre, int alquiler, int precio, Jugador dueño) {
        super(posicion);
        this.nombre = nombre;
        this.alquiler = alquiler;
        this.precio = precio;
        this.dueño = dueño;
    }

    /**
     * Returns the name of the property.
     * @return name of the property
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the property.
     * @param nombre name of the property
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Returns the rent of the property.
     * @return rent amount
     */
    public int getAlquiler() {
        return alquiler;
    }

    /**
     * Sets the rent of the property.
     * @param alquiler rent amount
     */
    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

    /**
     * Returns the price of the property.
     * @return price amount
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Sets the price of the property.
     * @param precio price amount
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Returns the owner of the property.
     * @return owner of the property
     */
    public Jugador getDueño() {
        return dueño;
    }

    /**
     * Sets the owner of the property.
     * @param dueño owner of the property
     */
    public void setDueño(Jugador dueño) {
        this.dueño = dueño;
    }

    /**
     * Applies the effect of the property on the player.
     * If the property is not owned, it offers the player to buy it.
     * If it is owned, it charges rent to the player.
     * @param jugador the player who landed on the property
     * @param partida the game instance
     */
    @Override
    public void aplicarEfecto(Jugador jugador, Partida partida) {
        if (!jugador.getCalles().contains(this)){
            Scanner scanner = new Scanner(System.in);
            if (dueño == null){
                if (this instanceof Normal) {
                    System.out.println("Esta propiedad esta libre y cuesta "+precio+" su alquiler es de: "+alquiler+" "+ ((Normal) this).getTablaPrecios());
                } else {
                    System.out.println("Esta propiedad esta libre y cuesta "+precio+" su alquiler es de: "+alquiler);
                }

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

                if (dueño != jugador){
                    jugador.setDinero(jugador.getDinero()-alquiler);
                    System.out.println("Has pagado "+alquiler+" de alquiler");
                    dueño.setDinero(dueño.getDinero()+alquiler);
                }
            }
        }
    }

    /**
     * Modifies the rent of the property based on the player's properties.
     * This method should be implemented by subclasses to define specific rent logic.
     * @param jugador the player who owns the property
     */
    public abstract void modificarAlquiler(Jugador jugador);

    /**
     * Returns a string representation of the property.
     * @return string representation of the property
     */
    @Override
    public String toString() {
        return "Calle{" +
                "precio=" + precio +
                ", alquiler=" + alquiler +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

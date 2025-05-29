package CLASES;

import java.util.ArrayList;

/**
 * The Jugador class represents a player in the game.
 * It contains attributes such as id, name, position, money, owned properties, and whether the player is imprisoned.
 * It also provides methods to move the player and manage their properties.
 */
public class Jugador {
    private int id;
    private int p_ganadas;
    private String nombre;
    private int posicion;
    private int dinero;
    private ArrayList<Calle> calles;
    private boolean encarcelado;

    /**
     * Default constructor that initializes the player with default values.
     */
    public Jugador(){}

    /**
     * Constructor that initializes the player with a name.
     * @param nombre the name of the player
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        calles = new ArrayList<>();
        encarcelado = false;
    }

    /**
     * Constructor that initializes the player with all attributes.
     * @param id the unique identifier of the player
     * @param p_ganadas the number of games won by the player
     * @param nombre the name of the player
     * @param posicion the current position of the player on the board
     * @param dinero the amount of money the player has
     * @param calles the list of properties owned by the player
     * @param encarcelado whether the player is imprisoned or not
     */
    public Jugador(int id, int p_ganadas, String nombre, int posicion, int dinero, ArrayList<Calle> calles, boolean encarcelado) {
        this.id = id;
        this.p_ganadas = p_ganadas;
        this.nombre = nombre;
        this.posicion = posicion;
        this.dinero = dinero;
        this.calles = calles;
        this.encarcelado = encarcelado;
    }

    /**
     * Returns the unique identifier of the player.
     * @return the id of the player
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the player.
     * @param id the id to set for the player
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the number of games won by the player.
     * @return the number of games won
     */
    public int getP_ganadas() {
        return p_ganadas;
    }

    /**
     * Sets the number of games won by the player.
     * @param p_ganadas the number of games won to set
     */
    public void setP_ganadas(int p_ganadas) {
        this.p_ganadas = p_ganadas;
    }

    /**
     * Returns the name of the player.
     * @return the name of the player
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the player.
     * @param nombre the name to set for the player
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Returns the current position of the player on the board.
     * @return the position of the player
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Sets the current position of the player on the board.
     * @param posicion the position to set for the player
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * Returns the amount of money the player has.
     * @return the money of the player
     */
    public int getDinero() {
        return dinero;
    }

    /**
     * Sets the amount of money the player has.
     * @param dinero the money to set for the player
     */
    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    /**
     * Returns the list of properties owned by the player.
     * @return the list of properties
     */
    public ArrayList<Calle> getCalles() {
        return calles;
    }

    /**
     * Sets the list of properties owned by the player.
     * @param calles the list of properties to set for the player
     */
    public void setCalles(ArrayList<Calle> calles) {
        this.calles = calles;
    }

    /**
     * Returns whether the player is imprisoned or not.
     * @return true if the player is imprisoned, false otherwise
     */
    public boolean isEncarcelado() {
        return encarcelado;
    }

    /**
     * Sets whether the player is imprisoned or not.
     * @param encarcelado true to mark the player as imprisoned, false otherwise
     */
    public void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }

    /**
     * Moves the player a certain number of spaces on the board.
     * If the player passes the last space (27), they receive 200€.
     * @param num the number of spaces to move
     */
    public void mover (int num){
        int casilla_fin = posicion + num;

        if (casilla_fin < 28){
            posicion = casilla_fin;
        } else {
            casilla_fin -= 28;
            posicion = casilla_fin;
            System.out.println("Has pasado por la casilla de salida: Recibes 200€");
            dinero += 200;
        }
    }

    /**
     * Returns a string representation of the Jugador object.
     * @return a string containing the player's id and name
     */
    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

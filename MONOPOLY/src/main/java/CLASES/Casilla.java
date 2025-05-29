package CLASES;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract class representing a square on the game board.
 * It implements the Efectos interface to apply effects on players.
 */
public abstract class Casilla implements Efectos{

    private int posicion;

    /**
     * Gets the position of the square.
     * @return the position of the square
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Sets the position of the square.
     * @param posicion the new position of the square
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * Constructor for the Casilla class.
     * @param posicion position of the square on the board
     */
    public Casilla(int posicion) {
        this.posicion = posicion;
    }

    /**
     * Shows the square's information in a formatted string.
     */
    public String mostrar(String colorAnsi) {
        String reset = "\u001B[0m";
        String contenido;


        if (this instanceof Calle){
            contenido = String.format("%02d %s", getPosicion(), ((Calle) this).getNombre());
        }else if (this instanceof Esquina){
            contenido = String.format("%02d %s", getPosicion(), ((Esquina) this).getTipo());
        }else {
            contenido = String.format("%02d %s", getPosicion(), "suerte");
        }

        // Cortamos a máximo 30 caracteres
        if (contenido.length() > 30) {
            contenido = contenido.substring(0, 30);
        } else {
            contenido = String.format("%-30s", contenido); // rellena con espacios
        }
        return colorAnsi + contenido + reset;
    }

    /**
     * Shows the players on the square in a formatted string.
     * @param jugadores list of players in the game
     * @return formatted string of players on the square
     */
    public String mostrarJugador (ArrayList<Jugador> jugadores) {
        String contenido = "";

        HashMap<Integer, String> fichaJugador = new HashMap<>();
        fichaJugador.put(0, "J1 ");
        fichaJugador.put(1, "J2 ");
        fichaJugador.put(2, "J3 ");
        fichaJugador.put(3, "J4 ");

        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getPosicion() == getPosicion()) {
                contenido += fichaJugador.get(i)+" ";
            }
        }
        // Cortamos a máximo 15 caracteres
        if (contenido.length() > 15) {
            contenido = contenido.substring(0, 15);

        } else {
            contenido = String.format("%-15s", contenido); // rellena con espacios
        }

        return contenido;
    }

    /**
     * shows the houses on the square in a formatted string.
     */
    public String mostrarCasas (){
        String contenido = "";
        if (this instanceof Normal) {
            int casas = ((Normal) this).getNumCasas();

            if (casas == 5){
                contenido = "$ "; // Emoji de hotel
            } else  {
                for (int i = 0; i < casas; i++) {
                    contenido += "■ "; // Emoji de casa
                }
            }

        } else {
            contenido = " ".repeat(15); // Espacio para otras casillas
        }

        contenido = String.format("%15s", contenido); // Rellena con espacios hasta 15 caracteres
        return contenido;
    }
}

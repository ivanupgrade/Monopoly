package CLASES;

/**
 * Abstract class representing a square on the game board.
 * It implements the Efectos interface to apply effects on players.
 */
public abstract class Casilla implements Efectos{

    /**
     * Position of the square on the board.
     */
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

}

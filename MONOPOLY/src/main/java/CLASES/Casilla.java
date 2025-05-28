package CLASES;

public abstract class Casilla implements Efectos{

    private int posicion;

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Casilla(int posicion) {
        this.posicion = posicion;
    }

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

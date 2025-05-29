package CLASES;

import java.util.ArrayList;
import java.util.HashMap;

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

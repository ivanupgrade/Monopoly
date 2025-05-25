package CLASES;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Partida {

    private int id;
    private Date fecha;
    private Baraja mazoRobo;
    private Baraja mazoDescarte;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Casilla> casillas;
    private int turno;

    public Partida(int id, Date fecha, Baraja mazoRobo, Baraja mazoDescarte, ArrayList<Jugador> jugadores, ArrayList<Casilla> casillas, int turno) {
        this.id = id;
        this.fecha = fecha;
        this.mazoRobo = mazoRobo;
        this.mazoDescarte = mazoDescarte;
        this.jugadores = jugadores;
        this.casillas = casillas;
        this.turno = turno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Baraja getMazoRobo() {
        return mazoRobo;
    }

    public void setMazoRobo(Baraja mazoRobo) {
        this.mazoRobo = mazoRobo;
    }

    public Baraja getMazoDescarte() {
        return mazoDescarte;
    }

    public void setMazoDescarte(Baraja mazoDescarte) {
        this.mazoDescarte = mazoDescarte;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public void setCasillas(ArrayList<Casilla> casillas) {
        this.casillas = casillas;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void dibujarTablero(){

    }

    public void cambiarTurno(){

    }

    public int tirarDado(){
        Random random = new Random();
        int dado = random.nextInt(1,7);
        return dado;
    }


}

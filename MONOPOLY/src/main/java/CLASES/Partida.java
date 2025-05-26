package CLASES;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Partida {

    private int id;
    private Date fecha;
    private Baraja mazoRobo;
    private Baraja mazoDescarte;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Casilla> casillas;
    private int turno;

    public Partida() {

    }

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

    public void setFecha(java.sql.Date fecha) {
        this.fecha = this.fecha;
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
        turno++;
        turno = Math.floorMod(turno, jugadores.size());

    }
    public void turnos() {
        Scanner sc = new Scanner(System.in);
        Jugador jugador = jugadores.get(turno);

        System.out.printf("Es el turno de %s id=%d", jugador.getNombre(), jugador.getId());



        String respuesta = "";
        String nombre_calle;


        while (!respuesta.equalsIgnoreCase("l")) {
            System.out.println("Pulsa 'l' para lanzar los dados!!!");
            respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("l")) {

                int dado = tirarDado();
                System.out.println("Has sacado un "+dado);

                if (jugador.isEncarcelado()) {
                    if (dado == 6) {
                        System.out.println("Felicidades, has salido de la carcel");
                        jugador.setEncarcelado(false);

                    }else {
                        System.out.println("Sigues siendo preso, mas suerte la proxima vez (Sacar 6 para salir)");
                        cambiarTurno();
                        return;
                    }

                }
                jugador.mover(dado);
                Casilla casilla_jugador = casillas.get(jugador.getPosicion());


                if (casilla_jugador instanceof Calle) {
                    nombre_calle = ((Calle) casilla_jugador).getNombre();
                    System.out.println("Has caido en la calle " + nombre_calle);
                    ((Calle) casilla_jugador).aplicarEfecto(jugador, this);

                }else if (casilla_jugador instanceof Suerte) {
                    System.out.println("Has caido en una casilla de suerte");
                    ((Suerte) casilla_jugador).aplicarEfecto(jugador,this);

                } else{
                    String tipo_casilla = ((Esquina) casilla_jugador).getTipo();
                    System.out.println("Has caido en la calle " + tipo_casilla);
                    ((Esquina) casilla_jugador).aplicarEfecto(jugador,this);

                }
                dibujarTablero();




            } else {
                System.out.println("comando incorrecto");
            }
        }

        System.out.printf("¿QUE DESEAS REALIZAR? %n 1.Continuar %n 2.Realizar un intercambio");
        int respuesta2 = sc.nextInt();

        if (respuesta2 == 1) {
            cambiarTurno();
        } else if (respuesta2 == 2) {
            System.out.println("Indique la id del jugador con el que desea tradear");
            for (int i = 0; i < jugadores.size(); i++) {
                System.out.println(i+"."+jugadores.get(i).getNombre());
            }
            int id = sc.nextInt();

            System.out.println("indica que quieres ofrecer(1=dinero, 2=calle)");
            int respuesta3= sc.nextInt();

            if (respuesta3==1){
                System.out.println("Indica cuanto dinero quieres ofrecer");
                int dinero_ofrecido = sc.nextInt();

            } else if (respuesta3==2) {
                System.out.println("Indica la id de la propiedad a ofrecer");
                for (int i = 0; i < jugador.getCalles().size(); i++) {
                    System.out.println(i+"."+jugador.getCalles());
                }
                
            }else {
                System.out.println("opcion incorrecta");
            }

        }
    }




    public void intercambiar (Jugador jugador, int dinero, Calle calle){

    }
    public void intercambiar (Jugador jugador, Calle calle, int dinero){

    }
    public void intercambiar (Jugador jugador, Calle calle1, Calle calle2){

    }

    public int tirarDado(){
        Random random = new Random();
        int dado = random.nextInt(1,7);
        return dado;
    }


    public int getId() {
        return id;
    }
}

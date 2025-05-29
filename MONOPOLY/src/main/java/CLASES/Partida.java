package CLASES;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Partida class represents a game of Monopoly.
 * It contains information about the game state, including players, decks, and the game board.
 * It also provides methods for managing turns, drawing the board, and checking for winners.
 */
public class Partida {

    private int id;
    private Date fecha;
    private Baraja mazoRobo;
    private Baraja mazoDescarte;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Casilla> casillas;
    private int turno;

    /**
     * Default constructor initializes the partida with empty values.
     */
    public Partida() {

    }

    /**
     * Constructor to initialize a Partida with specific values.
     *
     * @param id          the unique identifier for the partida
     * @param fecha       the date of the partida
     * @param mazoRobo    the deck of cards for drawing
     * @param mazoDescarte the deck of discarded cards
     * @param jugadores   the list of players in the partida
     * @param casillas    the list of board squares in the partida
     * @param turno       the current turn number
     */
    public Partida(int id, Date fecha, Baraja mazoRobo, Baraja mazoDescarte, ArrayList<Jugador> jugadores, ArrayList<Casilla> casillas, int turno) {
        this.id = id;
        this.fecha = fecha;
        this.mazoRobo = mazoRobo;
        this.mazoDescarte = mazoDescarte;
        this.jugadores = jugadores;
        this.casillas = casillas;
        this.turno = turno;
    }


    /**
     * Method to get the unique identifier of the partida.
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set the unique identifier of the partida.
     *
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to get the date of the partida.
     *
     * @return the date of the partida
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Method to set the date of the partida.
     *
     * @param fecha the date to set
     */
    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Method to get the deck of cards for drawing.
     *
     * @return the deck of cards for drawing
     */
    public Baraja getMazoRobo() {
        return mazoRobo;
    }

    /**
     * Method to set the deck of cards for drawing.
     *
     * @param mazoRobo the deck of cards to set
     */
    public void setMazoRobo(Baraja mazoRobo) {
        this.mazoRobo = mazoRobo;
    }

    /**
     * Method to get the deck of discarded cards.
     *
     * @return the deck of discarded cards
     */
    public Baraja getMazoDescarte() {
        return mazoDescarte;
    }

    /**
     * Method to set the deck of discarded cards.
     *
     * @param mazoDescarte the deck of discarded cards to set
     */
    public void setMazoDescarte(Baraja mazoDescarte) {
        this.mazoDescarte = mazoDescarte;
    }

    /**
     * Method to get the list of players in the partida.
     *
     * @return the list of players
     */
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Method to set the list of players in the partida.
     *
     * @param jugadores the list of players to set
     */
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Method to get the list of board squares in the partida.
     *
     * @return the list of board squares
     */
    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    /**
     * Method to set the list of board squares in the partida.
     *
     * @param casillas the list of board squares to set
     */
    public void setCasillas(ArrayList<Casilla> casillas) {
        this.casillas = casillas;
    }

    /**
     * Method to get the current turn number.
     *
     * @return the current turn number
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Method to set the current turn number.
     *
     * @param turno the turn number to set
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    /**
     * Method to draw the game board with all the squares and players.
     */
    public void dibujarTablero(){

        casillas.sort(Comparator.comparingInt(Casilla::getPosicion));

        HashMap<String, String> colores = new HashMap<>();

        // Colores ANSI
        colores.put("blanco","\u001B[30m"+"\u001B[47m");
        colores.put("marron","\u001B[30m"+"\u001B[43m");
        colores.put("celeste","\u001B[30m"+"\u001B[106m");
        colores.put("rosa","\u001B[30m"+"\u001B[105m");
        colores.put("rojo","\u001B[30m"+"\u001B[41m");
        colores.put("azul","\u001B[30m"+"\u001B[44m");
        colores.put("verde","\u001B[30m"+"\u001B[42m");
        colores.put("amarillo","\u001B[30m"+"\u001B[103m");
        colores.put("morado","\u001B[30m"+"\u001B[45m");
        String reset = "\u001B[0m";



        // Parte superior (0–7). Colores y datos
        System.out.println("╔"+"═".repeat(30)+("╦"+"═".repeat(30)).repeat(7)+"╗");
        for (int i = 0; i <= 7; i++) {
            if (casillas.get(i) instanceof Normal){
                System.out.print("║"+casillas.get(i).mostrar(colores.get(((Normal)casillas.get(i)).getColor())));
            }else {
                System.out.print("║"+casillas.get(i).mostrar(colores.get("blanco")));
            }
        }
        System.out.println("║");


        // Separacion parte abajo de las casillas (0–7).
        for (int i = 0; i <= 7; i++) {
            if (casillas.get(i) instanceof Normal){
                System.out.print("║"+colores.get(((Normal)casillas.get(i)).getColor())+"     -   " +
                        "     -        -      "+reset);
            }else {
                System.out.print("║"+colores.get("blanco")+"     -   " +
                        "     -        -      "+reset);
            }
        }
        System.out.println("║");

        //Apartado para casas. Parte arriba
        for (int i = 0; i <= 7; i++) {
            if (casillas.get(i) instanceof Normal){
                System.out.print("║"+colores.get(((Normal)casillas.get(i)).getColor())+casillas.get(i).mostrarJugador(jugadores)+casillas.get(i).mostrarCasas()+reset);
            }else {
                System.out.print("║"+colores.get("blanco")+casillas.get(i).mostrarJugador(jugadores)+casillas.get(i).mostrarCasas()+reset);
            }
        }
        System.out.println("║");
        System.out.println("╠"+"═".repeat(30)+"╬"+("═".repeat(30)+"╩").repeat(5)+"═".repeat(30)+"╬"+"═".repeat(30)+"╣");

        // Lados (27–21 y 8–13)
        for (int i = 27, j = 8; i > 21; i--, j++) {

            //Datos casillas y colores
            if (casillas.get(i) instanceof Normal){
                System.out.print("║"+casillas.get(i).mostrar(colores.get(((Normal)casillas.get(i)).getColor()))+"║");
                System.out.print(" ".repeat(185)+"║");
                System.out.println(casillas.get(j).mostrar(colores.get(((Normal)casillas.get(j)).getColor()))+"║");
            }else {
                System.out.print("║"+casillas.get(i).mostrar(colores.get("blanco"))+"║");
                System.out.print(" ".repeat(185)+"║");
                System.out.println(casillas.get(j).mostrar(colores.get("blanco"))+"║");
            }

            //Separacion (27–21 y 8–13)
            if (casillas.get(i) instanceof Normal){
                System.out.print("║"+colores.get(((Normal)casillas.get(i)).getColor())+"     -   " +
                        "     -        -      "+reset+"║");
                System.out.print(" ".repeat(185)+"║");
                System.out.println(colores.get(((Normal)casillas.get(j)).getColor())+"     -   " +
                        "     -        -      "+reset+"║");
            }else {
                System.out.print("║"+colores.get("blanco")+"     -   " +
                        "     -        -      "+reset+"║");
                System.out.print(" ".repeat(185)+"║");
                System.out.println(colores.get("blanco")+"     -   " +
                        "     -        -      "+reset+"║");
            }

            //Zona casas (27–21 y 8–13)
            if (casillas.get(i) instanceof Normal){
                System.out.print("║"+colores.get(((Normal)casillas.get(i)).getColor())+casillas.get(i).mostrarJugador(jugadores)+casillas.get(i).mostrarCasas()+reset+"║");
                System.out.print(" ".repeat(185)+"║");
                System.out.println(colores.get(((Normal)casillas.get(j)).getColor())+casillas.get(j).mostrarJugador(jugadores)+casillas.get(j).mostrarCasas()+reset+"║");
            }else {
                System.out.print("║"+colores.get("blanco")+casillas.get(i).mostrarJugador(jugadores)+casillas.get(i).mostrarCasas()+reset+"║");
                System.out.print(" ".repeat(185)+"║");
                System.out.println(colores.get("blanco")+casillas.get(j).mostrarJugador(jugadores)+casillas.get(j).mostrarCasas()+reset+"║");
            }

            if (i!=21 && j!=13){
                System.out.println("╠"+"═".repeat(30)+"╣"+" ".repeat(185)+"╠"+"═".repeat(30)+"╣");
            }else {
                System.out.println("╠"+"═".repeat(30)+"╬"+("═".repeat(30)+"╦").repeat(5)+"═".repeat(30)+"╬"+"═".repeat(30)+"╣");
            }

        }

        // Datos y colores (21–14)
        // Parte inferior (21–14)
        for (int i = 21; i >= 14; i--) {
            if (casillas.get(i) instanceof Normal){
                System.out.print("║"+casillas.get(i).mostrar(colores.get(((Normal)casillas.get(i)).getColor())));
            }else {
                System.out.print("║"+casillas.get(i).mostrar(colores.get("blanco")));
            }
        }

        //Separacion (21-14)
        System.out.println("║");
        for (int i = 21; i >= 14; i--) {
            if (casillas.get(i) instanceof Normal){
                System.out.print("║"+colores.get(((Normal)casillas.get(i)).getColor())+"     -   " +
                        "     -        -      "+reset);
            }else {
                System.out.print("║"+colores.get("blanco")+"     -   " +
                        "     -        -      "+reset);
            }
        }
        System.out.println("║");


        //Datos Casas(21-14)
        for (int i = 21; i >= 14; i--) {
            if (casillas.get(i) instanceof Normal){
                System.out.print("║"+colores.get(((Normal)casillas.get(i)).getColor())+casillas.get(i).mostrarJugador(jugadores)+casillas.get(i).mostrarCasas()+reset);
            }else {
                System.out.print("║"+colores.get("blanco")+casillas.get(i).mostrarJugador(jugadores)+casillas.get(i).mostrarCasas()+reset);
            }
        }
        System.out.println("║");

        System.out.println("╚"+"═".repeat(30)+("╩"+"═".repeat(30)).repeat(7)+"╝");

    }


    /**
     * Method to check if there is a winner in the game.
     * If only one player remains, they are declared the winner.
     *
     * @return true if there is still a winner, false otherwise
     */
    public boolean comprobarGanador() {
        if (jugadores.size()==1) {
            System.out.println("Felicidades "+jugadores.getFirst()+" has ganado");
            jugadores.getFirst().setP_ganadas(jugadores.getFirst().getP_ganadas()+1);
            return false;
        }
        return true;
    }

    /**
     * Method to change the turn to the next player.
     * It increments the turno variable to switch to the next player in the list.
     */
    public void cambiarTurno(){
        turno++;
    }

    /**
     * Method to handle the player's turn.
     * It prompts the player to roll the dice, moves them, and applies effects based on the square they land on.
     * It also allows players to perform actions like trading or building houses.
     */
    public void turnos() {
        Scanner sc = new Scanner(System.in);
        Jugador jugador = jugadores.get(Math.floorMod(turno, jugadores.size()));

        System.out.printf("Es el turno de %s: tienes %d%n", jugador.getNombre(), jugador.getDinero());
        
        String respuesta = "";
        String nombre_calle;

        while (!respuesta.equalsIgnoreCase("l")) {
            System.out.println("Pulsa 'l' para lanzar los dados!!!");
            respuesta = sc.nextLine();
            System.out.println();

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

                dibujarTablero();

                for (Casilla casilla : casillas) {
                    if (casilla.getPosicion() == jugador.getPosicion()) {
                        if (casilla instanceof Calle) {
                            nombre_calle = ((Calle) casilla).getNombre();
                            System.out.println("Has caido en " + nombre_calle);
                            ((Calle) casilla).aplicarEfecto(jugador, this);

                        }else if (casilla instanceof Suerte) {
                            System.out.println("Has caido en una casilla de suerte");
                            ((Suerte) casilla).aplicarEfecto(jugador,this);

                        } else{
                            String tipo_casilla = ((Esquina) casilla).getTipo();
                            System.out.println("Has caido en " + tipo_casilla);
                            ((Esquina) casilla).aplicarEfecto(jugador,this);

                        }
                        break;
                    }
                }

                if (jugador.getDinero()<=0) {
                    System.out.println(jugador.getNombre()+" te has quedado sin dinero, quedas eliminado de la partida");
                    jugadores.remove(jugador);
                    return;
                }


            } else {
                System.out.println("comando incorrecto");
            }
        }

        int respuesta2=0;
        while (respuesta2<=0 || respuesta2>3){
            System.out.printf("¿QUE DESEAS REALIZAR? %n 1.Continuar %n 2.Realizar un intercambio %n 3.Edificar%n");
            respuesta2 = sc.nextInt();
            sc.nextLine();
            System.out.println();

            if (respuesta2 == 1) {
                cambiarTurno();

            } else if (respuesta2 == 2) {
                System.out.println("Indique la id del jugador con el que desea tradear");

                for (int i = 0; i < jugadores.size(); i++) {
                    System.out.println(i+"."+jugadores.get(i).getNombre());
                }
                int id = sc.nextInt();
                sc.nextLine();
                System.out.println();

                int respuesta3 = 0;

                while (true){
                    System.out.printf("indica el metodo de intercambio %n 1.Comprar propiedad %n 2.Vender propiedad %n 3.Intercambiar propiedad %n");
                    respuesta3= sc.nextInt();
                    sc.nextLine();
                    System.out.println();

                    if (respuesta3==1){
                        intercambiar1(jugador,jugadores.get(id));
                        break;

                    }else if (respuesta3==2) {
                        intercambiar2(jugador,jugadores.get(id));
                        break;

                    }else if(respuesta3==3){
                        intercambiar3(jugador,jugadores.get(id));
                        break;

                    }else {
                        System.out.println("opcion incorrecta");
                    }
                }

                cambiarTurno();

            }else if (respuesta2==3){
                while (true){
                    for (int i = 0; i < jugador.getCalles().size(); i++) {
                        if (jugador.getCalles().get(i) instanceof Normal){
                            System.out.printf("%d. %s: precio edificio: %d, numero de casas: %d%n", i, jugador.getCalles().get(i), ((Normal) jugador.getCalles().get(i)).getPrecioEdificio(), ((Normal) jugador.getCalles().get(i)).getNumCasas());
                        }
                    }

                    System.out.println("¿Que casilla quieres modificar?");
                    int id_casilla= sc.nextInt();
                    sc.nextLine();
                    System.out.println();

                    if (id_casilla<0 || id_casilla > jugador.getCalles().size()) {
                        System.out.println("Selecciona una casilla existente");
                    }else {
                        ((Normal) jugador.getCalles().get(id_casilla)).construirCasa(jugador);
                        break;
                    }
                    
                }

                cambiarTurno();

            }else {
                System.out.println("opcion incorrecta");
            }
        }
    }


    /**
     * Method to handle the trading of properties between players.
     * It allows players to trade money for a street.
     *
     * @param jugador1 the player initiating the trade
     * @param jugador2 the player receiving the trade offer
     */
    public void intercambiar1 (Jugador jugador1, Jugador jugador2){
        Scanner sc = new Scanner(System.in);

        int dinero_ofrecido;
        while (true){
            System.out.println("Indica cuanto dinero quieres ofrecer");
            dinero_ofrecido = sc.nextInt();
            sc.nextLine();
            System.out.println();

            if (dinero_ofrecido > jugador1.getDinero()) {
                System.out.println("insuficiente dinero, solo dispones de "+jugador1.getDinero()+" dólares)");
            }else {
                break;
            }
        }

        int id_propiedad_recibida;
        while (true){
            if (jugador2.getCalles().isEmpty()) {
                System.out.println("El jugador no dispone de ninguna calle");
                return;
            }
            System.out.println("Indica el id de la propiedad a recibir");
            for (int i = 0; i < jugador2.getCalles().size(); i++) {
                System.out.println(i+"."+jugador2.getCalles().get(i));
            }
            id_propiedad_recibida= sc.nextInt();
            sc.nextLine();
            System.out.println();

            if (id_propiedad_recibida > jugador2.getCalles().size()-1  ||  id_propiedad_recibida < 0) {
                System.out.println("id de propiedad incorrecto, inténtelo de nuevo");
            }else {
                break;
            }
        }

        while (true){
            System.out.println(jugador2.getNombre()+" deseas aceptar el intercambio de tu calle "+jugador2.getCalles().get(id_propiedad_recibida).getNombre()+" por "+dinero_ofrecido+" dólares? (s/n)");
            String respuesta= sc.nextLine();
            System.out.println();

            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("Intercambio aceptado!!");

                jugador1.setDinero(jugador1.getDinero()-dinero_ofrecido);
                jugador1.getCalles().add(jugador2.getCalles().get(id_propiedad_recibida));

                jugador2.setDinero(jugador2.getDinero()+dinero_ofrecido);
                jugador2.getCalles().remove(id_propiedad_recibida);
                break;

            } else if (respuesta.equalsIgnoreCase("n")) {
                System.out.println("Intercambio cancelado");
                break;
            }else {
                System.out.println("Opción incorrecta");
            }
        }
    }


    /**
     * Method to handle the trading of properties between players.
     * It allows players to trade a street for money.
     *
     * @param jugador1 the player initiating the trade
     * @param jugador2 the player receiving the trade offer
     */
    public void intercambiar2 (Jugador jugador1, Jugador jugador2){
        Scanner sc =new Scanner(System.in);
        int id_propiedad_ofrecida;
        while (true){
            if (jugador1.getCalles().isEmpty()) {
                System.out.println("No dispones de ninguna calle");
                return;
            }
            System.out.println("Indica el id de la propiedad a ofrecer");
            for (int i = 0; i < jugador1.getCalles().size(); i++) {
                System.out.println(i+"."+jugador1.getCalles().get(i));
            }
            id_propiedad_ofrecida= sc.nextInt();
            sc.nextLine();
            System.out.println();

            if (id_propiedad_ofrecida > jugador1.getCalles().size()-1  ||  id_propiedad_ofrecida < 0) {
                System.out.println("id de propiedad incorrecto, inténtelo de nuevo");
            }else {
                break;
            }
        }
        int dinero_recibido;
        while (true){
            System.out.println("Indica cuanto dinero quieres recibir");
            dinero_recibido = sc.nextInt();
            sc.nextLine();
            System.out.println();

            if (dinero_recibido > jugador2.getDinero()) {
                System.out.println("El jugador no dispone de ese dinero (tiene "+jugador2.getDinero()+" dólares)");
            }else {
                break;
            }
        }
        while (true){
            System.out.println(jugador2.getNombre()+" deseas pagar "+dinero_recibido+" dólares por la calle "+jugador2.getCalles().get(id_propiedad_ofrecida).getNombre()+"? (s/n)");
            String respuesta= sc.nextLine();
            System.out.println();

            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("Intercambio aceptado!!");

                jugador2.setDinero(jugador2.getDinero()-dinero_recibido);
                jugador2.getCalles().add(jugador1.getCalles().get(id_propiedad_ofrecida));

                jugador1.setDinero(jugador1.getDinero()+dinero_recibido);
                jugador1.getCalles().remove(id_propiedad_ofrecida);
                break;

            } else if (respuesta.equalsIgnoreCase("n")) {
                System.out.println("Intercambio cancelado");
                break;
            }else {
                System.out.println("Opción incorrecta");
            }
        }
    }


    /**
     * Method to handle the trading of properties between players.
     * It allows players to trade a street for another street.
     *
     * @param jugador1 the player initiating the trade
     * @param jugador2 the player receiving the trade offer
     */
    public void intercambiar3 (Jugador jugador1, Jugador jugador2){
        Scanner sc =new Scanner(System.in);
        int id_propiedad_ofrecida;
        while (true){
            if (jugador1.getCalles().isEmpty()) {
                System.out.println("No dispones de ninguna calle");
                return;
            }
            System.out.println("Indica el id de la propiedad a ofrecer");
            for (int i = 0; i < jugador1.getCalles().size(); i++) {
                System.out.println(i+"."+jugador1.getCalles().get(i));
            }
            id_propiedad_ofrecida= sc.nextInt();
            sc.nextLine();
            System.out.println();

            if (id_propiedad_ofrecida > jugador1.getCalles().size()-1  ||  id_propiedad_ofrecida < 0) {
                System.out.println("id de propiedad incorrecto, inténtelo de nuevo");
            }else {
                break;
            }
        }
        int id_propiedad_recibida;
        while (true){
            if (jugador2.getCalles().isEmpty()) {
                System.out.println("El jugador seleccionado no dispone de ninguna calle");
                return;
            }else {
                System.out.println("Indica el id de la propiedad a recibir");
                for (int i = 0; i < jugador2.getCalles().size(); i++) {
                    System.out.println(i+"."+jugador2.getCalles().get(i));
                }
                id_propiedad_recibida= sc.nextInt();
                sc.nextLine();
                System.out.println();

                if (id_propiedad_recibida > jugador2.getCalles().size()-1  ||  id_propiedad_recibida < 0) {
                    System.out.println("id de propiedad incorrecto, inténtelo de nuevo");
                }else {
                    break;
                }
            }
        }

        while (true){
            System.out.println(jugador2.getNombre()+" deseas aceptar el intercambio de la calle "+jugador1.getCalles().get(id_propiedad_ofrecida).getNombre()+" por tu calle "+jugador2.getCalles().get(id_propiedad_recibida).getNombre()+"? (s/n)");
            String respuesta= sc.nextLine();
            System.out.println();

            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("Intercambio aceptado!!");
                Calle calle_jugador1=jugador1.getCalles().get(id_propiedad_ofrecida);
                Calle calle_jugador2=jugador2.getCalles().get(id_propiedad_ofrecida);

                jugador1.getCalles().remove(id_propiedad_ofrecida);
                jugador1.getCalles().add(calle_jugador2);

                jugador2.getCalles().remove(id_propiedad_recibida);
                jugador2.getCalles().add(calle_jugador1);
                break;

            } else if (respuesta.equalsIgnoreCase("n")) {
                System.out.println("Intercambio cancelado");
                break;
            }else {
                System.out.println("Opción incorrecta");
            }
        }

    }

    /**
     * Method to simulate rolling a dice.
     * It generates a random number between 1 and 6 (inclusive).
     *
     * @return the result of the dice roll
     */
    public int tirarDado(){
        Random random = new Random();
        int dado = random.nextInt(1,7);
        return dado;
    }

    /**
     * Returns a string representation of the Partida object.
     */
    @Override
    public String toString() {
        return "Partida{" +
                "id=" + id +
                ", fecha=" + fecha +
                '}';
    }
}

import CLASES.Jugador;
import CLASES.Partida;
import DAO.Conexion;
import DAO.JugadorDAOImpl;
import DAO.PartidaDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JugadorDAOImpl jugadorDAO = new JugadorDAOImpl();
        PartidaDAOImpl partidaDAO = new PartidaDAOImpl();
        Partida partida;

        String respuesta;

        while (true){
            System.out.println("¿Quieres empezar una partida nueva o continuar una existente? (1/2)");
            respuesta = scanner.nextLine();
            System.out.println();

            if (respuesta.equals("1")){
                while (true){
                    System.out.println("Quieres crear nuevos jugadores? (s/n)");
                    String respuesta2 = scanner.nextLine().toLowerCase();
                    if (respuesta2.equals("s")) {

                        System.out.println("¿Cuantos jugadores quieres crear?");
                        int numJugadores = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();

                        for (int i = 0; i < numJugadores; i++) {
                            System.out.println("Introduce el nombre del jugador: ");
                            String nombreJugador = scanner.nextLine();
                            System.out.println();

                            jugadorDAO.insertar(new Jugador(nombreJugador));
                        }

                    } else if (respuesta2.equals("n")) {

                    } else {
                        System.out.println("Respuesta no valida");
                        continue;
                    }


                    while (true){
                        System.out.println("¿Cuantos jugadores van a jugar? (2-4)");
                        int numJugadores = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println();

                        if (numJugadores < 2 || numJugadores > 4) {
                            System.out.println("Numero de jugadores no valido. Debe ser entre 2 y 4.");
                            continue;
                        }

                        ArrayList<Jugador> jugadores = new ArrayList<>();

                        System.out.println("¿Que jugadores van a jugar? (Introduce los IDs de los jugadores)");
                        System.out.println(jugadorDAO.obtenerTodos());

                        for (int i = 0; i < numJugadores; i++) {
                            int idJugador = scanner.nextInt();
                            scanner.nextLine();

                            jugadores.add(jugadorDAO.obtener(idJugador));

                            if (jugadores.get(i) == null) {
                                System.out.println("Jugador no encontrado. Por favor, introduce un ID valido.");
                                i--;
                            }
                        }

                        partidaDAO.insertar(jugadores);

                        break;
                    }

                    partida = partidaDAO.obtener(partidaDAO.obtenerTodos().getLast().getId());

                    for (Jugador jugador : partida.getJugadores()) {
                        jugador.setDinero(1500);
                    }

                    break;
                }

            } else if (respuesta.equals("2")) {

                while (true) {
                    System.out.println("¿Que partida quieres continuar? (Introduce el ID de la partida)");
                    System.out.println(partidaDAO.obtenerTodos());

                    int idPartida = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println();

                    if (partidaDAO.obtener(idPartida) == null) {
                        System.out.println("Partida no encontrada. Por favor, introduce un ID valido.");
                        continue;
                    }

                    partida = partidaDAO.obtener(idPartida);
                    break;
                }

            } else {
                System.out.println("Respuesta no valida");
                continue;
            }

            break;
        }

        System.out.println("Iniciando partida...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (partida.comprobarGanador()){
            partida.turnos();
            partidaDAO.actualizar(partida);
        }

        partidaDAO.actualizar(partida);
        partidaDAO.eliminar(partida.getId());

        try {
            Conexion.getConexion().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

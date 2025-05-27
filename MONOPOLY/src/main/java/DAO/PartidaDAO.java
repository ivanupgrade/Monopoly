package DAO;

import CLASES.Jugador;
import CLASES.Partida;

import java.util.ArrayList;

public interface PartidaDAO {
    void insertar(ArrayList<Jugador> jugadores);
    Partida obtener(int id);
    ArrayList<Partida> obtenerTodos();
    void actualizar(Partida partida);
    void eliminar(int id);
}
package DAO;

import CLASES.Partida;

import java.util.ArrayList;

public interface PartidaDAO {
    void insertar(Partida partida);
    Partida obtener(int id);
    ArrayList<Partida> obtenerTodos();
    void actualizar(Partida partida);
    void eliminar(int id);
}

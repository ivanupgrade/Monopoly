package DAO;

import CLASES.Jugador;
import CLASES.Partida;

import java.util.ArrayList;

/**
 * PartidaDAO interface for managing Partida (game) objects in a data access layer.
 * It provides methods to insert, retrieve, update, and delete Partida records.
 */
public interface PartidaDAO {

    /**
     * Inserts a new Partida object into the data source with a list of Jugador objects.
     * @param jugadores the list of Jugador objects to be associated with the Partida
     */
    void insertar(ArrayList<Jugador> jugadores);

    /**
     * Retrieves a Partida object by its unique identifier.
     * @param id the unique identifier of the Partida
     * @return the Partida object if found, otherwise null
     */
    Partida obtener(int id);

    /**
     * Retrieves all Partida objects from the data source.
     * @return a list of all Partida objects
     */
    ArrayList<Partida> obtenerTodos();

    /**
     * Updates an existing Partida record in the data source.
     * @param partida the Partida object with updated values
     */
    void actualizar(Partida partida);

    /**
     * Deletes a Partida record by its unique identifier.
     * @param id the unique identifier of the Partida to be deleted
     */
    void eliminar(int id);
}
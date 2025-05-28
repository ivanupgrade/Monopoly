package DAO;

import CLASES.Jugador;

import java.util.ArrayList;

/**
 * JugadorDAO interface for managing Jugador objects in a data access layer.
 * It provides methods to insert, retrieve, update, and delete Jugador records.
 */
public interface JugadorDAO {

    /**
     * Inserts a new Jugador object into the data source.
     * @param jugador the Jugador object to be inserted
     */
    void insertar(Jugador jugador);

    /**
     * Retrieves a Jugador object by its unique identifier.
     * @param id the unique identifier of the Jugador
     * @return the Jugador object if found, otherwise null
     */
    Jugador obtener(int id);

    /**
     * Retrieves all Jugador objects from the data source.
     * @return a list of all Jugador objects
     */
    ArrayList<Jugador> obtenerTodos();

    /**
     * Updates an existing Jugador record in the data source.
     * @param jugador the Jugador object with updated values
     */
    void actualizar(Jugador jugador);

    /**
     * Deletes a Jugador record by its unique identifier.
     * @param id the unique identifier of the Jugador to be deleted
     */
    void eliminar(int id);
}

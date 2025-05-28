package DAO;

import CLASES.Estacion;

import java.util.ArrayList;

/**
 * EstacionDAO interface for managing Estacion objects in a data access layer.
 * It provides methods to retrieve, update, and delete Estacion records.
 */
public interface EstacionDAO {

    /**
     * Retrieves an Estacion object by its position.
     * @param posicion the position of the Estacion
     */
    Estacion obtener(int posicion);

    /**
     * Retrieves all Estacion objects from the data source.
     * @return a list of all Estacion objects
     */
    ArrayList<Estacion> obtenerTodos();

    /**
     * Updates an existing Estacion record in the data source.
     * @param estacion the Estacion object with updated values
     */
    void actualizar(Estacion estacion);

    /**
     * Deletes an Estacion record by its position.
     * @param posicion the position of the Estacion to be deleted
     */
    void eliminar(int posicion);
}

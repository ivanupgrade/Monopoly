package DAO;

import CLASES.Normal;

import java.util.ArrayList;

/**
 * NormalDAO interface for managing Normal(streets) objects in a data access layer.
 * It provides methods to retrieve, update, and delete Normal records.
 */
public interface NormalDAO {

    /**
     * Retrieves a Normal object by its position.
     * @param posicion the position of the Normal streets
     */
    Normal obtener (int posicion);

    /**
     * Retrieves all Normal objects from the data source.
     * @return a list of all Normal objects
     */
    ArrayList<Normal> obtenerTodos ();

    /**
     * Updates an existing Normal record in the data source.
     * @param normal the Normal object with updated values
     */
    void actualizar (Normal normal);

    /**
     * Deletes a Normal record by its position.
     * @param posicion the position of the Normal to be deleted
     */
    void eliminar (int posicion);

}

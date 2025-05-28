package DAO;

import CLASES.CartaSuerte;

import java.util.ArrayList;

/**
 * CartaSuerteDAO interface for managing CartaSuerte objects in a data access layer.
 * It provides methods to insert, retrieve, update, and delete CartaSuerte records.
 */
public interface CartaSuerteDAO {

    /**
     * inserts a new CartaSuerte record into the data source.
     * @param carta the CartaSuerte object to be inserted
     */
    void insertar(CartaSuerte carta);

    /**
     * Retrieves a CartaSuerte object by its unique identifier.
     * @param id the unique identifier of the CartaSuerte
     * @return the CartaSuerte object if found, otherwise null
     */
    CartaSuerte obtener(int id);

    /**
     * Retrieves all CartaSuerte objects from the data source.
     * @return a list of all CartaSuerte objects
     */
    ArrayList<CartaSuerte> obtenerTodos();

    /**
     * Updates an existing CartaSuerte record in the data source.
     * @param carta the CartaSuerte object with updated values
     */
    void actualizar(CartaSuerte carta);

    /**
     * Deletes a CartaSuerte record by its unique identifier.
     * @param id the unique identifier of the CartaSuerte to be deleted
     */
    void eliminar(int id);


}

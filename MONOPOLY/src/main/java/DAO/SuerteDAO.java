package DAO;
import CLASES.Suerte;

import java.util.ArrayList;

/**
 * SuerteDAO interface for managing Suerte objects in a data access layer.
 * It provides methods to retrieve and delete Suerte records.
 */
public interface SuerteDAO {



    /**
     * Retrieves a Suerte object by its position.
     * @param posicion the position of the Suerte
     */
    Suerte obtener(int posicion);

    /**
     * Retrieves all Suerte objects from the data source.
     * @return a list of all Suerte objects
     */
    ArrayList<Suerte> obtenerTodos();

    /**
     * Deletes a Suerte record by its position.
     * @param posicion the position of the Suerte to be deleted
     */
    void eliminar(int posicion);
}

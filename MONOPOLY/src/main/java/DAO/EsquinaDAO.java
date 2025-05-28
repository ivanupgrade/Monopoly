package DAO;

import CLASES.Esquina;

import java.util.ArrayList;

/**
 * EsquinaDAO interface for managing Esquina objects in a data access layer.
 * It provides methods to retrieve, update, and delete Esquina records.
 */
public interface EsquinaDAO {

    /**
     * Retrieves an Esquina object by its position.
     * @param posicion the position of the Esquina
     */
    Esquina obtener(int posicion);

    /**
     * Retrieves all Esquina objects from the data source.
     * @return a list of all Esquina objects
     */
    ArrayList<Esquina> obtenerTodos();

    /**
     * Updates an existing Esquina record in the data source.
     * @param esquina the Esquina object with updated values
     */
    void actualizar(Esquina esquina);

    /**
     * Deletes an Esquina record by its position.
     * @param posicion the position of the Esquina to be deleted
     */
    void eliminar(int posicion);
}

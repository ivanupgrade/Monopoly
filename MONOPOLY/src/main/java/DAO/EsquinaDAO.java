package DAO;

import CLASES.Esquina;

import java.util.ArrayList;

public interface EsquinaDAO {

    Esquina obtener(int posicion);
    ArrayList<Esquina> obtenerTodos();
    void actualizar(Esquina esquina);
    void eliminar(int posicion);
}

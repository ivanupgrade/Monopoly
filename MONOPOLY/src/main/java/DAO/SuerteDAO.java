package DAO;
import CLASES.Suerte;

import java.util.ArrayList;

public interface SuerteDAO {

    Suerte obtener(int posicion);
    ArrayList<Suerte> obtenerTodos();
    void eliminar(int posicion);
}

package DAO;

import CLASES.Normal;

import java.util.ArrayList;

public interface NormalDAO {
    Normal obtener (int posicion);
    ArrayList<Normal> obtenerTodos ();
    void actualizar (Normal normal);
    void eliminar (int posicion);

}

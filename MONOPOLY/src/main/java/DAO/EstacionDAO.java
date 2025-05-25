package DAO;

import CLASES.Estacion;

import java.util.ArrayList;

public interface EstacionDAO {
    Estacion obtener(int posicion);
    ArrayList<Estacion> obtenerTodos();
    void actualizar(Estacion estacion);
    void eliminar(int posicion);
}

package DAO;

import CLASES.CartaSuerte;

import java.util.ArrayList;

public interface CartaSuerteDAO {

    void insertar(CartaSuerte carta);
    CartaSuerte obtener(int id);
    ArrayList<CartaSuerte> obtenerTodos();
    void actualizar(CartaSuerte carta);
    void eliminar(int id);


}

import java.util.ArrayList;

public interface JugadorDAO {

    void insertar(Jugador jugador);
    Jugador obtener(int id);
    ArrayList<Jugador> obtenerTodos();
    void actualizar(Jugador jugador);
    void eliminar(int id);
}

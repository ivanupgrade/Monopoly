import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartaSuerteDAOImpl implements CartaSuerteDAO {

    Connection conn;

    public CartaSuerteDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(CartaSuerte carta) {
    String sql = "INSERT INTO carta (tipo, valor, descripcion) VALUES (?, ?, ?)";
       try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, carta.getTipo());
            pst.setInt(2, carta.getValor());
            pst.setString(3, carta.getDescripcion());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   @Override
    public CartaSuerte obtener(int id) {
       String sql = "SELECT * FROM carta WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    CartaSuerte carta = new CartaSuerte();
                    carta.setId(rs.getInt("id"));
                    carta.setTipo(rs.getString("tipo"));
                    carta.setValor(rs.getInt("valor"));
                    carta.setDescripcion(rs.getString("descripcion"));
                    return carta;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<CartaSuerte> obtenerTodos() {
    ArrayList<CartaSuerte> cartas = new ArrayList<>();
        String sql = "SELECT * FROM carta";
        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                CartaSuerte carta = new CartaSuerte();
                carta.setId(rs.getInt("id"));
                carta.setTipo(rs.getString("tipo"));
                carta.setValor(rs.getInt("valor"));
                carta.setDescripcion(rs.getString("descripcion"));
                cartas.add(carta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartas;
    }

    @Override
    public void actualizar(CartaSuerte carta) {
        String sql = "UPDATE carta SET tipo = ?, valor = ?, descripcion = ? WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1, carta.getTipo());
            pst.setInt(2, carta.getValor());
            pst.setString(3, carta.getDescripcion());
            pst.setInt(4, carta.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM carta WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

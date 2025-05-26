package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JugadorDAOImpl implements JugadorDAO {

    Connection conn;

    @Override
    public void insertar(Jugador jugador) {
        String sql = "INSERT INTO jugadores ( nombre, p_ganadas) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, jugador.getNombre());
            pst.setInt(2, jugador.getP_ganadas());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Jugador obtener(int id) {
        String sql = "SELECT * FROM jugadores WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Jugador jugador = new Jugador();
                    jugador.setId(rs.getInt("id"));
                    jugador.setNombre(rs.getString("nombre"));
                    jugador.setP_ganadas(rs.getInt("p_ganadas"));
                    return jugador;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Jugador> obtenerTodos() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugadores";
        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setId(rs.getInt("id"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setP_ganadas(rs.getInt("p_ganadas"));
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jugadores;
    }

    @Override
    public void actualizar(Jugador jugador) {
        String sql = "UPDATE jugadores SET nombre = ?, p_ganadas = ? WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, jugador.getNombre());
            pst.setInt(2, jugador.getP_ganadas());
            pst.setInt(3, jugador.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package DAO;

import CLASES.Partida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PartidaDAOImpl implements PartidaDAO{

    Connection conn;

    public PartidaDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Partida partida) {
        String sql = "INSERT INTO partidas (fecha) VALUES (curdate())";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Partida obtener(int id) {
        String sql = "SELECT * FROM partidas WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1,id);
            try (ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    Partida partida = new Partida();
                    partida.setFecha(rs.getDate("fecha"));
                    return partida;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Partida> obtenerTodos() {
        ArrayList<Partida> partidas = new ArrayList<>();
        String sql = "SELECT * FROM partidas";
        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Partida partida = new Partida();
                partida.setFecha(rs.getDate("fecha"));
                partidas.add(partida);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void actualizar(Partida partida) {
        String sql = "UPDATE partidas SET fecha = curdate() WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, partida.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM partidas WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

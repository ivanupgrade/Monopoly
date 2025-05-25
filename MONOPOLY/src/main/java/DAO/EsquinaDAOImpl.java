package DAO;

import CLASES.Esquina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EsquinaDAOImpl implements EsquinaDAO{
    private Connection conexion;

    public EsquinaDAOImpl() {
        this.conexion = Conexion.getConexion();
    }

    @Override
    public Esquina obtener(int posicion) {
        Esquina esquina = null;
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM esquina WHERE posicion = ?")
        ) {
            preparedStatement.setInt(1, posicion);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                esquina = new Esquina(resultSet.getInt("posicion"), resultSet.getString("tipo"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return esquina;
    }

    @Override
    public ArrayList<Esquina> obtenerTodos() {
        ArrayList<Esquina> esquinas = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM esquina")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                esquinas.add(new Esquina(resultSet.getInt("posicion"), resultSet.getString("tipo")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return esquinas;
    }

    @Override
    public void actualizar(Esquina esquina) {
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE esquinas SET tipo = ? WHERE posicion = ?")
        ) {
            preparedStatement.setString(1, esquina.getTipo());
            preparedStatement.setInt(2, esquina.getPosicion());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int posicion) {
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("DELETE FROM esquinas WHERE posicion = ?")
        ) {
            preparedStatement.setInt(1, posicion);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

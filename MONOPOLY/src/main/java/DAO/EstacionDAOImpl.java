package DAO;

import CLASES.Estacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EstacionDAOImpl implements EstacionDAO{
    private Connection conexion;

    public EstacionDAOImpl() {
        this.conexion = Conexion.getConexion();
    }

    @Override
    public Estacion obtener(int posicion) {
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM calles WHERE posicion = ? AND tipo = 'estacion'")
        ) {
            preparedStatement.setInt(1, posicion);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Estacion(resultSet.getInt("posicion"), resultSet.getString("nombre"), resultSet.getInt("precio"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Estacion> obtenerTodos() {
        ArrayList<Estacion> estaciones = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM calles WHERE tipo = 'estacion'")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                estaciones.add(new Estacion(resultSet.getInt("posicion"), resultSet.getString("nombre"), resultSet.getInt("precio")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return estaciones;
    }

    @Override
    public void actualizar(Estacion estacion) {
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE calles SET nombre = ?, precio = ? WHERE posicion = ? AND tipo = 'estacion'")
        ) {
            preparedStatement.setString(1, estacion.getNombre());
            preparedStatement.setInt(2, estacion.getPrecio());
            preparedStatement.setInt(3, estacion.getPosicion());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int posicion) {
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("DELETE FROM calles WHERE posicion = ? AND tipo = 'estacion'")
        ) {
            preparedStatement.setInt(1, posicion);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

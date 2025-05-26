package DAO;

import CLASES.Estacion;
import CLASES.Normal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NormalDAOImpl implements NormalDAO{
    private Connection conexion;

    public NormalDAOImpl() {
        conexion = Conexion.getConexion();
    }

    @Override
    public Normal obtener(int posicion) {
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM calles WHERE posicion = ? AND tipo = 'normal'")
        ) {
            preparedStatement.setInt(1, posicion);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int [] tablaPrecios = {resultSet.getInt("alquiler"), resultSet.getInt("casa_1"), resultSet.getInt("casa_2"), resultSet.getInt("casa_3"), resultSet.getInt("casa_4"), resultSet.getInt("hotel")};
                return new Normal(resultSet.getInt("posicion"), resultSet.getString("nombre"), resultSet.getInt("precio"), resultSet.getString("color"), 0, resultSet.getInt("p_edificio"), tablaPrecios, null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Normal> obtenerTodos() {
        ArrayList<Normal> normales = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM calles WHERE tipo = 'normal'")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int [] tablaPrecios = {resultSet.getInt("casa_1"), resultSet.getInt("casa_2"), resultSet.getInt("casa_3"), resultSet.getInt("casa_4"), resultSet.getInt("hotel")};
                normales.add(new Normal(resultSet.getInt("posicion"), resultSet.getString("nombre"), resultSet.getInt("precio"), resultSet.getString("color"), 0, resultSet.getInt("p_edificio"), tablaPrecios, null));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return normales;
    }

    @Override
    public void actualizar(Normal normal) {
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE calles SET nombre = ?, precio = ?, color = ?, p_edificio = ?, casa_1 = ?, casa_2 = ?, casa_3 = ?, casa_4 = ?, hotel = ? WHERE posicion = ? AND tipo = 'normal'")
        ) {
            preparedStatement.setString(1, normal.getNombre());
            preparedStatement.setInt(2, normal.getPrecio());
            preparedStatement.setString(3, normal.getColor());
            preparedStatement.setInt(4, normal.getPrecioEdificio());
            preparedStatement.setInt(5, normal.getTablaPrecios()[0]);
            preparedStatement.setInt(6, normal.getTablaPrecios()[1]);
            preparedStatement.setInt(7, normal.getTablaPrecios()[2]);
            preparedStatement.setInt(8, normal.getTablaPrecios()[3]);
            preparedStatement.setInt(9, normal.getTablaPrecios()[4]);
            preparedStatement.setInt(10, normal.getPosicion());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int posicion) {
        try (
                PreparedStatement preparedStatement = conexion.prepareStatement("DELETE FROM calles WHERE posicion = ? AND tipo = 'normal'")
        ) {
            preparedStatement.setInt(1, posicion);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package DAO;

import CLASES.Suerte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SuerteDAOImpl implements SuerteDAO{
    private Connection conexion;

    public SuerteDAOImpl() {
        this.conexion = Conexion.getConexion();
    }

    @Override
    public Suerte obtener(int posicion) {
        Suerte suerte = null;
        try (
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM suerte WHERE posicion = ?")
        ) {
            preparedStatement.setInt(1, posicion);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                suerte = new Suerte(resultSet.getInt("posicion"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return suerte;
    }

    @Override
    public ArrayList<Suerte> obtenerTodos() {
        ArrayList<Suerte> casillasSuerte = new ArrayList<>();
        try (
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM suerte")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                casillasSuerte.add(new Suerte(resultSet.getInt("posicion")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return casillasSuerte;
    }

    @Override
    public void eliminar(int posicion) {
        try (
            PreparedStatement preparedStatement = conexion.prepareStatement("DELETE FROM suerte WHERE posicion = ?")
        ) {
            preparedStatement.setInt(1, posicion);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

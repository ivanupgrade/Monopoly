package DAO;

import CLASES.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class PartidaDAOImpl implements PartidaDAO{

    Connection conn;

    public PartidaDAOImpl() {
        this.conn = Conexion.getConexion();
    }

    @Override
    public void insertar(Partida partida) {
        String sql = "INSERT INTO partidas (fecha, turno) VALUES (curdate(), ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, partida.getTurno());
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
                    partida.setId(rs.getInt("id"));
                    partida.setFecha(rs.getDate("fecha"));
                    partida.setTurno(rs.getInt("turno"));
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

    public Partida obtenerExistente (int id){
        String sql = "SELECT * FROM partidas WHERE id = ?";
        String sql2 = "SELECT * FROM cartas_partidas LEFT JOIN cartas ON id = id_cartas where id_partidas = ?";
        String sql3 = "SELECT * FROM jugadores_partidas LEFT JOIN jugadores ON id = id_jugadores where id_partidas = ?";
        String sql4 = """
                SELECT * FROM partidas_jugadores_calles LEFT JOIN calles ON partidas_jugadores_calles.posicion = calles.posicion
                LEFT JOIN jugadores ON id_jugadores = jugadores.id
                WHERE partidas.id = ?
                """;
        String sql5 = "SELECT * FROM calles WHERE posicion NOT IN (SELECT posicion FROM partidas_jugadores_calles WHERE id_partidas = ?)";

        try (
                PreparedStatement pst = conn.prepareStatement(sql);
                PreparedStatement pst2 = conn.prepareStatement(sql2);
                PreparedStatement pst3 = conn.prepareStatement(sql3);
                PreparedStatement pst4 = conn.prepareStatement(sql4);
                PreparedStatement pst5 = conn.prepareStatement(sql5)

        ){
            pst.setInt(1,id);
            pst2.setInt(1, id);
            pst3.setInt(1, id);
            pst4.setInt(1, id);
            pst5.setInt(1, id);

            try (
                    ResultSet rs = pst.executeQuery();
                    ResultSet rs2 = pst2.executeQuery();
                    ResultSet rs3 = pst3.executeQuery();
                    ResultSet rs4 = pst4.executeQuery();
                    ResultSet rs5 = pst5.executeQuery()

            ){
                if(rs.next()){
                    Partida partida = new Partida();
                    partida.setId(rs.getInt("id"));
                    partida.setFecha(rs.getDate("fecha"));
                    partida.setTurno(rs.getInt("turno"));

                    ArrayList<CartaSuerte> robo = new ArrayList<>();
                    ArrayList<CartaSuerte> descarte = new ArrayList<>();
                    ArrayList<Jugador> jugadores = new ArrayList<>();
                    ArrayList<Casilla> casillas = new ArrayList<>();

                    while (rs2.next()){
                        if (rs2.getInt("baraja") == 0){
                            robo.add(new CartaSuerte(rs2.getInt("id"), rs2.getString("tipo"), rs2.getInt("valor"), rs2.getString("descripcion")));
                        } else {
                            descarte.add(new CartaSuerte(rs2.getInt("id"), rs2.getString("tipo"), rs2.getInt("valor"), rs2.getString("descripcion")));
                        }
                    }

                    while (rs3.next()){
                        ArrayList<Calle> calles = new ArrayList<>();
                        jugadores.add(new Jugador(rs3.getInt("id"), rs3.getInt("p_ganadas"), rs3.getString("nombre"), rs3.getInt("posicion"), rs3.getInt("dinero"),calles ,rs3.getBoolean("encarcelado")));
                    }

                    while (rs4.next()){
                        if (rs4.getString("tipo").equals("normal")){
                            int [] tablaPrecios = {rs4.getInt("alquiler"), rs4.getInt("casa_1"), rs4.getInt("casa_2"), rs4.getInt("casa_3"), rs4.getInt("casa_4"), rs4.getInt("hotel")};

                            for (Jugador jugador : jugadores){
                                if (jugador.getId() == rs4.getInt("id_jugadores")){
                                    Normal normal = new Normal(rs4.getInt("posicion"), rs4.getString("nombre"), rs4.getInt("precio"), rs4.getString("color"), rs4.getInt("n_casas"), rs4.getInt("p_edificio"), tablaPrecios, jugador);
                                    jugador.getCalles().add(normal);
                                    casillas.add(normal);
                                }
                            }
                        } else {
                            for (Jugador jugador : jugadores){
                                if (jugador.getId() == rs4.getInt("id_jugadores")){
                                    Estacion estacion = new Estacion(rs4.getInt("posicion"), rs4.getString("nombre"), rs4.getInt("precio"), jugador);
                                    jugador.getCalles().add(estacion);
                                    casillas.add(estacion);
                                }
                            }
                        }
                    }

                    while (rs5.next()){
                        if (rs5.getString("tipo").equals("normal")){
                            int [] tablaPrecios = {rs5.getInt("alquiler"), rs5.getInt("casa_1"), rs5.getInt("casa_2"), rs5.getInt("casa_3"), rs5.getInt("casa_4"), rs5.getInt("hotel")};
                            Normal normal = new Normal(rs5.getInt("posicion"), rs5.getString("nombre"), rs5.getInt("precio"), rs5.getString("color"), 0, rs5.getInt("p_edificio"), tablaPrecios, null);
                            casillas.add(normal);
                        } else {
                            Estacion estacion = new Estacion(rs5.getInt("posicion"), rs5.getString("nombre"), rs5.getInt("precio"), null);
                            casillas.add(estacion);
                        }
                    }

                    Baraja mazoRobo = new Baraja(robo);
                    Baraja mazoDescarte = new Baraja(descarte);

                    SuerteDAOImpl casillasSuerte = new SuerteDAOImpl();
                    EsquinaDAOImpl casillasEsquina = new EsquinaDAOImpl();

                    casillas.addAll(casillasSuerte.obtenerTodos());
                    casillas.addAll(casillasEsquina.obtenerTodos());

                    partida.setMazoRobo(mazoRobo);
                    partida.setMazoDescarte(mazoDescarte);
                    partida.setJugadores(jugadores);
                    partida.setCasillas(casillas);

                    return partida;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

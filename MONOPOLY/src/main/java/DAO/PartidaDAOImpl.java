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
        String sql3 = "SELECT * FROM partidas_jugadores LEFT JOIN jugadores ON id = id_jugadores where id_partidas = ?";
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


    public void actualizarExistente(Partida partida) {

        String sql = "UPDATE partidas SET fecha = curdate(), turno = ? WHERE id = ?";
        String sql2 = "UPDATE cartas_partidas SET baraja = ? WHERE carta_id = ? AND partida_id = ?";
        String sql3 = "UPDATE partidas_jugadores SET encarcelado = ?, dinero = ?, posicion = ? WHERE jugador_id = ? AND partida_id = ?";
        String sql4 = "UPDATE partidas_jugadores_calles SET n_casas = ?, alquiler_mod = ?, jugador_id = ? WHERE partida_id = ? AND posicion = ?";

        try (
                PreparedStatement pst = conn.prepareStatement(sql);
                PreparedStatement pst2 = conn.prepareStatement(sql2);
                PreparedStatement pst3 = conn.prepareStatement(sql3);
                PreparedStatement pst4 = conn.prepareStatement(sql4)
        ){
            //sql
            pst.setInt(1,partida.getTurno());
            pst.setInt(2,partida.getId());

            //sql2
            for (CartaSuerte carta : partida.getMazoRobo().getBaraja()){
                pst2.setInt(1,0);
                pst2.setInt(2,carta.getId());
                pst2.setInt(3,partida.getId());
                pst2.addBatch();
            }

            for (CartaSuerte carta : partida.getMazoDescarte().getBaraja()){
                pst2.setInt(1,1);
                pst2.setInt(2,carta.getId());
                pst2.setInt(3,partida.getId());
                pst2.addBatch();
            }

            //sql3
            for (Jugador jugador : partida.getJugadores()){
                pst3.setBoolean(1,jugador.isEncarcelado());
                pst3.setInt(2, jugador.getDinero());
                pst3.setInt(3,jugador.getPosicion());
                pst3.setInt(4, jugador.getId());
                pst3.setInt(5, partida.getId());
                pst3.addBatch();
            }

            //sql4 String sql4 = "UPDATE partidas_jugadores_calles SET n_casas = ?, alquiler_mod = ?, jugador_id = ? WHERE partida_id = ? AND posicion = ?";
            for (Jugador jugador : partida.getJugadores()){
                if (!jugador.getCalles().isEmpty()){
                    for (Calle calle : jugador.getCalles()){
                        if (calle instanceof Normal){
                            pst4.setInt(1, ((Normal) calle).getNumCasas());
                        }else {
                            pst4.setInt(1,0);
                        }
                        pst4.setInt(2,calle.getAlquiler());
                        pst4.setInt(3,jugador.getId());
                        pst4.setInt(4,partida.getId());
                        pst4.setInt(5,calle.getPosicion());
                        pst4.addBatch();
                    }
                }
            }

            pst.executeUpdate();
            pst2.executeBatch();
            pst3.executeBatch();
            pst4.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package CLASES;

public class CartaSuerte implements Efectos{

    private int id;
    private String tipo;
    private int valor;
    private String descripcion;

    public CartaSuerte() {
    }

    public CartaSuerte(int id, String tipo, int valor, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    @Override
    public void aplicarEfecto(Jugador jugador, Partida partida) {
        System.out.println(descripcion);

        if (tipo.equals("pagar")){
            jugador.setDinero(jugador.getDinero()-valor);
        } else if (tipo.equals("cobrar")) {
            jugador.setDinero(jugador.getDinero()+valor);
        } else {
            int mov_casilla;
            if (valor<0){
                mov_casilla = jugador.getPosicion()-valor;
            } else {
                mov_casilla = valor-jugador.getPosicion();
            }

            if (mov_casilla >= 0){
                jugador.mover(mov_casilla);
            } else {
                mov_casilla += 28;
                jugador.mover(mov_casilla);
            }

            partida.getCasillas().get(valor).aplicarEfecto(jugador, partida);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

public class CartaSuerte implements Efectos{

    private int id;
    private String tipo;
    private int valor;
    private String descripcion;

    public CartaSuerte(int id, String tipo, int valor, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    @Override
    public void aplicarEfecto(Partida partida) {

    }
}

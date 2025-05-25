package CLASES;

public class Normal extends Calle{

    private String color;
    private int numCasas;
    private int precioEdificio;
    private int[] tablaPrecios;

    public Normal(int posicion, String nombre, int precio, String color, int numCasas, int precioEdificio, int[] tablaPrecios) {
        super(posicion, nombre, tablaPrecios[numCasas], precio);
        this.color = color;
        this.numCasas = numCasas;
        this.precioEdificio = precioEdificio;
        this.tablaPrecios = tablaPrecios;
    }

    @Override
    public void modificarAlquiler(Jugador jugador) {
        setAlquiler(tablaPrecios[numCasas]);
    }

    public void construirCasa (Jugador jugador){

    }
}

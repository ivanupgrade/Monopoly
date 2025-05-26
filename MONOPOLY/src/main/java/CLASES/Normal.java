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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumCasas() {
        return numCasas;
    }

    public void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }

    public int getPrecioEdificio() {
        return precioEdificio;
    }

    public void setPrecioEdificio(int precioEdificio) {
        this.precioEdificio = precioEdificio;
    }

    public int[] getTablaPrecios() {
        return tablaPrecios;
    }

    public void setTablaPrecios(int[] tablaPrecios) {
        this.tablaPrecios = tablaPrecios;
    }

    @Override
    public void modificarAlquiler(Jugador jugador) {
        setAlquiler(tablaPrecios[numCasas]);
    }

    public void construirCasa (Jugador jugador){
        if (numCasas < 5) {
            if (jugador.getDinero() > precioEdificio) {
                jugador.setDinero(jugador.getDinero()-precioEdificio);
                numCasas++;
                modificarAlquiler(jugador);
            }else {
                System.out.println("Dinero insuficiente");
            }

        }else {
            System.out.println("Has alcanzado el numero máximo de casas");
        }
    }
}

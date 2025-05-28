package CLASES;

/**
 * Class representing a "Chance" card in the Monopoly game.
 */
public class CartaSuerte implements Efectos{

    private int id;
    private String tipo;
    private int valor;
    private String descripcion;

    /**
     * Default constructor for CartaSuerte.
     * Initializes a new instance of CartaSuerte with default values.
     */
    public CartaSuerte() {
    }

    /**
     * Constructor for CartaSuerte with parameters.
     * @param id The unique identifier for the card.
     * @param tipo The type of effect the card has (e.g., "pagar", "cobrar", "mover").
     * @param valor The value associated with the card's effect.
     * @param descripcion A description of the card's effect.
     */
    public CartaSuerte(int id, String tipo, int valor, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    /**
     * Returns a string representation of the CartaSuerte object.
     * @return A string containing the card's ID, type, value, and description.
     */
    @Override
    public void aplicarEfecto(Jugador jugador, Partida partida) {
        System.out.println(descripcion);

        if (tipo.equals("pagar")){
            jugador.setDinero(jugador.getDinero()-valor);

        } else if (tipo.equals("cobrar")) {
            jugador.setDinero(jugador.getDinero()+valor);

        } else {
            int mov_casilla;

            if (valor == 21){
                jugador.setPosicion(27);

            } else {
                if (valor < 0) {
                    mov_casilla = jugador.getPosicion() - valor;
                } else {
                    mov_casilla = valor - jugador.getPosicion();
                }

                if (mov_casilla >= 0) {
                    jugador.mover(mov_casilla);
                } else {
                    mov_casilla += 28;
                    jugador.mover(mov_casilla);
                }
            }

            for (Casilla casilla : partida.getCasillas()) {
                if (casilla.getPosicion() == jugador.getPosicion()) {
                    casilla.aplicarEfecto(jugador, partida);
                    break;
                }
            }
        }
    }

    /**
     * Returns a string representation of the CartaSuerte object.
     * @return A string containing the card's ID, type, value, and description.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the CartaSuerte card.
     * @param id The unique identifier to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the type of effect the CartaSuerte card has.
     * @return The type of effect (e.g., "pagar", "cobrar", "mover").
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the type of effect for the CartaSuerte card.
     * @param tipo The type of effect to set.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Returns the value associated with the CartaSuerte card's effect.
     * @return The value of the card.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Sets the value associated with the CartaSuerte card's effect.
     * @param valor The value to set for the card.
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Returns the description of the CartaSuerte card's effect.
     * @return The description of the card.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the description of the CartaSuerte card's effect.
     * @param descripcion The description to set for the card.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

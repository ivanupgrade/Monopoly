import java.util.ArrayList;
import java.util.Collections;

public class Baraja {

    private ArrayList<CartaSuerte> baraja;

    public Baraja(ArrayList<CartaSuerte> baraja) {
        this.baraja = baraja;
    }

    public void barajar() {
        Collections.shuffle(baraja);
    }

    public void reponerBaraja (Baraja baraja){

    }

    public CartaSuerte robarCarta(){
        CartaSuerte carta;
        carta = baraja.getFirst();
        baraja.removeFirst();
        return carta;
    }

}

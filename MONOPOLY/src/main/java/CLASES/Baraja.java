package CLASES;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Baraja class represents a deck of cards used in the game.
 * It contains methods to shuffle the deck, draw cards, and manage the deck.
 */
public class Baraja {
    /**
     * The deck of cards represented as an ArrayList of CartaSuerte objects.
     */
    private ArrayList<CartaSuerte> baraja;
    /**
     * Default constructor that initializes the deck with an empty list.
     */
    public Baraja(ArrayList<CartaSuerte> baraja) {
        this.baraja = baraja;
    }
    /**
     * Default constructor that initializes the deck with an empty list.
     */
    public ArrayList<CartaSuerte> getBaraja() {
        return baraja;
    }
    /**
     * Sets the deck of cards.
     * @param baraja
     */
    public void setBaraja(ArrayList<CartaSuerte> baraja) {
        this.baraja = baraja;
    }
    /**
     * Shuffles the deck of cards.
     */
    public void barajar() {
        Collections.shuffle(baraja);
    }

    /**
     * Replaces the current deck with a new one.
     * @param baraja
     */
    public void reponerBaraja (Baraja baraja){
        this.baraja = baraja.getBaraja();
        baraja.getBaraja().clear();
        barajar();
    }

    /**
     * Returns and removes the first card from the deck.
     * @return
     */
    public CartaSuerte robarCarta(){
        CartaSuerte carta;
        carta = baraja.getFirst();
        baraja.removeFirst();
        return carta;
    }

}

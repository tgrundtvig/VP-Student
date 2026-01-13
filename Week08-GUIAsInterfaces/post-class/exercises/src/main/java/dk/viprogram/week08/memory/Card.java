package dk.viprogram.week08.memory;

/**
 * Represents a card in the Memory game.
 *
 * Each card has:
 * - A unique id (to distinguish cards with the same symbol)
 * - A symbol (the emoji shown when face up)
 * - faceUp state (is the card currently visible?)
 * - matched state (has this card been matched and removed from play?)
 */
public record Card(
        int id,
        String symbol,
        boolean faceUp,
        boolean matched
) {
    /**
     * The symbol shown on the back of all cards.
     */
    public static final String CARD_BACK = "?";

    /**
     * Returns what should be displayed for this card.
     * - If matched: the symbol (shown faded via styling)
     * - If face up: the symbol
     * - If face down: the card back
     */
    public String display() {
        if (matched || faceUp) {
            return symbol;
        }
        return CARD_BACK;
    }

    /**
     * Returns a new Card with faceUp set to true.
     */
    public Card flip() {
        return new Card(id, symbol, true, matched);
    }

    /**
     * Returns a new Card with faceUp set to false.
     */
    public Card flipDown() {
        return new Card(id, symbol, false, matched);
    }

    /**
     * Returns a new Card marked as matched.
     */
    public Card markMatched() {
        return new Card(id, symbol, faceUp, true);
    }
}

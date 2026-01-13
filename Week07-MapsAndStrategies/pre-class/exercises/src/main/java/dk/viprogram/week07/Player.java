package dk.viprogram.week07;

/**
 * A simple player record for demonstrating maps and sorting strategies.
 *
 * Records are perfect for data objects - they automatically provide:
 * - Constructor
 * - Getters (name(), score(), level())
 * - equals(), hashCode(), toString()
 *
 * @param name  the player's name
 * @param score the player's score
 * @param level the player's level (1-100)
 */
public record Player(String name, int score, int level) {

    /**
     * Creates a player with default level of 1.
     *
     * @param name  the player's name
     * @param score the player's score
     */
    public Player(String name, int score) {
        this(name, score, 1);
    }
}

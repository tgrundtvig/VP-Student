package dk.viprogram.week04;

/**
 * Represents a reward that can be granted to a player.
 * This interface is PROVIDED - you should use it for your implementations.
 */
public interface Reward {
    /**
     * Get a description of this reward for display.
     */
    String getDescription();

    /**
     * Grant this reward to the specified player.
     */
    void grantTo(Player player);
}

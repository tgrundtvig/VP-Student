package dk.viprogram.week04;

/**
 * Interface for rewards that can be granted to a player.
 * 
 * This interface is PROVIDED - you will implement classes that use it.
 */
public interface Reward {
    
    /**
     * Get a description of this reward for display.
     */
    String getDescription();
    
    /**
     * Grant this reward to a player.
     */
    void grantTo(Player player);
}

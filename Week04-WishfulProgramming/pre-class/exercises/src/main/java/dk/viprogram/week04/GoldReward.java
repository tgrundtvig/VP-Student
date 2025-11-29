package dk.viprogram.week04;

/**
 * Exercise 2a: Implement the GoldReward class.
 * 
 * A GoldReward grants a specific amount of gold to a player.
 * 
 * TODO: Implement the Reward interface
 * - Store the gold amount in a field
 * - getDescription() should return something like "50 gold"
 * - grantTo() should add the gold to the player
 */
public class GoldReward implements Reward {

    // TODO: Add a field for the gold amount

    public GoldReward(int amount) {
        // TODO: Store the amount in a field
    }

    @Override
    public String getDescription() {
        // TODO: Return a description like "50 gold"
        throw new UnsupportedOperationException("Implement this method");
    }
    
    @Override
    public void grantTo(Player player) {
        // TODO: Add the gold to the player
        throw new UnsupportedOperationException("Implement this method");
    }
}

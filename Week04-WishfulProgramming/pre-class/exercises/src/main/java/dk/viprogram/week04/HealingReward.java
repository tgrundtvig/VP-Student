package dk.viprogram.week04;

/**
 * Exercise 2c: Implement the HealingReward class.
 * 
 * A HealingReward restores health to a player.
 * 
 * TODO: Implement the Reward interface
 * - Store the healing amount in a field
 * - getDescription() should return something like "Restore 30 HP"
 * - grantTo() should heal the player
 */
public class HealingReward implements Reward {

    // TODO: Add a field for the healing amount

    public HealingReward(int amount) {
        // TODO: Store the amount in a field
    }

    @Override
    public String getDescription() {
        // TODO: Return a description like "Restore 30 HP"
        throw new UnsupportedOperationException("Implement this method");
    }
    
    @Override
    public void grantTo(Player player) {
        // TODO: Heal the player
        throw new UnsupportedOperationException("Implement this method");
    }
}

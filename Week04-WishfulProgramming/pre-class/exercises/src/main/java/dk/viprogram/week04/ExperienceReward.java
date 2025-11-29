package dk.viprogram.week04;

/**
 * Exercise 2b: Implement the ExperienceReward class.
 * 
 * An ExperienceReward grants experience points to a player.
 * 
 * TODO: Implement the Reward interface
 * - Store the XP amount in a field
 * - getDescription() should return something like "100 XP"
 * - grantTo() should add the experience to the player
 */
public class ExperienceReward implements Reward {

    // TODO: Add a field for the XP amount

    public ExperienceReward(int amount) {
        // TODO: Store the amount in a field
    }

    @Override
    public String getDescription() {
        // TODO: Return a description like "100 XP"
        throw new UnsupportedOperationException("Implement this method");
    }
    
    @Override
    public void grantTo(Player player) {
        // TODO: Add the experience to the player
        throw new UnsupportedOperationException("Implement this method");
    }
}

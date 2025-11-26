package dk.viprogram.week04;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exercise 2d: Implement the CompositeReward class.
 * 
 * A CompositeReward combines multiple rewards into one.
 * When granted, ALL contained rewards are granted.
 * 
 * This demonstrates the Composite pattern - treating a group
 * of objects the same as a single object.
 * 
 * TODO: Implement the Reward interface
 * - Store a list of rewards
 * - getDescription() should combine all descriptions (e.g., "50 gold, 100 XP")
 * - grantTo() should grant ALL rewards to the player
 */
public class CompositeReward implements Reward {
    
    // TODO: Add a field for the list of rewards
    
    // TODO: Add a constructor that takes Reward... (varargs)
    
    @Override
    public String getDescription() {
        // TODO: Combine all reward descriptions with ", "
        // Hint: Use streams or a loop
        throw new UnsupportedOperationException("Implement this method");
    }
    
    @Override
    public void grantTo(Player player) {
        // TODO: Grant each reward to the player
        throw new UnsupportedOperationException("Implement this method");
    }
}

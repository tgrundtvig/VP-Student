package dk.viprogram.week04;

import java.util.List;

/**
 * Exercise 1: Implement the Quest class using wishful programming.
 *
 * A Quest represents a complete quest with:
 * - A name and description
 * - One or more objectives that must be completed
 * - A reward granted upon completion
 *
 * Design the class first by thinking about what you WISH you had,
 * then implement it.
 *
 * Hint: Use the provided interfaces (QuestObjective, Reward) and
 * the implementations you created in pre-class exercises.
 *
 * TODO:
 * 1. Add fields for name, description, objectives (List), and reward
 * 2. Create a constructor that accepts these values
 * 3. Implement getName(), getDescription()
 * 4. Implement getObjectives() - returns the list of objectives
 * 5. Implement isComplete() - true if ALL objectives are fulfilled
 * 6. Implement getReward() - returns the reward
 * 7. Implement complete(Player) - grants reward if quest is complete
 */
public class Quest {

    // TODO: Add fields

    // TODO: Add constructor

    public String getName() {
        // TODO: Implement
        throw new UnsupportedOperationException("Implement this method");
    }

    public String getDescription() {
        // TODO: Implement
        throw new UnsupportedOperationException("Implement this method");
    }

    public List<QuestObjective> getObjectives() {
        // TODO: Implement
        throw new UnsupportedOperationException("Implement this method");
    }

    public boolean isComplete() {
        // TODO: Return true if ALL objectives are fulfilled
        // Hint: Use streams or a loop
        throw new UnsupportedOperationException("Implement this method");
    }

    public Reward getReward() {
        // TODO: Implement
        throw new UnsupportedOperationException("Implement this method");
    }

    /**
     * Complete the quest and grant the reward to the player.
     *
     * @param player the player to grant the reward to
     * @return true if the quest was completed, false if not all objectives are done
     */
    public boolean complete(Player player) {
        // TODO: If isComplete(), grant reward to player and return true
        // Otherwise return false
        throw new UnsupportedOperationException("Implement this method");
    }
}

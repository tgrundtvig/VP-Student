package dk.viprogram.week04;

/**
 * Exercise 3a: Implement the KillCountObjective class.
 * 
 * A KillCountObjective tracks how many monsters need to be killed.
 * 
 * Example: "Kill 5 Goblins" - requires killing 5, tracks current count.
 * 
 * TODO: Implement the QuestObjective interface
 * - Store target name (e.g., "Goblin"), required count, and current count
 * - getDescription() should return something like "Kill 5 Goblins (2/5)"
 * - isFulfilled() returns true when current >= required
 * - recordProgress(1) adds to the kill count
 */
public class KillCountObjective implements QuestObjective {

    // TODO: Add fields for targetName, requiredCount, currentCount

    public KillCountObjective(String targetName, int requiredCount) {
        // TODO: Store targetName and requiredCount, initialize currentCount to 0
    }

    @Override
    public String getDescription() {
        // TODO: Return description showing progress, e.g., "Kill 5 Goblins (2/5)"
        throw new UnsupportedOperationException("Implement this method");
    }
    
    @Override
    public boolean isFulfilled() {
        // TODO: Return true if currentCount >= requiredCount
        throw new UnsupportedOperationException("Implement this method");
    }
    
    @Override
    public void recordProgress(int amount) {
        // TODO: Add amount to currentCount
        throw new UnsupportedOperationException("Implement this method");
    }
}

package dk.viprogram.week04;

/**
 * Exercise 3b: Implement the CollectGoldObjective class.
 * 
 * A CollectGoldObjective tracks gold collection progress.
 * 
 * Example: "Collect 100 gold" - tracks total gold collected toward goal.
 * 
 * TODO: Implement the QuestObjective interface
 * - Store required amount and current amount
 * - getDescription() should return something like "Collect 100 gold (45/100)"
 * - isFulfilled() returns true when current >= required
 * - recordProgress(amount) adds gold collected
 */
public class CollectGoldObjective implements QuestObjective {

    // TODO: Add fields for requiredAmount and currentAmount

    public CollectGoldObjective(int requiredAmount) {
        // TODO: Store requiredAmount, initialize currentAmount to 0
    }

    @Override
    public String getDescription() {
        // TODO: Return description showing progress
        throw new UnsupportedOperationException("Implement this method");
    }
    
    @Override
    public boolean isFulfilled() {
        // TODO: Return true if currentAmount >= requiredAmount
        throw new UnsupportedOperationException("Implement this method");
    }
    
    @Override
    public void recordProgress(int amount) {
        // TODO: Add amount to currentAmount
        throw new UnsupportedOperationException("Implement this method");
    }
}

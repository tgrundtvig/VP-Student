package dk.viprogram.week04;

/**
 * Exercise 3: Design and implement the QuestObjective interface and implementations.
 * 
 * A QuestObjective represents a single goal within a quest.
 * 
 * This interface is PROVIDED as a starting point. You may modify it if needed.
 */
public interface QuestObjective {
    
    /**
     * Get a description of this objective for display.
     */
    String getDescription();
    
    /**
     * Check if this objective has been fulfilled.
     */
    boolean isFulfilled();
    
    /**
     * Record progress toward this objective.
     * 
     * @param amount the amount of progress (interpretation depends on objective type)
     */
    void recordProgress(int amount);
}

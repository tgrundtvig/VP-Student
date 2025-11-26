package dk.viprogram.week04;

/**
 * Represents a single objective within a quest.
 * This interface is PROVIDED - you should use it for your implementations.
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

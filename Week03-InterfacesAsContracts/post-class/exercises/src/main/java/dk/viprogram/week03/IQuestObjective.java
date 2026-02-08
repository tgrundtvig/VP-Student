package dk.viprogram.week03;

/**
 * Interface for quest objectives.
 *
 * Different objective types implement this interface.
 * No if-else chains needed to check objective type!
 */
public interface IQuestObjective {
    String getDescription();
    boolean isFulfilled();
    void updateProgress(String eventType, String target, int amount);
    String getProgressDescription();
}

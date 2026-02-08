package dk.viprogram.week03;

/**
 * Quest objective for killing monsters.
 *
 * Each objective type is a separate class - no if-else chains!
 */
public class KillObjective implements IQuestObjective {
    private final String monsterType;
    private final int requiredCount;
    private int currentCount;

    public KillObjective(String monsterType, int requiredCount) {
        this.monsterType = monsterType;
        this.requiredCount = requiredCount;
        this.currentCount = 0;
    }

    @Override
    public String getDescription() {
        // TODO: Return a description like "Kill X MonsterType(s)"
        // where X is the required count and MonsterType is the monster type
        return null;
    }

    @Override
    public boolean isFulfilled() {
        // TODO: Return true if the current count is at least the required count
        return false;
    }

    @Override
    public void updateProgress(String eventType, String target, int amount) {
        // TODO: If the eventType is "MONSTER_KILLED" AND the target matches
        // the monsterType, add the amount to currentCount.
        // Ignore other event types or wrong monster types.
    }

    @Override
    public String getProgressDescription() {
        // TODO: Return a progress string like "Kill MonsterType: X/Y"
        // where X is currentCount and Y is requiredCount
        return null;
    }
}

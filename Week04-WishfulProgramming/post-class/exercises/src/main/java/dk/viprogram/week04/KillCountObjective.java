package dk.viprogram.week04;

/**
 * An objective that tracks killing a certain number of monsters.
 * Copy your implementation from pre-class exercises, or implement fresh.
 */
public class KillCountObjective implements QuestObjective {

    private final String targetName;
    private final int requiredCount;
    private int currentCount;

    public KillCountObjective(String targetName, int requiredCount) {
        this.targetName = targetName;
        this.requiredCount = requiredCount;
        this.currentCount = 0;
    }

    public String getTargetName() {
        return targetName;
    }

    @Override
    public String getDescription() {
        return String.format("Kill %d %ss (%d/%d)", requiredCount, targetName, currentCount, requiredCount);
    }

    @Override
    public boolean isFulfilled() {
        return currentCount >= requiredCount;
    }

    @Override
    public void recordProgress(int amount) {
        currentCount += amount;
    }
}

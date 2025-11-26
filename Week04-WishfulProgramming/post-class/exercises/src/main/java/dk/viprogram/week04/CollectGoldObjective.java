package dk.viprogram.week04;

/**
 * An objective that tracks collecting a certain amount of gold.
 * Copy your implementation from pre-class exercises, or implement fresh.
 */
public class CollectGoldObjective implements QuestObjective {

    private final int requiredAmount;
    private int currentAmount;

    public CollectGoldObjective(int requiredAmount) {
        this.requiredAmount = requiredAmount;
        this.currentAmount = 0;
    }

    @Override
    public String getDescription() {
        return String.format("Collect %d gold (%d/%d)", requiredAmount, currentAmount, requiredAmount);
    }

    @Override
    public boolean isFulfilled() {
        return currentAmount >= requiredAmount;
    }

    @Override
    public void recordProgress(int amount) {
        currentAmount += amount;
    }
}

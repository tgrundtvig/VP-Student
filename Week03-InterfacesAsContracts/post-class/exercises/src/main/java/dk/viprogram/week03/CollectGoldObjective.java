package dk.viprogram.week03;

/**
 * Quest objective for collecting gold.
 */
public class CollectGoldObjective implements IQuestObjective {
    private final int requiredAmount;
    private int currentAmount;

    public CollectGoldObjective(int requiredAmount) {
        this.requiredAmount = requiredAmount;
        this.currentAmount = 0;
    }

    @Override
    public String getDescription() {
        // TODO: Return a description like "Collect X gold" where X is the required amount
        return null;
    }

    @Override
    public boolean isFulfilled() {
        // TODO: Return true if the current amount is at least the required amount
        return false;
    }

    @Override
    public void updateProgress(String eventType, String target, int amount) {
        // TODO: If the eventType is "GOLD_COLLECTED", add the amount to currentAmount.
        // Ignore other event types.
    }

    @Override
    public String getProgressDescription() {
        // TODO: Return a progress string like "Collect gold: X/Y"
        // where X is currentAmount and Y is requiredAmount
        return null;
    }
}

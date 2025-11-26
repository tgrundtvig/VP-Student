package dk.viprogram.week04;

/**
 * A reward that grants experience points to the player.
 * Copy your implementation from pre-class exercises, or implement fresh.
 */
public class ExperienceReward implements Reward {

    private final int amount;

    public ExperienceReward(int amount) {
        this.amount = amount;
    }

    @Override
    public String getDescription() {
        return amount + " XP";
    }

    @Override
    public void grantTo(Player player) {
        player.addExperience(amount);
    }
}

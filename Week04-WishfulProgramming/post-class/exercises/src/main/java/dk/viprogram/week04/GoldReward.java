package dk.viprogram.week04;

/**
 * A reward that grants gold to the player.
 * Copy your implementation from pre-class exercises, or implement fresh.
 */
public class GoldReward implements Reward {

    private final int amount;

    public GoldReward(int amount) {
        this.amount = amount;
    }

    @Override
    public String getDescription() {
        return amount + " gold";
    }

    @Override
    public void grantTo(Player player) {
        player.addGold(amount);
    }
}

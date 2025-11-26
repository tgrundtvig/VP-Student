package dk.viprogram.week04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A reward that combines multiple rewards.
 * Copy your implementation from pre-class exercises, or implement fresh.
 */
public class CompositeReward implements Reward {

    private final List<Reward> rewards;

    public CompositeReward(Reward... rewards) {
        this.rewards = Arrays.asList(rewards);
    }

    @Override
    public String getDescription() {
        return rewards.stream()
            .map(Reward::getDescription)
            .collect(Collectors.joining(", "));
    }

    @Override
    public void grantTo(Player player) {
        rewards.forEach(reward -> reward.grantTo(player));
    }
}

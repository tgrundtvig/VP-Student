package dk.viprogram.week03;

import java.util.List;

/**
 * Basic implementation of IQuest.
 *
 * Notice: Uses IQuestObjective interface, so ANY objective type works.
 */
public class BasicQuest implements IQuest {
    private final String name;
    private final String description;
    private final List<IQuestObjective> objectives;
    private final int goldReward;
    private final int xpReward;

    public BasicQuest(String name, String description,
                      List<IQuestObjective> objectives,
                      int goldReward, int xpReward) {
        this.name = name;
        this.description = description;
        this.objectives = objectives;
        this.goldReward = goldReward;
        this.xpReward = xpReward;
    }

    @Override
    public String getName() {
        // TODO: Return the quest name
        return null;
    }

    @Override
    public String getDescription() {
        // TODO: Return the quest description
        return null;
    }

    @Override
    public List<IQuestObjective> getObjectives() {
        // TODO: Return the list of objectives
        return null;
    }

    @Override
    public boolean isComplete() {
        // TODO: Return true if ALL objectives are fulfilled.
        // Hint: You can use a loop or the stream API (objectives.stream().allMatch(...))
        return false;
    }

    @Override
    public void grantReward(IPlayer player) {
        // TODO: If the quest is complete, grant gold and XP rewards to the player.
        // If the quest is NOT complete, do nothing.
    }

    @Override
    public int getGoldReward() {
        // TODO: Return the gold reward amount
        return 0;
    }

    @Override
    public int getXPReward() {
        // TODO: Return the XP reward amount
        return 0;
    }
}

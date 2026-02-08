package dk.viprogram.week03;

import java.util.List;

/**
 * Interface for quests.
 */
public interface IQuest {
    String getName();
    String getDescription();
    List<IQuestObjective> getObjectives();
    boolean isComplete();
    void grantReward(IPlayer player);
    int getGoldReward();
    int getXPReward();
}

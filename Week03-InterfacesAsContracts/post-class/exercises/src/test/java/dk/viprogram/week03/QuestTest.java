package dk.viprogram.week03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Quest system using interfaces.
 *
 * Notice: We can test quests in complete isolation using MockPlayer!
 */
class QuestTest {

    @Test
    @DisplayName("Kill objective tracks progress")
    void killObjectiveTracksProgress() {
        IQuestObjective objective = new KillObjective("Goblin", 5);

        assertFalse(objective.isFulfilled());

        objective.updateProgress("MONSTER_KILLED", "Goblin", 3);
        assertFalse(objective.isFulfilled());

        objective.updateProgress("MONSTER_KILLED", "Goblin", 2);
        assertTrue(objective.isFulfilled());
    }

    @Test
    @DisplayName("Kill objective ignores wrong monster type")
    void killObjectiveIgnoresWrongMonster() {
        IQuestObjective objective = new KillObjective("Goblin", 5);

        objective.updateProgress("MONSTER_KILLED", "Orc", 10);

        assertFalse(objective.isFulfilled());
    }

    @Test
    @DisplayName("Collect gold objective tracks progress")
    void collectGoldTracksProgress() {
        IQuestObjective objective = new CollectGoldObjective(100);

        objective.updateProgress("GOLD_COLLECTED", "", 50);
        assertFalse(objective.isFulfilled());

        objective.updateProgress("GOLD_COLLECTED", "", 50);
        assertTrue(objective.isFulfilled());
    }

    @Test
    @DisplayName("Quest with single objective")
    void questWithSingleObjective() {
        IQuestObjective objective = new KillObjective("Goblin", 2);
        IQuest quest = new BasicQuest(
            "Goblin Slayer",
            "Kill the goblins",
            List.of(objective),
            100, 50
        );

        assertFalse(quest.isComplete());

        objective.updateProgress("MONSTER_KILLED", "Goblin", 2);

        assertTrue(quest.isComplete());
    }

    @Test
    @DisplayName("Quest with multiple objectives requires all")
    void questWithMultipleObjectives() {
        IQuestObjective kill = new KillObjective("Goblin", 2);
        IQuestObjective gold = new CollectGoldObjective(50);

        IQuest quest = new BasicQuest(
            "Double Trouble",
            "Kill goblins and collect gold",
            List.of(kill, gold),
            200, 100
        );

        kill.updateProgress("MONSTER_KILLED", "Goblin", 2);
        assertFalse(quest.isComplete());  // Still need gold

        gold.updateProgress("GOLD_COLLECTED", "", 50);
        assertTrue(quest.isComplete());
    }

    @Test
    @DisplayName("Quest grants rewards to player")
    void questGrantsRewards() {
        IQuestObjective objective = new KillObjective("Goblin", 1);
        objective.updateProgress("MONSTER_KILLED", "Goblin", 1);

        IQuest quest = new BasicQuest(
            "Quick Quest",
            "Kill one goblin",
            List.of(objective),
            100, 50
        );

        MockPlayer player = new MockPlayer("TestHero");
        int initialGold = player.getGold();

        quest.grantReward(player);

        assertEquals(initialGold + 100, player.getGold());
    }

    @Test
    @DisplayName("Incomplete quest does not grant rewards")
    void incompleteQuestNoRewards() {
        IQuestObjective objective = new KillObjective("Goblin", 5);

        IQuest quest = new BasicQuest(
            "Hard Quest",
            "Kill many goblins",
            List.of(objective),
            100, 50
        );

        MockPlayer player = new MockPlayer("TestHero");
        int initialGold = player.getGold();

        quest.grantReward(player);

        assertEquals(initialGold, player.getGold());  // No reward given
    }

    @Test
    @DisplayName("Different objective types can be mixed")
    void mixedObjectiveTypes() {
        // This demonstrates the power of interfaces!
        IQuestObjective[] objectives = {
            new KillObjective("Goblin", 1),
            new CollectGoldObjective(10)
        };

        // All can be treated uniformly
        for (IQuestObjective objective : objectives) {
            assertFalse(objective.isFulfilled());
            assertNotNull(objective.getDescription());
            assertNotNull(objective.getProgressDescription());
        }
    }
}

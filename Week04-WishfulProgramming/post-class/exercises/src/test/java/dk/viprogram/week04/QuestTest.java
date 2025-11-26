package dk.viprogram.week04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Quest class.
 */
class QuestTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestHero", 100, 10);
    }

    @Nested
    @DisplayName("Exercise 1: Quest class")
    class QuestClassTests {

        @Test
        @DisplayName("Quest should have name and description")
        void questHasNameAndDescription() {
            Quest quest = new Quest(
                "Goblin Slayer",
                "Defeat the goblin threat in the forest",
                List.of(new KillCountObjective("Goblin", 5)),
                new GoldReward(100)
            );

            assertEquals("Goblin Slayer", quest.getName());
            assertEquals("Defeat the goblin threat in the forest", quest.getDescription());
        }

        @Test
        @DisplayName("New quest should not be complete")
        void newQuestNotComplete() {
            Quest quest = new Quest(
                "Test Quest",
                "Description",
                List.of(new KillCountObjective("Goblin", 3)),
                new GoldReward(50)
            );

            assertFalse(quest.isComplete());
        }

        @Test
        @DisplayName("Quest with fulfilled objective should be complete")
        void questCompleteWhenObjectiveFulfilled() {
            KillCountObjective objective = new KillCountObjective("Goblin", 2);
            objective.recordProgress(1);
            objective.recordProgress(1);

            Quest quest = new Quest(
                "Test Quest",
                "Description",
                List.of(objective),
                new GoldReward(50)
            );

            assertTrue(quest.isComplete());
        }

        @Test
        @DisplayName("Quest with multiple objectives requires all to be complete")
        void questRequiresAllObjectives() {
            KillCountObjective kill = new KillCountObjective("Goblin", 2);
            CollectGoldObjective gold = new CollectGoldObjective(50);

            kill.recordProgress(2); // Fulfilled
            gold.recordProgress(25); // Not fulfilled

            Quest quest = new Quest(
                "Test Quest",
                "Description",
                List.of(kill, gold),
                new GoldReward(100)
            );

            assertFalse(quest.isComplete());

            gold.recordProgress(25); // Now fulfilled
            assertTrue(quest.isComplete());
        }

        @Test
        @DisplayName("Completing quest grants reward")
        void completingQuestGrantsReward() {
            KillCountObjective objective = new KillCountObjective("Goblin", 1);
            objective.recordProgress(1);

            Quest quest = new Quest(
                "Test Quest",
                "Description",
                List.of(objective),
                new GoldReward(100)
            );

            int initialGold = player.getGold();
            boolean completed = quest.complete(player);

            assertTrue(completed);
            assertEquals(initialGold + 100, player.getGold());
        }

        @Test
        @DisplayName("Cannot complete incomplete quest")
        void cannotCompleteIncompleteQuest() {
            Quest quest = new Quest(
                "Test Quest",
                "Description",
                List.of(new KillCountObjective("Goblin", 5)),
                new GoldReward(100)
            );

            int initialGold = player.getGold();
            boolean completed = quest.complete(player);

            assertFalse(completed);
            assertEquals(initialGold, player.getGold());
        }
    }
}

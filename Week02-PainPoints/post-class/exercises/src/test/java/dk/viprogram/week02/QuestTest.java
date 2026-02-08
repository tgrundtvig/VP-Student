package dk.viprogram.week02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Quest system.
 *
 * TEACHING NOTE: Notice how the tests reveal the pain points:
 * - Different objective types need different test scenarios
 * - Progress tracking is type-dependent
 * - Reward granting depends on Player internals
 */
class QuestTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestHero", 100, 10);
    }

    @Test
    @DisplayName("Kill quest tracks monster kills")
    void killQuestTracksKills() {
        Quest quest = Quest.killQuest(
            "Goblin Slayer",
            "Kill 5 goblins",
            "Goblin",
            5,
            100,
            50
        );

        quest.updateProgress("MONSTER_KILLED", "Goblin", 1);
        assertEquals(1, quest.getCurrentProgress());

        quest.updateProgress("MONSTER_KILLED", "Goblin", 2);
        assertEquals(3, quest.getCurrentProgress());
    }

    @Test
    @DisplayName("Kill quest ignores wrong monster type")
    void killQuestIgnoresWrongMonster() {
        Quest quest = Quest.killQuest(
            "Goblin Slayer",
            "Kill 5 goblins",
            "Goblin",
            5,
            100,
            50
        );

        quest.updateProgress("MONSTER_KILLED", "Orc", 3);
        assertEquals(0, quest.getCurrentProgress());
    }

    @Test
    @DisplayName("Quest completes when objective is met")
    void questCompletesWhenObjectiveMet() {
        Quest quest = Quest.killQuest(
            "Goblin Slayer",
            "Kill 5 goblins",
            "Goblin",
            5,
            100,
            50
        );

        quest.updateProgress("MONSTER_KILLED", "Goblin", 5);

        assertTrue(quest.checkCompletion());
        assertTrue(quest.isCompleted());
    }

    @Test
    @DisplayName("Quest grants rewards on completion")
    void questGrantsRewards() {
        Quest quest = Quest.killQuest(
            "Goblin Slayer",
            "Kill 5 goblins",
            "Goblin",
            5,
            100,
            50
        );
        quest.setPlayer(player);

        int initialGold = player.getGold();

        quest.updateProgress("MONSTER_KILLED", "Goblin", 5);
        quest.checkCompletion();
        quest.grantReward(player);

        assertTrue(player.getGold() > initialGold);
    }

    @Test
    @DisplayName("Collect gold quest tracks gold collected")
    void collectGoldQuestTracksGold() {
        Quest quest = Quest.collectGoldQuest(
            "Gold Collector",
            "Collect 100 gold",
            100,
            50,
            25
        );

        quest.updateProgress("GOLD_COLLECTED", "", 50);
        assertEquals(50, quest.getCurrentProgress());

        quest.updateProgress("GOLD_COLLECTED", "", 50);
        assertTrue(quest.checkCompletion());
    }

    @Test
    @DisplayName("Progress description varies by objective type")
    void progressDescriptionVariesByType() {
        Quest killQuest = Quest.killQuest("Kill Quest", "desc", "Goblin", 5, 0, 0);
        Quest goldQuest = Quest.collectGoldQuest("Gold Quest", "desc", 100, 0, 0);

        killQuest.updateProgress("MONSTER_KILLED", "Goblin", 2);
        goldQuest.updateProgress("GOLD_COLLECTED", "", 50);

        assertTrue(killQuest.getProgressDescription().contains("Kill"));
        assertTrue(goldQuest.getProgressDescription().contains("gold"));
    }

    // PAIN POINT: Can't easily test without real Player affecting rewards
    @Test
    @DisplayName("Quest rewards affected by difficulty")
    void questRewardsAffectedByDifficulty() {
        Quest quest = Quest.killQuest("Test", "desc", "Goblin", 1, 100, 50);
        quest.setPlayer(player);

        // Easy mode doubles gold
        player.setDifficulty("EASY");
        quest.updateProgress("MONSTER_KILLED", "Goblin", 1);
        quest.checkCompletion();
        quest.grantReward(player);

        // Gold should be doubled (200) minus guild share (0 since no guild)
        assertEquals(200, player.getGold());
    }
}

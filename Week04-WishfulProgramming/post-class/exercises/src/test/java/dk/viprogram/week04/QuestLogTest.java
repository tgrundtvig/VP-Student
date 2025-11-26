package dk.viprogram.week04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the QuestLog class.
 */
class QuestLogTest {

    private Player player;
    private QuestLog questLog;

    @BeforeEach
    void setUp() {
        player = new Player("TestHero", 100, 10);
        questLog = new QuestLog();
    }

    @Nested
    @DisplayName("Exercise 2: QuestLog class")
    class QuestLogClassTests {

        @Test
        @DisplayName("New quest log should be empty")
        void newQuestLogEmpty() {
            assertTrue(questLog.getActiveQuests().isEmpty());
            assertTrue(questLog.getCompletedQuests().isEmpty());
        }

        @Test
        @DisplayName("Adding quest should add to active list")
        void addingQuestToActive() {
            Quest quest = createSimpleQuest("Test Quest", 5);

            questLog.addQuest(quest);

            assertEquals(1, questLog.getActiveQuests().size());
            assertEquals("Test Quest", questLog.getActiveQuests().get(0).getName());
        }

        @Test
        @DisplayName("Completed quests should move from active to completed")
        void completedQuestsMove() {
            KillCountObjective objective = new KillCountObjective("Goblin", 1);
            objective.recordProgress(1); // Already fulfilled

            Quest quest = new Quest(
                "Easy Quest",
                "Description",
                List.of(objective),
                new GoldReward(50)
            );

            questLog.addQuest(quest);
            assertEquals(1, questLog.getActiveQuests().size());
            assertEquals(0, questLog.getCompletedQuests().size());

            List<Quest> justCompleted = questLog.checkAndCompleteQuests(player);

            assertEquals(1, justCompleted.size());
            assertEquals(0, questLog.getActiveQuests().size());
            assertEquals(1, questLog.getCompletedQuests().size());
        }

        @Test
        @DisplayName("checkAndCompleteQuests grants rewards")
        void checkAndCompleteGrantsRewards() {
            KillCountObjective objective = new KillCountObjective("Goblin", 1);
            objective.recordProgress(1);

            Quest quest = new Quest(
                "Reward Quest",
                "Description",
                List.of(objective),
                new GoldReward(75)
            );

            questLog.addQuest(quest);
            int initialGold = player.getGold();

            questLog.checkAndCompleteQuests(player);

            assertEquals(initialGold + 75, player.getGold());
        }

        @Test
        @DisplayName("Multiple quests can complete at once")
        void multipleQuestsCompleteAtOnce() {
            // Two quests, both already fulfilled
            KillCountObjective obj1 = new KillCountObjective("Goblin", 1);
            obj1.recordProgress(1);
            Quest quest1 = new Quest("Quest 1", "Desc", List.of(obj1), new GoldReward(50));

            KillCountObjective obj2 = new KillCountObjective("Orc", 1);
            obj2.recordProgress(1);
            Quest quest2 = new Quest("Quest 2", "Desc", List.of(obj2), new GoldReward(75));

            questLog.addQuest(quest1);
            questLog.addQuest(quest2);

            List<Quest> completed = questLog.checkAndCompleteQuests(player);

            assertEquals(2, completed.size());
            assertEquals(0, questLog.getActiveQuests().size());
            assertEquals(2, questLog.getCompletedQuests().size());
        }

        @Test
        @DisplayName("getSummary returns correct counts")
        void getSummaryCorrectCounts() {
            questLog.addQuest(createSimpleQuest("Active 1", 10));
            questLog.addQuest(createSimpleQuest("Active 2", 10));

            KillCountObjective done = new KillCountObjective("Done", 1);
            done.recordProgress(1);
            Quest completedQuest = new Quest("Completed", "Desc", List.of(done), new GoldReward(10));
            questLog.addQuest(completedQuest);
            questLog.checkAndCompleteQuests(player);

            String summary = questLog.getSummary();
            assertTrue(summary.contains("2"));
            assertTrue(summary.contains("1"));
        }
    }

    private Quest createSimpleQuest(String name, int killCount) {
        return new Quest(
            name,
            "Description",
            List.of(new KillCountObjective("Monster", killCount)),
            new GoldReward(50)
        );
    }
}

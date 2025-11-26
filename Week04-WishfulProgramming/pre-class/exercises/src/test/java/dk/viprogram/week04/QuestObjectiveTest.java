package dk.viprogram.week04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the QuestObjective implementations.
 *
 * These tests verify that your objective classes correctly track
 * progress and report fulfillment status.
 */
class QuestObjectiveTest {

    @Nested
    @DisplayName("Exercise 3a: KillCountObjective")
    class KillCountObjectiveTests {

        @Test
        @DisplayName("New objective should not be fulfilled")
        void newObjectiveNotFulfilled() {
            QuestObjective objective = new KillCountObjective("Goblin", 5);
            assertFalse(objective.isFulfilled());
        }

        @Test
        @DisplayName("Description should show target and progress")
        void descriptionShowsProgress() {
            QuestObjective objective = new KillCountObjective("Goblin", 5);
            String description = objective.getDescription();

            assertTrue(description.contains("Goblin"));
            assertTrue(description.contains("5"));
            assertTrue(description.contains("0") || description.contains("(0/5)"));
        }

        @Test
        @DisplayName("Recording progress should update count")
        void recordingProgressUpdatesCount() {
            QuestObjective objective = new KillCountObjective("Goblin", 5);

            objective.recordProgress(1);
            objective.recordProgress(1);

            String description = objective.getDescription();
            assertTrue(description.contains("2") || description.contains("(2/5)"));
        }

        @Test
        @DisplayName("Objective fulfilled when count reached")
        void fulfilledWhenCountReached() {
            QuestObjective objective = new KillCountObjective("Goblin", 3);

            objective.recordProgress(1);
            objective.recordProgress(1);
            assertFalse(objective.isFulfilled());

            objective.recordProgress(1);
            assertTrue(objective.isFulfilled());
        }

        @Test
        @DisplayName("Can exceed required count")
        void canExceedRequiredCount() {
            QuestObjective objective = new KillCountObjective("Goblin", 2);

            objective.recordProgress(1);
            objective.recordProgress(1);
            objective.recordProgress(1); // One extra

            assertTrue(objective.isFulfilled());
        }
    }

    @Nested
    @DisplayName("Exercise 3b: CollectGoldObjective")
    class CollectGoldObjectiveTests {

        @Test
        @DisplayName("New objective should not be fulfilled")
        void newObjectiveNotFulfilled() {
            QuestObjective objective = new CollectGoldObjective(100);
            assertFalse(objective.isFulfilled());
        }

        @Test
        @DisplayName("Description should show gold progress")
        void descriptionShowsProgress() {
            QuestObjective objective = new CollectGoldObjective(100);
            String description = objective.getDescription();

            assertTrue(description.toLowerCase().contains("gold"));
            assertTrue(description.contains("100"));
        }

        @Test
        @DisplayName("Recording progress should update amount")
        void recordingProgressUpdatesAmount() {
            QuestObjective objective = new CollectGoldObjective(100);

            objective.recordProgress(45);

            String description = objective.getDescription();
            assertTrue(description.contains("45"));
        }

        @Test
        @DisplayName("Objective fulfilled when gold collected")
        void fulfilledWhenGoldCollected() {
            QuestObjective objective = new CollectGoldObjective(100);

            objective.recordProgress(50);
            assertFalse(objective.isFulfilled());

            objective.recordProgress(50);
            assertTrue(objective.isFulfilled());
        }

        @Test
        @DisplayName("Multiple small amounts add up")
        void multipleSmallAmountsAddUp() {
            QuestObjective objective = new CollectGoldObjective(50);

            objective.recordProgress(10);
            objective.recordProgress(15);
            objective.recordProgress(25);

            assertTrue(objective.isFulfilled());
        }
    }
}

package dk.viprogram.week04;

/**
 * Exercise 3: Design and implement the QuestEvent system.
 *
 * A QuestEvent represents something that happened in the game
 * that might affect quest progress. Events connect game actions
 * to quest objectives.
 *
 * This is a DESIGN exercise. You need to:
 * 1. Think about what events can occur (monster killed, gold collected, etc.)
 * 2. Design how events match with objectives
 * 3. Implement the event handling
 *
 * Suggested approach using records:
 * - Create different event types as records
 * - Use pattern matching (instanceof) to handle different events
 *
 * Example events you might need:
 * - MonsterKilledEvent(String monsterType)
 * - GoldCollectedEvent(int amount)
 * - ItemFoundEvent(String itemName)
 *
 * TODO: Design your event system. Here's a starting point:
 */

/**
 * Base interface for all quest events.
 * Events are things that happen in the game that might advance quests.
 */
public interface QuestEvent {
    /**
     * Get a description of this event for logging/display.
     */
    String getDescription();
}

// TODO: Create event records that implement QuestEvent
// Example:
// public record MonsterKilledEvent(String monsterType) implements QuestEvent {
//     @Override
//     public String getDescription() {
//         return "Killed a " + monsterType;
//     }
// }

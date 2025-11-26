package dk.viprogram.week04;

import java.util.List;

/**
 * Exercise 3 (continued): Implement the QuestEventHandler.
 *
 * The QuestEventHandler processes game events and updates quest objectives.
 * This is where the connection between game actions and quests happens.
 *
 * Design considerations:
 * - How do you match events to the right objectives?
 * - Different objectives care about different events
 * - KillCountObjective cares about MonsterKilledEvent
 * - CollectGoldObjective cares about GoldCollectedEvent
 *
 * TODO:
 * 1. Design how events match with objectives
 * 2. Implement handleEvent() to update relevant objectives
 *
 * Hint: You might need to extend your objective classes to support
 * checking if they care about a specific event type.
 */
public class QuestEventHandler {

    private final QuestLog questLog;

    public QuestEventHandler(QuestLog questLog) {
        this.questLog = questLog;
    }

    /**
     * Handle a quest event by updating all relevant objectives.
     *
     * @param event the event that occurred
     */
    public void handleEvent(QuestEvent event) {
        // TODO: For each active quest, check each objective
        // If the objective cares about this event type, update it
        //
        // Hint: You might use instanceof pattern matching:
        // if (event instanceof MonsterKilledEvent mke) {
        //     // handle monster killed
        // }
        throw new UnsupportedOperationException("Implement this method");
    }
}

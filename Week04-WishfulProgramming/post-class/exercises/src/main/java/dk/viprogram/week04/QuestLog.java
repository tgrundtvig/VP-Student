package dk.viprogram.week04;

import java.util.List;

/**
 * Exercise 2: Implement the QuestLog class.
 *
 * A QuestLog tracks a player's active and completed quests.
 *
 * This class demonstrates collection management with interfaces.
 * The QuestLog doesn't care about specific quest types - it works
 * with any Quest through the common interface.
 *
 * TODO:
 * 1. Add fields for activeQuests and completedQuests (both List<Quest>)
 * 2. Create a constructor that initializes empty lists
 * 3. Implement addQuest(Quest) - adds to active quests
 * 4. Implement getActiveQuests() - returns list of active quests
 * 5. Implement getCompletedQuests() - returns list of completed quests
 * 6. Implement checkAndCompleteQuests(Player) - see below
 */
public class QuestLog {

    // TODO: Add fields for activeQuests and completedQuests

    // TODO: Add constructor

    public void addQuest(Quest quest) {
        // TODO: Add quest to active quests
        throw new UnsupportedOperationException("Implement this method");
    }

    public List<Quest> getActiveQuests() {
        // TODO: Return active quests
        throw new UnsupportedOperationException("Implement this method");
    }

    public List<Quest> getCompletedQuests() {
        // TODO: Return completed quests
        throw new UnsupportedOperationException("Implement this method");
    }

    /**
     * Check all active quests and complete any that are ready.
     *
     * For each active quest that isComplete():
     * 1. Call quest.complete(player) to grant the reward
     * 2. Move the quest from activeQuests to completedQuests
     *
     * @param player the player to grant rewards to
     * @return list of quests that were just completed
     */
    public List<Quest> checkAndCompleteQuests(Player player) {
        // TODO: Implement
        // Hint: Be careful when modifying a list while iterating!
        // Consider collecting completed quests first, then moving them.
        throw new UnsupportedOperationException("Implement this method");
    }

    /**
     * Get a summary of the quest log for display.
     *
     * @return a formatted string showing active and completed quest counts
     */
    public String getSummary() {
        // TODO: Return something like "Quest Log: 3 active, 2 completed"
        throw new UnsupportedOperationException("Implement this method");
    }
}

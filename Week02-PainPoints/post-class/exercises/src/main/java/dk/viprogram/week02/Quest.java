package dk.viprogram.week02;

import java.util.ArrayList;
import java.util.List;

/**
 * Quest system implementation - the "painful" way.
 *
 * TEACHING NOTE: This demonstrates how quest systems become
 * unwieldy without proper interface design.
 *
 * Notice:
 * - Quest must know about Player internals
 * - Different objective types require if-else chains
 * - Hard to add new objective types
 * - Testing requires full Player setup
 */
public class Quest {
    private String name;
    private String description;
    private Player player;  // Circular!

    // Different types of objectives - leads to if-else explosion
    private String objectiveType;  // "KILL", "COLLECT", "REACH", "TALK"
    private String objectiveTarget;  // Monster type, item name, location, NPC name
    private int objectiveAmount;
    private int currentProgress;

    // Rewards
    private int goldReward;
    private int xpReward;
    private List<String> itemRewards;

    private boolean completed;

    public Quest(String name, String description, String objectiveType,
                 String objectiveTarget, int objectiveAmount,
                 int goldReward, int xpReward) {
        this.name = name;
        this.description = description;
        this.objectiveType = objectiveType;
        this.objectiveTarget = objectiveTarget;
        this.objectiveAmount = objectiveAmount;
        this.currentProgress = 0;
        this.goldReward = goldReward;
        this.xpReward = xpReward;
        this.itemRewards = new ArrayList<>();
        this.completed = false;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    // PAIN POINT: Different logic for different objective types
    public void updateProgress(String eventType, String target, int amount) {
        if (completed) return;

        // TODO: Update progress based on the objective type.
        // This requires a large if-else chain:
        //
        // If objectiveType is "KILL":
        //   Only count progress when eventType is "MONSTER_KILLED"
        //   AND target matches objectiveTarget. Add amount to currentProgress.
        //
        // If objectiveType is "COLLECT":
        //   Only count when eventType is "ITEM_COLLECTED"
        //   AND target matches objectiveTarget. Add amount to currentProgress.
        //
        // If objectiveType is "COLLECT_GOLD":
        //   Only count when eventType is "GOLD_COLLECTED".
        //   Add amount to currentProgress (no target matching needed).
        //
        // If objectiveType is "REACH":
        //   Only count when eventType is "LOCATION_REACHED"
        //   AND target matches objectiveTarget. Set currentProgress = objectiveAmount (instant).
        //
        // If objectiveType is "TALK":
        //   Only count when eventType is "NPC_TALKED"
        //   AND target matches objectiveTarget. Set currentProgress = objectiveAmount (instant).
        //
        // Notice: Adding a new objective type means modifying this method!
    }

    public boolean checkCompletion() {
        // TODO: If not already completed and currentProgress >= objectiveAmount,
        // mark as completed and return true. Otherwise return false.
        return false;
    }

    // PAIN POINT: Reward granting depends on player internals
    public void grantReward(Player player) {
        // TODO: If not completed, do nothing (return early).
        // Otherwise, give the player gold and experience rewards.
        // Use player.addGold(goldReward) and player.addExperience(xpReward).
        // Note: addGold already handles difficulty scaling!
    }

    // PAIN POINT: How do we describe progress for different types?
    public String getProgressDescription() {
        // TODO: Return a progress description string based on objectiveType.
        // For "KILL":        "Kill <target>: <current>/<amount>"
        // For "COLLECT":     "Collect <target>: <current>/<amount>"
        // For "COLLECT_GOLD": "Collect gold: <current>/<amount>"
        // For "REACH":       "Reach <target>: Done" or "Reach <target>: Not done"
        // For "TALK":        "Talk to <target>: Done" or "Talk to <target>: Not done"
        // Default:           "Unknown objective"
        return "Unknown objective";
    }

    // Factory methods for common quests
    public static Quest killQuest(String name, String description,
                                   String monsterType, int count,
                                   int gold, int xp) {
        return new Quest(name, description, "KILL", monsterType, count, gold, xp);
    }

    public static Quest collectGoldQuest(String name, String description,
                                         int amount, int gold, int xp) {
        return new Quest(name, description, "COLLECT_GOLD", "", amount, gold, xp);
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getObjectiveType() { return objectiveType; }
    public int getCurrentProgress() { return currentProgress; }
    public int getObjectiveAmount() { return objectiveAmount; }
    public boolean isCompleted() { return completed; }
    public int getGoldReward() { return goldReward; }
    public int getXpReward() { return xpReward; }
}

package dk.viprogram.week02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Player class with guild support added in a "painful" way.
 *
 * TEACHING NOTE: This code demonstrates the problems that arise
 * when features are added without proper interface design.
 *
 * Notice:
 * - Circular dependency with Guild class
 * - Feature envy (reaching into Guild to check things)
 * - God class tendencies (Player does too much)
 * - Hard to test in isolation
 */
public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;
    private int gold;
    private int experience;
    private int level;

    // Inventory - simple map approach
    private Map<String, Integer> inventory;

    // Guild reference - creates circular dependency!
    private Guild guild;

    // Quest tracking - gets messy fast
    private List<Quest> activeQuests;
    private List<Quest> completedQuests;

    // Difficulty modifier - has to be stored somewhere
    private String difficulty;  // "EASY", "NORMAL", "HARD", "NIGHTMARE"

    public Player(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
        this.gold = 0;
        this.experience = 0;
        this.level = 1;
        this.inventory = new HashMap<>();
        this.guild = null;
        this.activeQuests = new ArrayList<>();
        this.completedQuests = new ArrayList<>();
        this.difficulty = "NORMAL";
    }

    // PAIN POINT: Attack power now depends on multiple things
    public int getEffectiveAttackPower() {
        int base = attackPower;

        // TODO: Add guild bonus if in a guild.
        // If guild is not null, add guild.getAttackBonus() to base.
        // Notice the feature envy - we're reaching into Guild!

        // TODO: Modify by difficulty.
        // If "EASY", multiply base by 1.25 (cast to int).
        // If "NIGHTMARE", multiply base by 0.75 (cast to int).

        return base;
    }

    // PAIN POINT: Taking damage involves checking multiple systems
    public void takeDamage(int damage) {
        int actualDamage = damage;

        // TODO: Apply guild defense bonus.
        // If guild is not null, subtract guild.getDefenseBonus() from actualDamage.
        // Notice the feature envy!

        // TODO: Apply difficulty modifier.
        // If "EASY", multiply actualDamage by 0.75 (cast to int).
        // If "HARD", multiply actualDamage by 1.25 (cast to int).
        // If "NIGHTMARE", multiply actualDamage by 1.5 (cast to int).

        // Ensure minimum 1 damage and health doesn't go below 0
        actualDamage = Math.max(1, actualDamage);
        health = Math.max(0, health - actualDamage);
    }

    // PAIN POINT: Guild management creates bidirectional dependency
    public void joinGuild(Guild guild) {
        // TODO: If already in a guild, leave the old guild first
        // (tell the old guild to remove this player).
        // Then set the new guild reference and add this player to the new guild.
        // Notice the circular dependency!
    }

    public void leaveGuild() {
        // TODO: If in a guild, tell the guild to remove this player,
        // then set guild to null.
    }

    // PAIN POINT: Quest handling mixed into Player class
    public void acceptQuest(Quest quest) {
        // TODO: Add the quest to activeQuests and tell the quest about this player.
        // Notice the circular dependency!
    }

    public void checkQuestProgress() {
        // TODO: Iterate through active quests.
        // For each quest, check if it's completed (quest.checkCompletion()).
        // If completed, grant the reward and move it from activeQuests to completedQuests.
        // Hint: Collect completed quests in a separate list first,
        // then remove them from activeQuests and add to completedQuests.
    }

    // PAIN POINT: Gold earning affected by difficulty
    public void addGold(int amount) {
        int adjusted = amount;

        // TODO: Apply difficulty modifier.
        // If "EASY", multiply amount by 2.0 (cast to int).
        // If "NIGHTMARE", multiply amount by 0.5 (cast to int).

        // TODO: Share with guild bank if in a guild.
        // Calculate 10% of adjusted amount as guildShare (integer division).
        // Add guildShare to guild bank, subtract guildShare from adjusted.

        gold += adjusted;
    }

    // Basic getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getGold() { return gold; }
    public int getLevel() { return level; }
    public Guild getGuild() { return guild; }
    public String getDifficulty() { return difficulty; }
    public List<Quest> getActiveQuests() { return activeQuests; }
    public List<Quest> getCompletedQuests() { return completedQuests; }

    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public void heal(int amount) { health = Math.min(maxHealth, health + amount); }
    public boolean isAlive() { return health > 0; }

    public void addExperience(int amount) {
        // TODO: Add experience points. When experience reaches level * 100,
        // subtract that threshold, increment level, increase maxHealth by 10,
        // restore health to maxHealth, and increase attackPower by 2.
        // Use a while loop to handle multiple level-ups at once.
    }
}

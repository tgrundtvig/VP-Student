package dk.viprogram.week01;

/**
 * Represents a monster that the player can fight.
 * Different monster types have different stats and rewards.
 *
 * This shows how a naive approach leads to duplication - notice how
 * each factory method repeats similar patterns with different numbers.
 *
 * YOUR TASKS:
 * 1. Implement the constructor to store all monster properties
 * 2. Implement getter methods
 * 3. Implement combat methods (takeDamage, isAlive)
 * 4. Implement factory methods for different monster types
 * 5. Implement scaled monster factories that adjust stats by player level
 */
public class Monster {
    private String name;
    private String type;
    private int health;
    private int maxHealth;
    private int attackPower;
    private int goldDrop;
    private int experienceDrop;

    /**
     * Creates a new Monster with the given properties.
     * The monster's maxHealth is set equal to health.
     *
     * @param name           the monster's display name
     * @param type           the monster's type (e.g., "goblin", "orc", "dragon")
     * @param health         the monster's starting health (also becomes maxHealth)
     * @param attackPower    the monster's attack damage per hit
     * @param goldDrop       the gold rewarded to the player on defeat
     * @param experienceDrop the experience rewarded to the player on defeat
     */
    public Monster(String name, String type, int health, int attackPower, int goldDrop, int experienceDrop) {
        // TODO: Store all six parameters in the corresponding fields.
        // Remember: maxHealth should be set equal to health.
    }

    // =========================================================================
    // Factory Methods - Standard Monsters
    // =========================================================================
    // Each factory method creates a pre-configured monster with set stats.

    /**
     * Creates a Goblin - a weak but common monster.
     * Name: "Goblin", Type: "goblin", Health: 30, Attack: 5, Gold: 10, XP: 15
     *
     * @return a new Goblin monster
     */
    public static Monster goblin() {
        // TODO: Return a new Monster with the stats described in the JavaDoc
        return null;
    }

    /**
     * Creates an Orc - stronger than a goblin.
     * Name: "Orc", Type: "orc", Health: 60, Attack: 10, Gold: 25, XP: 35
     *
     * @return a new Orc monster
     */
    public static Monster orc() {
        // TODO: Return a new Monster with the stats described in the JavaDoc
        return null;
    }

    /**
     * Creates a Dragon - the strongest standard monster.
     * Name: "Dragon", Type: "dragon", Health: 200, Attack: 25, Gold: 200, XP: 500
     *
     * @return a new Dragon monster
     */
    public static Monster dragon() {
        // TODO: Return a new Monster with the stats described in the JavaDoc
        return null;
    }

    // =========================================================================
    // Factory Methods - Scaled Monsters
    // =========================================================================
    // Scaled monsters adjust their stats based on the player's level.
    // This keeps encounters challenging as the player grows stronger.

    /**
     * Creates a Goblin scaled to the player's level.
     * Base stats scale linearly with playerLevel:
     * - Health: 30 + (playerLevel * 10)
     * - Attack: 5 + (playerLevel * 2)
     * - Gold: 10 + (playerLevel * 5)
     * - XP: 15 + (playerLevel * 5)
     * Name format: "Goblin Lv.{playerLevel}", Type: "goblin"
     *
     * @param playerLevel the player's current level
     * @return a new level-scaled Goblin
     */
    public static Monster scaledGoblin(int playerLevel) {
        // TODO: Calculate scaled stats using the formulas in the JavaDoc
        // and return a new Monster with those stats
        return null;
    }

    /**
     * Creates an Orc scaled to the player's level.
     * Base stats scale linearly with playerLevel:
     * - Health: 60 + (playerLevel * 15)
     * - Attack: 10 + (playerLevel * 3)
     * - Gold: 25 + (playerLevel * 10)
     * - XP: 35 + (playerLevel * 10)
     * Name format: "Orc Lv.{playerLevel}", Type: "orc"
     *
     * @param playerLevel the player's current level
     * @return a new level-scaled Orc
     */
    public static Monster scaledOrc(int playerLevel) {
        // TODO: Calculate scaled stats using the formulas in the JavaDoc
        // and return a new Monster with those stats
        return null;
    }

    // =========================================================================
    // Combat Methods
    // =========================================================================

    /**
     * Reduces the monster's health by the given damage amount.
     * Health cannot go below 0.
     *
     * @param damage the amount of damage to take
     */
    public void takeDamage(int damage) {
        // TODO: Reduce health by damage, but health cannot go below 0
        // Hint: health = Math.max(0, health - damage)
    }

    /**
     * Checks if the monster is still alive.
     *
     * @return true if health is greater than 0
     */
    public boolean isAlive() {
        // TODO: Return true if health > 0
        return false;
    }

    // =========================================================================
    // Getters
    // =========================================================================

    /** @return the monster's name */
    public String getName() {
        // TODO: Return the monster's name
        return null;
    }

    /** @return the monster's type (e.g., "goblin", "orc") */
    public String getType() {
        // TODO: Return the monster's type
        return null;
    }

    /** @return the monster's current health */
    public int getHealth() {
        // TODO: Return current health
        return 0;
    }

    /** @return the monster's maximum health */
    public int getMaxHealth() {
        // TODO: Return max health
        return 0;
    }

    /** @return the monster's attack power */
    public int getAttackPower() {
        // TODO: Return attack power
        return 0;
    }

    /** @return the gold dropped when this monster is defeated */
    public int getGoldDrop() {
        // TODO: Return gold drop amount
        return 0;
    }

    /** @return the experience dropped when this monster is defeated */
    public int getExperienceDrop() {
        // TODO: Return experience drop amount
        return 0;
    }

    @Override
    public String toString() {
        return name + " (" + type + ") - HP: " + health + "/" + maxHealth +
               ", ATK: " + attackPower;
    }
}

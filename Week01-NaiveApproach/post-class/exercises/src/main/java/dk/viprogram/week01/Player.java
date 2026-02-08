package dk.viprogram.week01;

import java.util.ArrayList;
import java.util.List;

/**
 * Extended Player class with inventory, equipment, gold, and leveling systems.
 *
 * This is the most complex class in the post-class exercises. It demonstrates
 * a naive approach to inventory management that works but has limitations
 * you will explore in later weeks.
 *
 * YOUR TASKS:
 * 1. Implement the constructor to initialize all fields
 * 2. Implement inventory management (add, remove, find, has)
 * 3. Implement item usage (potions heal, weapons/armor get equipped)
 * 4. Implement equipment system (equip weapon/armor, calculate bonuses)
 * 5. Implement combat methods (takeDamage with armor, heal with cap)
 * 6. Implement gold management (add, spend with validation)
 * 7. Implement experience and leveling system
 */
public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int baseAttackPower;
    private int gold;
    private int experience;
    private int level;

    // Inventory system
    private List<Item> inventory;
    private Item equippedWeapon;
    private Item equippedArmor;

    /**
     * Creates a new Player with the given starting stats.
     * The player starts at level 1, with 0 gold, 0 experience,
     * an empty inventory, and no equipped items.
     *
     * @param name        the player's name
     * @param health      the player's starting (and max) health
     * @param attackPower the player's base attack power
     */
    public Player(String name, int health, int attackPower) {
        // TODO: Initialize all fields.
        // - name, health, maxHealth (same as health), baseAttackPower
        // - gold = 0, experience = 0, level = 1
        // - inventory = new ArrayList<>()
        // - equippedWeapon = null, equippedArmor = null
        this.name = null;
        this.health = 0;
        this.maxHealth = 0;
        this.baseAttackPower = 0;
        this.gold = 0;
        this.experience = 0;
        this.level = 0;
        this.inventory = new ArrayList<>();
        this.equippedWeapon = null;
        this.equippedArmor = null;
    }

    // =========================================================================
    // Inventory Management
    // =========================================================================

    /**
     * Adds an item to the player's inventory.
     *
     * @param item the item to add
     */
    public void addItem(Item item) {
        // TODO: Add the item to the inventory list
    }

    /**
     * Removes an item from the player's inventory.
     *
     * @param item the item to remove
     * @return true if the item was found and removed, false otherwise
     */
    public boolean removeItem(Item item) {
        // TODO: Remove the item from the inventory list and return the result
        return false;
    }

    /**
     * Returns a copy of the player's inventory.
     * Returns a new list so the caller cannot modify the player's actual inventory.
     *
     * @return a new list containing all items in the inventory
     */
    public List<Item> getInventory() {
        // TODO: Return a new ArrayList containing all items in the inventory
        // Hint: new ArrayList<>(inventory)
        return new ArrayList<>();
    }

    /**
     * Checks if the player has an item with the given name.
     *
     * @param itemName the name to search for
     * @return true if the player has an item with that name
     */
    public boolean hasItem(String itemName) {
        // TODO: Search through the inventory for an item whose getName() equals itemName
        // Hint: You can use a for-loop or stream().anyMatch(...)
        return false;
    }

    /**
     * Finds and returns the first item with the given name, or null if not found.
     *
     * @param itemName the name to search for
     * @return the item if found, null otherwise
     */
    public Item findItem(String itemName) {
        // TODO: Search through the inventory for an item whose getName() equals itemName
        // Return the first match, or null if no match is found
        // Hint: You can use a for-loop or stream().filter(...).findFirst().orElse(null)
        return null;
    }

    // =========================================================================
    // Item Usage
    // =========================================================================

    /**
     * Uses an item from the player's inventory.
     * - If the item is not in the inventory, return false.
     * - If the item is a POTION: heal the player by the potion's effect amount,
     *   remove the potion from inventory, and return true.
     * - If the item is a WEAPON: equip it as the player's weapon and return true.
     * - If the item is a ARMOR: equip it as the player's armor and return true.
     *
     * @param item the item to use
     * @return true if the item was successfully used, false otherwise
     */
    public boolean useItem(Item item) {
        // TODO: Implement item usage logic as described in the JavaDoc above
        // Step 1: Check if the item is in the inventory. If not, return false.
        // Step 2: Check the item type and handle each case:
        //   - POTION: call heal() with the item's effect, remove from inventory, return true
        //   - WEAPON: call equipWeapon(), return true
        //   - ARMOR: call equipArmor(), return true
        return false;
    }

    /**
     * Equips a weapon. The weapon must be of type WEAPON.
     * The weapon's effect value is added to the player's base attack power
     * (calculated in getAttackPower()).
     *
     * @param weapon the weapon to equip
     * @throws IllegalArgumentException if the item is not a WEAPON
     */
    public void equipWeapon(Item weapon) {
        // TODO: Check if the item type is WEAPON. If not, throw IllegalArgumentException.
        // Then set equippedWeapon to the given weapon.
    }

    /**
     * Equips armor. The armor must be of type ARMOR.
     * The armor's effect value becomes the player's defense
     * (calculated in getDefense()).
     *
     * @param armor the armor to equip
     * @throws IllegalArgumentException if the item is not ARMOR
     */
    public void equipArmor(Item armor) {
        // TODO: Check if the item type is ARMOR. If not, throw IllegalArgumentException.
        // Then set equippedArmor to the given armor.
    }

    /**
     * @return the currently equipped weapon, or null if none
     */
    public Item getEquippedWeapon() {
        // TODO: Return the equipped weapon
        return null;
    }

    /**
     * @return the currently equipped armor, or null if none
     */
    public Item getEquippedArmor() {
        // TODO: Return the equipped armor
        return null;
    }

    // =========================================================================
    // Combat Stats
    // =========================================================================

    /**
     * Returns the player's total attack power.
     * This is the base attack power plus any bonus from an equipped weapon.
     *
     * @return base attack power + weapon effect (if a weapon is equipped)
     */
    public int getAttackPower() {
        // TODO: Calculate total attack power = baseAttackPower + weapon bonus
        // If equippedWeapon is not null, add equippedWeapon.getEffect()
        return 0;
    }

    /**
     * Returns the player's defense value from equipped armor.
     * If no armor is equipped, defense is 0.
     *
     * @return the defense value from equipped armor, or 0 if none
     */
    public int getDefense() {
        // TODO: Return the armor's effect if armor is equipped, otherwise 0
        return 0;
    }

    /**
     * Reduces the player's health by the given damage amount, minus defense.
     * The actual damage taken is: max(1, damage - defense).
     * Health cannot go below 0.
     *
     * @param damage the raw damage amount before defense is applied
     */
    public void takeDamage(int damage) {
        // TODO: Calculate actual damage = max(1, damage - getDefense())
        // Then reduce health by actual damage, but health cannot go below 0
        // Hint: health = Math.max(0, health - actualDamage)
    }

    /**
     * Heals the player by the given amount.
     * Health cannot exceed maxHealth.
     *
     * @param amount the amount to heal
     */
    public void heal(int amount) {
        // TODO: Increase health by amount, but cap at maxHealth
        // Hint: health = Math.min(maxHealth, health + amount)
    }

    /**
     * @return true if the player's health is greater than 0
     */
    public boolean isAlive() {
        // TODO: Return true if health > 0
        return false;
    }

    // =========================================================================
    // Gold Management
    // =========================================================================

    /**
     * Adds gold to the player's total.
     *
     * @param amount the amount of gold to add
     */
    public void addGold(int amount) {
        // TODO: Add the amount to the player's gold
    }

    /**
     * Attempts to spend gold. Only succeeds if the player has enough.
     * If successful, the gold is deducted. If not, nothing changes.
     *
     * @param amount the amount of gold to spend
     * @return true if the player had enough gold and it was spent, false otherwise
     */
    public boolean spendGold(int amount) {
        // TODO: Check if gold >= amount.
        // If yes, subtract amount from gold and return true.
        // If no, return false (do not change gold).
        return false;
    }

    // =========================================================================
    // Experience and Leveling
    // =========================================================================

    /**
     * Adds experience points to the player.
     * When experience reaches the level-up threshold (level * 100),
     * the player levels up. Handle multiple level-ups if enough XP is gained.
     *
     * @param amount the amount of experience to add
     */
    public void addExperience(int amount) {
        // TODO: Add experience points.
        // Check for level up: while experience >= level * 100, subtract the threshold
        // and call levelUp().
        // Hint: Use a while loop since gaining a lot of XP could cause multiple level-ups
    }

    /**
     * Returns the player's current experience points.
     *
     * @return current experience
     */
    public int getExperience() {
        // TODO: Return experience
        return 0;
    }

    /**
     * Levels up the player:
     * - Increment level by 1
     * - Increase maxHealth by 10
     * - Restore health to maxHealth
     * - Increase baseAttackPower by 2
     */
    private void levelUp() {
        // TODO: Implement level up logic as described in the JavaDoc
    }

    // =========================================================================
    // Getters
    // =========================================================================

    /** @return the player's name */
    public String getName() {
        // TODO: Return the player's name
        return null;
    }

    /** @return the player's current health */
    public int getHealth() {
        // TODO: Return current health
        return 0;
    }

    /** @return the player's maximum health */
    public int getMaxHealth() {
        // TODO: Return max health
        return 0;
    }

    /** @return the player's current gold */
    public int getGold() {
        // TODO: Return current gold
        return 0;
    }

    /** @return the player's current level */
    public int getLevel() {
        // TODO: Return current level
        return 0;
    }

    @Override
    public String toString() {
        return name + " (Lv." + level + ") - HP: " + health + "/" + maxHealth +
               ", ATK: " + getAttackPower() + ", DEF: " + getDefense() +
               ", Gold: " + gold;
    }
}

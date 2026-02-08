package dk.viprogram.week01;

/**
 * Represents an item in the game.
 * Items can be potions, weapons, or armor.
 *
 * This is a simple implementation that uses strings and enums
 * to differentiate item types - a "naive" approach that works
 * but will show limitations as the system grows.
 *
 * YOUR TASKS:
 * 1. Implement the constructor to store all item properties
 * 2. Implement getter methods and helper methods (isConsumable, isEquipable)
 * 3. Implement factory methods that create pre-configured items
 */
public class Item {

    /**
     * The different types of items in the game.
     * - POTION: Consumable, restores health when used
     * - WEAPON: Equippable, increases the player's attack power
     * - ARMOR: Equippable, increases the player's defense (reduces damage taken)
     */
    public enum ItemType {
        POTION,
        WEAPON,
        ARMOR
    }

    private String name;
    private ItemType type;
    private int value;       // Gold value for buying/selling
    private int effect;      // Effect magnitude (healing amount, attack bonus, defense bonus)

    /**
     * Creates a new Item with the given properties.
     *
     * @param name   the display name of the item
     * @param type   the type of item (POTION, WEAPON, or ARMOR)
     * @param value  the gold value of the item (used for buying/selling)
     * @param effect the effect magnitude (healing for potions, attack bonus for weapons,
     *               defense bonus for armor)
     */
    public Item(String name, ItemType type, int value, int effect) {
        // TODO: Store all four parameters in the corresponding fields
    }

    // =========================================================================
    // Factory Methods
    // =========================================================================
    // These static methods create pre-configured items with specific stats.
    // Each factory method should return a new Item(...) with appropriate values.

    /**
     * Creates a Health Potion.
     * Name: "Health Potion", Type: POTION, Value: 10 gold, Effect: 30 healing
     *
     * @return a new Health Potion item
     */
    public static Item healthPotion() {
        // TODO: Return a new Item with the stats described in the JavaDoc
        return null;
    }

    /**
     * Creates a Basic Sword.
     * Name: "Basic Sword", Type: WEAPON, Value: 25 gold, Effect: +5 attack
     *
     * @return a new Basic Sword item
     */
    public static Item basicSword() {
        // TODO: Return a new Item with the stats described in the JavaDoc
        return null;
    }

    /**
     * Creates an Iron Sword (stronger than Basic Sword).
     * Name: "Iron Sword", Type: WEAPON, Value: 50 gold, Effect: +10 attack
     *
     * @return a new Iron Sword item
     */
    public static Item ironSword() {
        // TODO: Return a new Item with the stats described in the JavaDoc
        return null;
    }

    /**
     * Creates Leather Armor.
     * Name: "Leather Armor", Type: ARMOR, Value: 30 gold, Effect: +3 defense
     *
     * @return a new Leather Armor item
     */
    public static Item leatherArmor() {
        // TODO: Return a new Item with the stats described in the JavaDoc
        return null;
    }

    /**
     * Creates Chain Mail (stronger than Leather Armor).
     * Name: "Chain Mail", Type: ARMOR, Value: 75 gold, Effect: +7 defense
     *
     * @return a new Chain Mail item
     */
    public static Item chainMail() {
        // TODO: Return a new Item with the stats described in the JavaDoc
        return null;
    }

    // =========================================================================
    // Getters
    // =========================================================================

    /**
     * @return the name of this item
     */
    public String getName() {
        // TODO: Return the item's name
        return null;
    }

    /**
     * @return the type of this item (POTION, WEAPON, or ARMOR)
     */
    public ItemType getType() {
        // TODO: Return the item's type
        return null;
    }

    /**
     * @return the gold value of this item
     */
    public int getValue() {
        // TODO: Return the item's value
        return 0;
    }

    /**
     * @return the effect magnitude of this item
     */
    public int getEffect() {
        // TODO: Return the item's effect
        return 0;
    }

    // =========================================================================
    // Helper Methods
    // =========================================================================

    /**
     * Checks if this item is consumable (used once and removed from inventory).
     * Only POTIONs are consumable.
     *
     * @return true if this item is a POTION, false otherwise
     */
    public boolean isConsumable() {
        // TODO: Return true if the item type is POTION
        return false;
    }

    /**
     * Checks if this item can be equipped (worn or held by the player).
     * WEAPONs and ARMOR are equipable.
     *
     * @return true if this item is a WEAPON or ARMOR, false otherwise
     */
    public boolean isEquipable() {
        // TODO: Return true if the item type is WEAPON or ARMOR
        return false;
    }

    @Override
    public String toString() {
        return name + " (" + type + ", value: " + value + " gold, effect: " + effect + ")";
    }
}

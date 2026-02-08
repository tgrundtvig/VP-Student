package dk.viprogram.week01;

import java.util.ArrayList;
import java.util.List;

/**
 * Shop system for buying and selling items.
 *
 * The shop has its own inventory of items for sale. Players can buy items
 * (if they have enough gold) and sell items (at 50% of the item's value).
 *
 * YOUR TASKS:
 * 1. Implement the constructor to initialize the shop with a default inventory
 * 2. Implement buying: validate gold, transfer item and gold
 * 3. Implement selling: validate ownership, transfer item, pay 50% value
 * 4. Implement price calculation methods
 */
public class Shop {
    private List<Item> inventory;
    private String name;

    /**
     * Creates a new Shop with the given name and a default inventory.
     * The default inventory should contain:
     * - 3 Health Potions
     * - 1 Basic Sword
     * - 1 Iron Sword
     * - 1 Leather Armor
     * - 1 Chain Mail
     *
     * @param name the shop's display name
     */
    public Shop(String name) {
        // TODO: Initialize the name field
        // TODO: Initialize inventory as a new ArrayList
        // TODO: Call initializeInventory() to stock the shop
        this.name = null;
        this.inventory = new ArrayList<>();
    }

    /**
     * Stocks the shop with default items.
     * Add the following items to the inventory:
     * - 3x Health Potion (use Item.healthPotion())
     * - 1x Basic Sword (use Item.basicSword())
     * - 1x Iron Sword (use Item.ironSword())
     * - 1x Leather Armor (use Item.leatherArmor())
     * - 1x Chain Mail (use Item.chainMail())
     */
    private void initializeInventory() {
        // TODO: Add default items to the inventory using the Item factory methods
    }

    /**
     * Returns a copy of the shop's inventory.
     *
     * @return a new list containing all items currently for sale
     */
    public List<Item> getInventory() {
        // TODO: Return a new ArrayList containing all items in the inventory
        return new ArrayList<>();
    }

    /** @return the shop's name */
    public String getName() {
        // TODO: Return the shop's name
        return null;
    }

    /**
     * Adds an item to the shop's inventory.
     *
     * @param item the item to add
     */
    public void addItem(Item item) {
        // TODO: Add the item to the shop's inventory
    }

    // =========================================================================
    // Buying and Selling
    // =========================================================================

    /**
     * Attempts to sell an item from the shop to the player (player buys).
     *
     * The purchase succeeds only if:
     * 1. The item is in the shop's inventory
     * 2. The player has enough gold to pay the item's value
     *
     * On success:
     * - The player's gold is reduced by the item's value
     * - The item is removed from the shop's inventory
     * - The item is added to the player's inventory
     *
     * @param player the player buying the item
     * @param item   the item to buy
     * @return true if the purchase succeeded, false otherwise
     */
    public boolean buyItem(Player player, Item item) {
        // TODO: Implement buy logic
        // Step 1: Check if the item is in the shop's inventory. If not, return false.
        // Step 2: Try to spend the player's gold (player.spendGold(item.getValue())).
        //         If that fails (returns false), return false.
        // Step 3: Remove the item from the shop's inventory.
        // Step 4: Add the item to the player's inventory.
        // Step 5: Return true.
        return false;
    }

    /**
     * Attempts to buy an item from the player (player sells).
     * The player receives 50% of the item's value (integer division).
     *
     * The sale succeeds only if the item is in the player's inventory.
     *
     * On success:
     * - The item is removed from the player's inventory
     * - The player receives gold equal to 50% of the item's value
     * - The item is added to the shop's inventory
     *
     * @param player the player selling the item
     * @param item   the item to sell
     * @return true if the sale succeeded, false otherwise
     */
    public boolean sellItem(Player player, Item item) {
        // TODO: Implement sell logic
        // Step 1: Check if the item is in the player's inventory. If not, return false.
        //         Hint: use player.getInventory().contains(item)
        // Step 2: Calculate sell price = item.getValue() / 2
        // Step 3: Remove the item from the player's inventory
        // Step 4: Add gold to the player
        // Step 5: Add the item to the shop's inventory
        // Step 6: Return true
        return false;
    }

    // =========================================================================
    // Price Methods
    // =========================================================================

    /**
     * Returns the price to buy the given item (full value).
     *
     * @param item the item to check
     * @return the buy price (item's value)
     */
    public int getBuyPrice(Item item) {
        // TODO: Return the item's value (full price)
        return 0;
    }

    /**
     * Returns the price the shop will pay for the given item (50% of value).
     *
     * @param item the item to check
     * @return the sell price (half of item's value, integer division)
     */
    public int getSellPrice(Item item) {
        // TODO: Return the item's value divided by 2
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(name).append(" ===\n");
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            sb.append(i + 1).append(". ").append(item.getName())
              .append(" - ").append(item.getValue()).append(" gold\n");
        }
        return sb.toString();
    }
}

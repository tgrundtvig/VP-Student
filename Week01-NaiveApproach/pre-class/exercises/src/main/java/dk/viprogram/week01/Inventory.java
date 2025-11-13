package dk.viprogram.week01;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an inventory system that stores items and their quantities.
 * Practices working with HashMap collections.
 */
public class Inventory {
    // TODO: Add a private field 'items' of type Map<String, Integer>
    // This will store item names (String) and their quantities (Integer)


    // TODO: Create a constructor that initializes the items map as a new HashMap


    // TODO: Create a method addItem(String itemName, int quantity) that:
    // - If the item already exists, adds the quantity to the existing amount
    // - If the item doesn't exist, adds it with the given quantity
    // - Quantity must be positive (> 0), otherwise do nothing


    // TODO: Create a method removeItem(String itemName, int quantity) that:
    // - Reduces the item quantity by the specified amount
    // - If quantity becomes 0 or less, removes the item entirely
    // - If item doesn't exist, do nothing
    // - Returns true if successful, false if item doesn't exist or insufficient quantity


    // TODO: Create a method getItemCount(String itemName) that:
    // - Returns the quantity of the specified item
    // - Returns 0 if the item doesn't exist


    // TODO: Create a method hasItem(String itemName) that:
    // - Returns true if the item exists in inventory (quantity > 0)
    // - Returns false otherwise


    // TODO: Create a method getTotalItems() that:
    // - Returns the total number of different item types (not quantities)
    // - For example: 3 swords and 5 potions = 2 different items


}

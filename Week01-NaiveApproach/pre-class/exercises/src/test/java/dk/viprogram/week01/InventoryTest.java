package dk.viprogram.week01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Inventory class.
 * When all tests pass, you've correctly implemented Inventory!
 */
class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void shouldStartEmpty() {
        assertEquals(0, inventory.getTotalItems());
    }

    @Test
    void shouldAddNewItem() {
        inventory.addItem("Sword", 1);

        assertTrue(inventory.hasItem("Sword"));
        assertEquals(1, inventory.getItemCount("Sword"));
        assertEquals(1, inventory.getTotalItems());
    }

    @Test
    void shouldAddMultipleQuantitiesOfSameItem() {
        inventory.addItem("Potion", 3);
        inventory.addItem("Potion", 2);

        assertEquals(5, inventory.getItemCount("Potion"));
        assertEquals(1, inventory.getTotalItems());  // Still only one type
    }

    @Test
    void shouldNotAddNegativeOrZeroQuantity() {
        inventory.addItem("Sword", -1);
        inventory.addItem("Shield", 0);

        assertFalse(inventory.hasItem("Sword"));
        assertFalse(inventory.hasItem("Shield"));
        assertEquals(0, inventory.getTotalItems());
    }

    @Test
    void shouldRemoveItemCompletely() {
        inventory.addItem("Sword", 1);
        boolean removed = inventory.removeItem("Sword", 1);

        assertTrue(removed);
        assertFalse(inventory.hasItem("Sword"));
        assertEquals(0, inventory.getItemCount("Sword"));
    }

    @Test
    void shouldRemovePartialQuantity() {
        inventory.addItem("Potion", 10);
        boolean removed = inventory.removeItem("Potion", 3);

        assertTrue(removed);
        assertTrue(inventory.hasItem("Potion"));
        assertEquals(7, inventory.getItemCount("Potion"));
    }

    @Test
    void shouldReturnFalseWhenRemovingNonExistentItem() {
        boolean removed = inventory.removeItem("NonExistent", 1);

        assertFalse(removed);
    }

    @Test
    void shouldReturnFalseWhenRemovingMoreThanExists() {
        inventory.addItem("Sword", 2);
        boolean removed = inventory.removeItem("Sword", 5);

        assertFalse(removed);
        assertEquals(2, inventory.getItemCount("Sword"));  // Should remain unchanged
    }

    @Test
    void shouldReturnZeroForNonExistentItem() {
        assertEquals(0, inventory.getItemCount("NonExistent"));
    }

    @Test
    void shouldTrackMultipleItemTypes() {
        inventory.addItem("Sword", 1);
        inventory.addItem("Shield", 1);
        inventory.addItem("Potion", 5);

        assertEquals(3, inventory.getTotalItems());
        assertTrue(inventory.hasItem("Sword"));
        assertTrue(inventory.hasItem("Shield"));
        assertTrue(inventory.hasItem("Potion"));
    }

    @Test
    void shouldHandleComplexInventoryOperations() {
        // Add various items
        inventory.addItem("Sword", 2);
        inventory.addItem("Potion", 10);
        inventory.addItem("Shield", 1);

        assertEquals(3, inventory.getTotalItems());

        // Use some potions
        inventory.removeItem("Potion", 7);
        assertEquals(3, inventory.getItemCount("Potion"));

        // Use all shields
        inventory.removeItem("Shield", 1);
        assertFalse(inventory.hasItem("Shield"));
        assertEquals(2, inventory.getTotalItems());

        // Add more swords
        inventory.addItem("Sword", 1);
        assertEquals(3, inventory.getItemCount("Sword"));
    }
}

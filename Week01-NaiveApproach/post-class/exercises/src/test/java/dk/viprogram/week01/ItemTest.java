package dk.viprogram.week01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Item class.
 * When all tests pass, you've correctly implemented Item!
 */
class ItemTest {

    @Test
    @DisplayName("Item should store basic properties")
    void itemStoresBasicProperties() {
        Item item = new Item("Test Sword", Item.ItemType.WEAPON, 50, 10);

        assertEquals("Test Sword", item.getName());
        assertEquals(Item.ItemType.WEAPON, item.getType());
        assertEquals(50, item.getValue());
        assertEquals(10, item.getEffect());
    }

    @Test
    @DisplayName("Health potion should be consumable")
    void healthPotionIsConsumable() {
        Item potion = Item.healthPotion();

        assertTrue(potion.isConsumable());
        assertFalse(potion.isEquipable());
        assertEquals(Item.ItemType.POTION, potion.getType());
    }

    @Test
    @DisplayName("Weapon should be equipable")
    void weaponIsEquipable() {
        Item sword = Item.basicSword();

        assertTrue(sword.isEquipable());
        assertFalse(sword.isConsumable());
        assertEquals(Item.ItemType.WEAPON, sword.getType());
    }

    @Test
    @DisplayName("Armor should be equipable")
    void armorIsEquipable() {
        Item armor = Item.leatherArmor();

        assertTrue(armor.isEquipable());
        assertFalse(armor.isConsumable());
        assertEquals(Item.ItemType.ARMOR, armor.getType());
    }

    @Test
    @DisplayName("Factory methods create valid items")
    void factoryMethodsCreateValidItems() {
        Item potion = Item.healthPotion();
        Item basicSword = Item.basicSword();
        Item ironSword = Item.ironSword();
        Item leatherArmor = Item.leatherArmor();
        Item chainMail = Item.chainMail();

        assertAll(
            () -> assertNotNull(potion.getName()),
            () -> assertNotNull(basicSword.getName()),
            () -> assertNotNull(ironSword.getName()),
            () -> assertNotNull(leatherArmor.getName()),
            () -> assertNotNull(chainMail.getName()),
            () -> assertTrue(potion.getValue() > 0),
            () -> assertTrue(basicSword.getValue() > 0),
            () -> assertTrue(ironSword.getValue() > basicSword.getValue())
        );
    }
}

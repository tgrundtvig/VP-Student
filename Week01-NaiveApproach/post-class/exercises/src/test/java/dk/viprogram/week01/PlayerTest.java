package dk.viprogram.week01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Player class with inventory system.
 * When all tests pass, you've correctly implemented Player!
 */
class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestHero", 100, 10);
    }

    @Nested
    @DisplayName("Basic Player Properties")
    class BasicProperties {

        @Test
        @DisplayName("Player should have initial properties")
        void playerHasInitialProperties() {
            assertEquals("TestHero", player.getName());
            assertEquals(100, player.getHealth());
            assertEquals(100, player.getMaxHealth());
            assertEquals(10, player.getAttackPower());
            assertEquals(0, player.getGold());
            assertEquals(1, player.getLevel());
        }

        @Test
        @DisplayName("Player can take damage")
        void playerCanTakeDamage() {
            player.takeDamage(30);
            assertEquals(70, player.getHealth());
        }

        @Test
        @DisplayName("Player health cannot go below zero")
        void healthCannotGoBelowZero() {
            player.takeDamage(200);
            assertEquals(0, player.getHealth());
            assertFalse(player.isAlive());
        }

        @Test
        @DisplayName("Player can heal")
        void playerCanHeal() {
            player.takeDamage(50);
            player.heal(30);
            assertEquals(80, player.getHealth());
        }

        @Test
        @DisplayName("Player cannot heal beyond max health")
        void cannotHealBeyondMaxHealth() {
            player.heal(50);
            assertEquals(100, player.getHealth());
        }
    }

    @Nested
    @DisplayName("Inventory System")
    class InventorySystem {

        @Test
        @DisplayName("Player starts with empty inventory")
        void playerStartsWithEmptyInventory() {
            assertTrue(player.getInventory().isEmpty());
        }

        @Test
        @DisplayName("Player can add items to inventory")
        void playerCanAddItems() {
            Item potion = Item.healthPotion();
            player.addItem(potion);

            assertEquals(1, player.getInventory().size());
            assertTrue(player.hasItem("Health Potion"));
        }

        @Test
        @DisplayName("Player can remove items from inventory")
        void playerCanRemoveItems() {
            Item potion = Item.healthPotion();
            player.addItem(potion);
            player.removeItem(potion);

            assertTrue(player.getInventory().isEmpty());
        }

        @Test
        @DisplayName("Player can find items by name")
        void playerCanFindItemsByName() {
            Item sword = Item.basicSword();
            player.addItem(sword);

            Item found = player.findItem("Basic Sword");
            assertNotNull(found);
            assertEquals(sword, found);
        }

        @Test
        @DisplayName("Finding non-existent item returns null")
        void findNonExistentItemReturnsNull() {
            assertNull(player.findItem("Nonexistent Item"));
        }
    }

    @Nested
    @DisplayName("Item Usage")
    class ItemUsage {

        @Test
        @DisplayName("Using potion heals player and removes potion")
        void usingPotionHealsAndRemoves() {
            player.takeDamage(50);
            Item potion = Item.healthPotion();
            player.addItem(potion);

            assertTrue(player.useItem(potion));
            assertEquals(80, player.getHealth()); // 50 + 30 healing
            assertFalse(player.hasItem("Health Potion"));
        }

        @Test
        @DisplayName("Cannot use item not in inventory")
        void cannotUseItemNotInInventory() {
            Item potion = Item.healthPotion();
            assertFalse(player.useItem(potion));
        }

        @Test
        @DisplayName("Using weapon equips it")
        void usingWeaponEquipsIt() {
            Item sword = Item.basicSword();
            player.addItem(sword);

            assertTrue(player.useItem(sword));
            assertEquals(sword, player.getEquippedWeapon());
        }

        @Test
        @DisplayName("Using armor equips it")
        void usingArmorEquipsIt() {
            Item armor = Item.leatherArmor();
            player.addItem(armor);

            assertTrue(player.useItem(armor));
            assertEquals(armor, player.getEquippedArmor());
        }
    }

    @Nested
    @DisplayName("Equipment")
    class Equipment {

        @Test
        @DisplayName("Equipping weapon increases attack power")
        void equippingWeaponIncreasesAttack() {
            assertEquals(10, player.getAttackPower());

            Item sword = Item.basicSword(); // +5 attack
            player.addItem(sword);
            player.equipWeapon(sword);

            assertEquals(15, player.getAttackPower());
        }

        @Test
        @DisplayName("Equipping better weapon increases attack more")
        void equippingBetterWeaponIncreasesAttackMore() {
            Item basicSword = Item.basicSword();  // +5
            Item ironSword = Item.ironSword();    // +10

            player.addItem(basicSword);
            player.addItem(ironSword);

            player.equipWeapon(basicSword);
            assertEquals(15, player.getAttackPower());

            player.equipWeapon(ironSword);
            assertEquals(20, player.getAttackPower());
        }

        @Test
        @DisplayName("Equipping armor provides defense")
        void equippingArmorProvidesDefense() {
            assertEquals(0, player.getDefense());

            Item armor = Item.leatherArmor(); // +3 defense
            player.addItem(armor);
            player.equipArmor(armor);

            assertEquals(3, player.getDefense());
        }

        @Test
        @DisplayName("Defense reduces damage taken")
        void defenseReducesDamage() {
            Item armor = Item.leatherArmor(); // +3 defense
            player.addItem(armor);
            player.equipArmor(armor);

            player.takeDamage(10); // Should only take 7 damage
            assertEquals(93, player.getHealth());
        }

        @Test
        @DisplayName("Cannot equip non-weapon as weapon")
        void cannotEquipNonWeaponAsWeapon() {
            Item potion = Item.healthPotion();
            assertThrows(IllegalArgumentException.class, () -> player.equipWeapon(potion));
        }

        @Test
        @DisplayName("Cannot equip non-armor as armor")
        void cannotEquipNonArmorAsArmor() {
            Item sword = Item.basicSword();
            assertThrows(IllegalArgumentException.class, () -> player.equipArmor(sword));
        }
    }

    @Nested
    @DisplayName("Gold Management")
    class GoldManagement {

        @Test
        @DisplayName("Player can add gold")
        void playerCanAddGold() {
            player.addGold(50);
            assertEquals(50, player.getGold());
        }

        @Test
        @DisplayName("Player can spend gold")
        void playerCanSpendGold() {
            player.addGold(100);
            assertTrue(player.spendGold(30));
            assertEquals(70, player.getGold());
        }

        @Test
        @DisplayName("Cannot spend more gold than player has")
        void cannotSpendMoreThanHas() {
            player.addGold(20);
            assertFalse(player.spendGold(50));
            assertEquals(20, player.getGold()); // Gold unchanged
        }
    }

    @Nested
    @DisplayName("Experience and Leveling")
    class ExperienceAndLeveling {

        @Test
        @DisplayName("Player gains experience")
        void playerGainsExperience() {
            player.addExperience(50);
            assertEquals(50, player.getExperience());
        }

        @Test
        @DisplayName("Player levels up at 100 XP")
        void playerLevelsUpAt100XP() {
            player.addExperience(100);
            assertEquals(2, player.getLevel());
        }

        @Test
        @DisplayName("Leveling up increases stats")
        void levelingUpIncreasesStats() {
            int initialMaxHealth = player.getMaxHealth();
            int initialAttack = player.getAttackPower();

            player.addExperience(100);

            assertTrue(player.getMaxHealth() > initialMaxHealth);
            assertTrue(player.getAttackPower() > initialAttack);
        }

        @Test
        @DisplayName("Leveling up restores health")
        void levelingUpRestoresHealth() {
            player.takeDamage(50);
            player.addExperience(100);

            assertEquals(player.getMaxHealth(), player.getHealth());
        }
    }
}

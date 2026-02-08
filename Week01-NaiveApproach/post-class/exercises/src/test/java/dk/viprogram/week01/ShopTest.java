package dk.viprogram.week01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Shop class.
 * When all tests pass, you've correctly implemented Shop!
 */
class ShopTest {

    private Shop shop;
    private Player player;

    @BeforeEach
    void setUp() {
        shop = new Shop("Test Shop");
        player = new Player("TestHero", 100, 10);
        player.addGold(100); // Give player some starting gold
    }

    @Test
    @DisplayName("Shop has name")
    void shopHasName() {
        assertEquals("Test Shop", shop.getName());
    }

    @Test
    @DisplayName("Shop starts with inventory")
    void shopStartsWithInventory() {
        assertFalse(shop.getInventory().isEmpty());
    }

    @Test
    @DisplayName("Player can buy item with enough gold")
    void playerCanBuyItem() {
        Item item = shop.getInventory().get(0);
        int itemPrice = item.getValue();
        int initialGold = player.getGold();

        assertTrue(shop.buyItem(player, item));
        assertEquals(initialGold - itemPrice, player.getGold());
        assertTrue(player.hasItem(item.getName()));
    }

    @Test
    @DisplayName("Player cannot buy item without enough gold")
    void cannotBuyWithoutGold() {
        player = new Player("PoorHero", 100, 10); // No gold
        Item expensiveItem = Item.chainMail(); // Most expensive
        shop.addItem(expensiveItem);

        assertFalse(shop.buyItem(player, expensiveItem));
        assertFalse(player.hasItem(expensiveItem.getName()));
    }

    @Test
    @DisplayName("Bought item is removed from shop")
    void boughtItemRemovedFromShop() {
        Item item = shop.getInventory().get(0);
        int initialShopSize = shop.getInventory().size();

        shop.buyItem(player, item);

        assertEquals(initialShopSize - 1, shop.getInventory().size());
    }

    @Test
    @DisplayName("Player can sell item to shop")
    void playerCanSellItem() {
        Item sword = Item.basicSword();
        player.addItem(sword);
        int sellPrice = shop.getSellPrice(sword);
        int initialGold = player.getGold();

        assertTrue(shop.sellItem(player, sword));
        assertEquals(initialGold + sellPrice, player.getGold());
        assertFalse(player.hasItem(sword.getName()));
    }

    @Test
    @DisplayName("Sold item is added to shop inventory")
    void soldItemAddedToShop() {
        Item sword = Item.basicSword();
        player.addItem(sword);
        int initialShopSize = shop.getInventory().size();

        shop.sellItem(player, sword);

        assertEquals(initialShopSize + 1, shop.getInventory().size());
    }

    @Test
    @DisplayName("Sell price is 50% of buy price")
    void sellPriceIsHalfOfBuyPrice() {
        Item sword = Item.basicSword();

        int buyPrice = shop.getBuyPrice(sword);
        int sellPrice = shop.getSellPrice(sword);

        assertEquals(buyPrice / 2, sellPrice);
    }

    @Test
    @DisplayName("Cannot sell item not in player inventory")
    void cannotSellItemNotOwned() {
        Item sword = Item.basicSword();
        // Player doesn't have the sword

        assertFalse(shop.sellItem(player, sword));
    }

    @Test
    @DisplayName("Cannot buy item not in shop inventory")
    void cannotBuyItemNotInShop() {
        Item fakeItem = new Item("Fake Item", Item.ItemType.WEAPON, 1, 1);

        assertFalse(shop.buyItem(player, fakeItem));
    }
}

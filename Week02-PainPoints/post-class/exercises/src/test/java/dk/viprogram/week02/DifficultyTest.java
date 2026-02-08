package dk.viprogram.week02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Difficulty system.
 *
 * TEACHING NOTE: These tests show how difficulty affects
 * multiple systems in scattered ways.
 */
class DifficultyTest {

    @Test
    @DisplayName("Easy mode increases player damage")
    void easyModeIncreasesPlayerDamage() {
        assertEquals(1.25, DifficultyManager.getPlayerDamageMultiplier("EASY"));
    }

    @Test
    @DisplayName("Easy mode decreases enemy damage")
    void easyModeDecreasesEnemyDamage() {
        assertEquals(0.75, DifficultyManager.getEnemyDamageMultiplier("EASY"));
    }

    @Test
    @DisplayName("Easy mode doubles gold rewards")
    void easyModeDoublesGold() {
        assertEquals(2.0, DifficultyManager.getGoldMultiplier("EASY"));
    }

    @Test
    @DisplayName("Nightmare mode disables saving")
    void nightmareDisablesSaving() {
        assertTrue(DifficultyManager.canSaveGame("NORMAL"));
        assertFalse(DifficultyManager.canSaveGame("NIGHTMARE"));
    }

    @Test
    @DisplayName("Hard mode increases enemy health")
    void hardModeIncreasesEnemyHealth() {
        assertEquals(1.5, DifficultyManager.getEnemyHealthMultiplier("HARD"));
    }

    // PAIN POINT: Testing difficulty integration requires real Player
    @Test
    @DisplayName("Player takes less damage on easy")
    void playerTakesLessDamageOnEasy() {
        Player player = new Player("TestHero", 100, 10);
        player.setDifficulty("EASY");

        player.takeDamage(100);

        // Should take 75% of 100 = 75, but minimum 1
        // Player has no armor, so full damage modified by difficulty
        assertTrue(player.getHealth() > 0);
        assertTrue(player.getHealth() < 100);
    }

    @Test
    @DisplayName("Player earns more gold on easy")
    void playerEarnsMoreGoldOnEasy() {
        Player normalPlayer = new Player("NormalHero", 100, 10);
        Player easyPlayer = new Player("EasyHero", 100, 10);

        normalPlayer.setDifficulty("NORMAL");
        easyPlayer.setDifficulty("EASY");

        normalPlayer.addGold(100);
        easyPlayer.addGold(100);

        assertTrue(easyPlayer.getGold() > normalPlayer.getGold());
    }

    // PAIN POINT: Nightmare mode affects multiple systems
    @Test
    @DisplayName("Nightmare mode is brutal")
    void nightmareModeIsBrutal() {
        assertEquals(0.75, DifficultyManager.getPlayerDamageMultiplier("NIGHTMARE"));
        assertEquals(1.5, DifficultyManager.getEnemyDamageMultiplier("NIGHTMARE"));
        assertEquals(0.5, DifficultyManager.getGoldMultiplier("NIGHTMARE"));
        assertEquals(2.0, DifficultyManager.getEnemyHealthMultiplier("NIGHTMARE"));
        assertFalse(DifficultyManager.canSaveGame("NIGHTMARE"));
    }
}

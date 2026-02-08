package dk.viprogram.week03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Difficulty system using strategy pattern.
 *
 * Notice: Each difficulty can be tested independently!
 * No if-else chains to verify.
 */
class DifficultyTest {

    @Test
    @DisplayName("Easy difficulty has correct modifiers")
    void easyDifficultyModifiers() {
        IDifficultyStrategy easy = new EasyDifficulty();

        assertEquals("Easy", easy.getName());
        assertEquals(1.25, easy.getPlayerDamageMultiplier());
        assertEquals(0.75, easy.getEnemyDamageMultiplier());
        assertEquals(2.0, easy.getGoldMultiplier());
        assertEquals(0.5, easy.getEnemyHealthMultiplier());
        assertTrue(easy.canSaveGame());
    }

    @Test
    @DisplayName("Normal difficulty has standard modifiers")
    void normalDifficultyModifiers() {
        IDifficultyStrategy normal = new NormalDifficulty();

        assertEquals("Normal", normal.getName());
        assertEquals(1.0, normal.getPlayerDamageMultiplier());
        assertEquals(1.0, normal.getEnemyDamageMultiplier());
        assertEquals(1.0, normal.getGoldMultiplier());
        assertEquals(1.0, normal.getEnemyHealthMultiplier());
        assertTrue(normal.canSaveGame());
    }

    @Test
    @DisplayName("Hard difficulty has challenging modifiers")
    void hardDifficultyModifiers() {
        IDifficultyStrategy hard = new HardDifficulty();

        assertEquals("Hard", hard.getName());
        assertEquals(1.0, hard.getPlayerDamageMultiplier());
        assertEquals(1.25, hard.getEnemyDamageMultiplier());
        assertEquals(1.5, hard.getEnemyHealthMultiplier());
        assertEquals(1.25, hard.getXPMultiplier());
        assertTrue(hard.canSaveGame());
    }

    @Test
    @DisplayName("Nightmare difficulty is brutal")
    void nightmareDifficultyModifiers() {
        IDifficultyStrategy nightmare = new NightmareDifficulty();

        assertEquals("Nightmare", nightmare.getName());
        assertEquals(0.75, nightmare.getPlayerDamageMultiplier());
        assertEquals(1.5, nightmare.getEnemyDamageMultiplier());
        assertEquals(0.5, nightmare.getGoldMultiplier());
        assertEquals(2.0, nightmare.getEnemyHealthMultiplier());
        assertFalse(nightmare.canSaveGame());  // No saving!
    }

    @Test
    @DisplayName("Different difficulties have different names")
    void difficultiesHaveDifferentNames() {
        IDifficultyStrategy easy = new EasyDifficulty();
        IDifficultyStrategy normal = new NormalDifficulty();
        IDifficultyStrategy hard = new HardDifficulty();
        IDifficultyStrategy nightmare = new NightmareDifficulty();

        assertNotEquals(easy.getName(), normal.getName());
        assertNotEquals(normal.getName(), hard.getName());
        assertNotEquals(hard.getName(), nightmare.getName());
    }

    @Test
    @DisplayName("All difficulties implement the same interface")
    void allImplementSameInterface() {
        // This test demonstrates polymorphism
        IDifficultyStrategy[] difficulties = {
            new EasyDifficulty(),
            new NormalDifficulty(),
            new HardDifficulty(),
            new NightmareDifficulty()
        };

        for (IDifficultyStrategy difficulty : difficulties) {
            assertNotNull(difficulty.getName());
            assertTrue(difficulty.getEnemyHealthMultiplier() > 0);
        }
    }
}

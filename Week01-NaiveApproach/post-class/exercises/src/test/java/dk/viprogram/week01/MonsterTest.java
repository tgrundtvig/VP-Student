package dk.viprogram.week01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Monster class.
 * When all tests pass, you've correctly implemented Monster!
 */
class MonsterTest {

    @Test
    @DisplayName("Monster stores basic properties")
    void monsterStoresBasicProperties() {
        Monster monster = new Monster("Test Monster", "test", 50, 10, 20, 30);

        assertEquals("Test Monster", monster.getName());
        assertEquals("test", monster.getType());
        assertEquals(50, monster.getHealth());
        assertEquals(50, monster.getMaxHealth());
        assertEquals(10, monster.getAttackPower());
        assertEquals(20, monster.getGoldDrop());
        assertEquals(30, monster.getExperienceDrop());
    }

    @Test
    @DisplayName("Monster can take damage")
    void monsterCanTakeDamage() {
        Monster monster = Monster.goblin();
        int initialHealth = monster.getHealth();

        monster.takeDamage(10);

        assertEquals(initialHealth - 10, monster.getHealth());
    }

    @Test
    @DisplayName("Monster health cannot go below zero")
    void monsterHealthCannotGoBelowZero() {
        Monster monster = Monster.goblin();

        monster.takeDamage(1000);

        assertEquals(0, monster.getHealth());
        assertFalse(monster.isAlive());
    }

    @Test
    @DisplayName("Factory method creates valid goblin")
    void factoryCreatesGoblin() {
        Monster goblin = Monster.goblin();

        assertEquals("Goblin", goblin.getName());
        assertEquals("goblin", goblin.getType());
        assertTrue(goblin.getHealth() > 0);
        assertTrue(goblin.getAttackPower() > 0);
        assertTrue(goblin.getGoldDrop() > 0);
        assertTrue(goblin.getExperienceDrop() > 0);
    }

    @Test
    @DisplayName("Factory method creates valid orc")
    void factoryCreatesOrc() {
        Monster orc = Monster.orc();

        assertEquals("Orc", orc.getName());
        assertTrue(orc.getHealth() > Monster.goblin().getHealth());
        assertTrue(orc.getAttackPower() > Monster.goblin().getAttackPower());
    }

    @Test
    @DisplayName("Dragon is the strongest monster")
    void dragonIsStrongest() {
        Monster dragon = Monster.dragon();
        Monster goblin = Monster.goblin();
        Monster orc = Monster.orc();

        assertTrue(dragon.getHealth() > orc.getHealth());
        assertTrue(dragon.getAttackPower() > orc.getAttackPower());
        assertTrue(dragon.getGoldDrop() > orc.getGoldDrop());
    }

    @Test
    @DisplayName("Scaled goblin scales with player level")
    void scaledGoblinScalesWithLevel() {
        Monster level1Goblin = Monster.scaledGoblin(1);
        Monster level5Goblin = Monster.scaledGoblin(5);

        assertTrue(level5Goblin.getHealth() > level1Goblin.getHealth());
        assertTrue(level5Goblin.getAttackPower() > level1Goblin.getAttackPower());
        assertTrue(level5Goblin.getGoldDrop() > level1Goblin.getGoldDrop());
    }

    @Test
    @DisplayName("Scaled orc scales with player level")
    void scaledOrcScalesWithLevel() {
        Monster level1Orc = Monster.scaledOrc(1);
        Monster level5Orc = Monster.scaledOrc(5);

        assertTrue(level5Orc.getHealth() > level1Orc.getHealth());
        assertTrue(level5Orc.getAttackPower() > level1Orc.getAttackPower());
        assertTrue(level5Orc.getGoldDrop() > level1Orc.getGoldDrop());
    }
}

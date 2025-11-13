package dk.viprogram.week01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Character class.
 * When all tests pass, you've correctly implemented Character!
 */
class CharacterTest {

    @Test
    void shouldCreateCharacterWithCorrectValues() {
        Character hero = new Character("Hero", 100, 15);

        assertEquals("Hero", hero.getName());
        assertEquals(100, hero.getHealth());
        assertEquals(15, hero.getAttackPower());
    }

    @Test
    void shouldReduceHealthWhenTakingDamage() {
        Character hero = new Character("Hero", 100, 15);
        hero.takeDamage(30);

        assertEquals(70, hero.getHealth());
    }

    @Test
    void shouldNotAllowNegativeHealth() {
        Character hero = new Character("Hero", 50, 10);
        hero.takeDamage(100);  // Massive damage

        assertEquals(0, hero.getHealth());
        assertTrue(hero.getHealth() >= 0, "Health should never be negative");
    }

    @Test
    void shouldBeAliveWhenHealthIsPositive() {
        Character hero = new Character("Hero", 50, 10);

        assertTrue(hero.isAlive());
    }

    @Test
    void shouldBeDeadWhenHealthIsZero() {
        Character hero = new Character("Hero", 10, 10);
        hero.takeDamage(10);

        assertFalse(hero.isAlive());
    }

    @Test
    void shouldReturnAttackPower() {
        Character hero = new Character("Hero", 100, 25);

        assertEquals(25, hero.attack());
    }

    @Test
    void shouldHandleMultipleDamageInstances() {
        Character hero = new Character("Hero", 100, 10);

        hero.takeDamage(20);
        assertEquals(80, hero.getHealth());

        hero.takeDamage(30);
        assertEquals(50, hero.getHealth());

        hero.takeDamage(10);
        assertEquals(40, hero.getHealth());

        assertTrue(hero.isAlive());
    }
}

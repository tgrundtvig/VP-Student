package dk.ek.evu.vpf26.textadventure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Monster")
class MonsterTest {

    private Monster monster;

    @BeforeEach
    void setUp() {
        monster = new Monster("TestGoblin", 50, 8);
    }

    @Nested
    @DisplayName("Constructor and Getters")
    class ConstructorAndGetters {

        @Test
        @DisplayName("should initialize with correct name")
        void shouldInitializeWithCorrectName() {
            assertEquals("TestGoblin", monster.getName());
        }

        @Test
        @DisplayName("should initialize with correct health")
        void shouldInitializeWithCorrectHealth() {
            assertEquals(50, monster.getHealth());
        }

        @Test
        @DisplayName("should initialize with correct damage")
        void shouldInitializeWithCorrectDamage() {
            assertEquals(8, monster.getDamage());
        }

        @Test
        @DisplayName("should be alive when created")
        void shouldBeAliveWhenCreated() {
            assertTrue(monster.isAlive());
        }

        @Test
        @DisplayName("should allow custom health values")
        void shouldAllowCustomHealthValues() {
            Monster dragon = new Monster("Dragon", 500, 25);
            assertEquals(500, dragon.getHealth());
        }

        @Test
        @DisplayName("should allow different damage values")
        void shouldAllowDifferentDamageValues() {
            Monster weakMonster = new Monster("Rat", 10, 2);
            Monster strongMonster = new Monster("Boss", 1000, 100);
            assertEquals(2, weakMonster.getDamage());
            assertEquals(100, strongMonster.getDamage());
        }
    }

    @Nested
    @DisplayName("takeDamage")
    class TakeDamage {

        @Test
        @DisplayName("should reduce health by damage amount")
        void shouldReduceHealthByDamageAmount() {
            monster.takeDamage(15);
            assertEquals(35, monster.getHealth());
        }

        @Test
        @DisplayName("should return new health value")
        void shouldReturnNewHealthValue() {
            int newHealth = monster.takeDamage(20);
            assertEquals(30, newHealth);
        }

        @Test
        @DisplayName("should not reduce health below zero")
        void shouldNotReduceHealthBelowZero() {
            monster.takeDamage(100);
            assertEquals(0, monster.getHealth());
        }

        @Test
        @DisplayName("should handle exact health damage")
        void shouldHandleExactHealthDamage() {
            monster.takeDamage(50);
            assertEquals(0, monster.getHealth());
        }

        @Test
        @DisplayName("should accumulate damage over multiple hits")
        void shouldAccumulateDamageOverMultipleHits() {
            monster.takeDamage(10);
            monster.takeDamage(15);
            monster.takeDamage(5);
            assertEquals(20, monster.getHealth());
        }

        @Test
        @DisplayName("should handle zero damage")
        void shouldHandleZeroDamage() {
            monster.takeDamage(0);
            assertEquals(50, monster.getHealth());
        }
    }

    @Nested
    @DisplayName("isAlive")
    class IsAlive {

        @Test
        @DisplayName("should return true when health is positive")
        void shouldReturnTrueWhenHealthIsPositive() {
            monster.takeDamage(30);
            assertTrue(monster.isAlive());
        }

        @Test
        @DisplayName("should return false when health is zero")
        void shouldReturnFalseWhenHealthIsZero() {
            monster.takeDamage(50);
            assertFalse(monster.isAlive());
        }

        @Test
        @DisplayName("should return false after overkill damage")
        void shouldReturnFalseAfterOverkillDamage() {
            monster.takeDamage(200);
            assertFalse(monster.isAlive());
        }

        @Test
        @DisplayName("should return true with 1 health remaining")
        void shouldReturnTrueWith1HealthRemaining() {
            monster.takeDamage(49);
            assertTrue(monster.isAlive());
            assertEquals(1, monster.getHealth());
        }
    }

    @Nested
    @DisplayName("Different Monster Types")
    class DifferentMonsterTypes {

        @Test
        @DisplayName("should create weak monster correctly")
        void shouldCreateWeakMonsterCorrectly() {
            Monster rat = new Monster("Rat", 10, 2);
            assertEquals("Rat", rat.getName());
            assertEquals(10, rat.getHealth());
            assertEquals(2, rat.getDamage());
            assertTrue(rat.isAlive());
        }

        @Test
        @DisplayName("should create boss monster correctly")
        void shouldCreateBossMonsterCorrectly() {
            Monster boss = new Monster("Dragon", 500, 50);
            assertEquals("Dragon", boss.getName());
            assertEquals(500, boss.getHealth());
            assertEquals(50, boss.getDamage());
            assertTrue(boss.isAlive());
        }

        @Test
        @DisplayName("weak monster should die quickly")
        void weakMonsterShouldDieQuickly() {
            Monster rat = new Monster("Rat", 10, 2);
            rat.takeDamage(10);
            assertFalse(rat.isAlive());
        }

        @Test
        @DisplayName("boss monster should survive many hits")
        void bossMonsterShouldSurviveManyHits() {
            Monster boss = new Monster("Dragon", 500, 50);
            for (int i = 0; i < 49; i++) {
                boss.takeDamage(10);
            }
            assertTrue(boss.isAlive());
            assertEquals(10, boss.getHealth());
        }
    }

    @Nested
    @DisplayName("Combat Scenarios")
    class CombatScenarios {

        @Test
        @DisplayName("should survive multiple hits from weak player")
        void shouldSurviveMultipleHitsFromWeakPlayer() {
            int playerDamage = 5;
            monster.takeDamage(playerDamage);
            monster.takeDamage(playerDamage);
            monster.takeDamage(playerDamage);
            assertTrue(monster.isAlive());
            assertEquals(35, monster.getHealth());
        }

        @Test
        @DisplayName("should die from strong player hit")
        void shouldDieFromStrongPlayerHit() {
            int strongPlayerDamage = 100;
            monster.takeDamage(strongPlayerDamage);
            assertFalse(monster.isAlive());
        }

        @Test
        @DisplayName("should calculate hits to kill correctly")
        void shouldCalculateHitsToKillCorrectly() {
            int playerDamage = 10;
            int expectedHitsToKill = 5;

            for (int i = 0; i < expectedHitsToKill - 1; i++) {
                monster.takeDamage(playerDamage);
                assertTrue(monster.isAlive(), "Monster should be alive after " + (i + 1) + " hits");
            }

            monster.takeDamage(playerDamage);
            assertFalse(monster.isAlive(), "Monster should be dead after " + expectedHitsToKill + " hits");
        }
    }
}

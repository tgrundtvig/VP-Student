package dk.ek.evu.vpf26.textadventure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Player")
class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestHero", 15);
    }

    @Nested
    @DisplayName("Constructor and Getters")
    class ConstructorAndGetters {

        @Test
        @DisplayName("should initialize with correct name")
        void shouldInitializeWithCorrectName() {
            assertEquals("TestHero", player.getName());
        }

        @Test
        @DisplayName("should initialize with correct attack power")
        void shouldInitializeWithCorrectAttackPower() {
            assertEquals(15, player.getAttackPower());
        }

        @Test
        @DisplayName("should initialize with 100 health")
        void shouldInitializeWith100Health() {
            assertEquals(100, player.getHealth());
        }

        @Test
        @DisplayName("should be alive when created")
        void shouldBeAliveWhenCreated() {
            assertTrue(player.isAlive());
        }
    }

    @Nested
    @DisplayName("takeDamage")
    class TakeDamage {

        @Test
        @DisplayName("should reduce health by damage amount")
        void shouldReduceHealthByDamageAmount() {
            player.takeDamage(30);
            assertEquals(70, player.getHealth());
        }

        @Test
        @DisplayName("should return new health value")
        void shouldReturnNewHealthValue() {
            int newHealth = player.takeDamage(25);
            assertEquals(75, newHealth);
        }

        @Test
        @DisplayName("should not reduce health below zero")
        void shouldNotReduceHealthBelowZero() {
            player.takeDamage(150);
            assertEquals(0, player.getHealth());
        }

        @Test
        @DisplayName("should handle exact health damage")
        void shouldHandleExactHealthDamage() {
            player.takeDamage(100);
            assertEquals(0, player.getHealth());
        }

        @Test
        @DisplayName("should accumulate damage over multiple hits")
        void shouldAccumulateDamageOverMultipleHits() {
            player.takeDamage(20);
            player.takeDamage(30);
            player.takeDamage(10);
            assertEquals(40, player.getHealth());
        }

        @Test
        @DisplayName("should handle zero damage")
        void shouldHandleZeroDamage() {
            player.takeDamage(0);
            assertEquals(100, player.getHealth());
        }
    }

    @Nested
    @DisplayName("isAlive")
    class IsAlive {

        @Test
        @DisplayName("should return true when health is positive")
        void shouldReturnTrueWhenHealthIsPositive() {
            player.takeDamage(50);
            assertTrue(player.isAlive());
        }

        @Test
        @DisplayName("should return false when health is zero")
        void shouldReturnFalseWhenHealthIsZero() {
            player.takeDamage(100);
            assertFalse(player.isAlive());
        }

        @Test
        @DisplayName("should return false after overkill damage")
        void shouldReturnFalseAfterOverkillDamage() {
            player.takeDamage(500);
            assertFalse(player.isAlive());
        }

        @Test
        @DisplayName("should return true with 1 health remaining")
        void shouldReturnTrueWith1HealthRemaining() {
            player.takeDamage(99);
            assertTrue(player.isAlive());
            assertEquals(1, player.getHealth());
        }
    }

    @Nested
    @DisplayName("heal")
    class Heal {

        @Test
        @DisplayName("should restore health by heal amount")
        void shouldRestoreHealthByHealAmount() {
            player.takeDamage(50);
            player.heal(20);
            assertEquals(70, player.getHealth());
        }

        @Test
        @DisplayName("should return new health value")
        void shouldReturnNewHealthValue() {
            player.takeDamage(40);
            int newHealth = player.heal(15);
            assertEquals(75, newHealth);
        }

        @Test
        @DisplayName("should not exceed max health")
        void shouldNotExceedMaxHealth() {
            player.takeDamage(20);
            player.heal(50);
            assertEquals(100, player.getHealth());
        }

        @Test
        @DisplayName("should have no effect when already at max health")
        void shouldHaveNoEffectWhenAlreadyAtMaxHealth() {
            player.heal(50);
            assertEquals(100, player.getHealth());
        }

        @Test
        @DisplayName("should heal to exactly max health")
        void shouldHealToExactlyMaxHealth() {
            player.takeDamage(30);
            player.heal(30);
            assertEquals(100, player.getHealth());
        }

        @Test
        @DisplayName("should accumulate healing over multiple heals")
        void shouldAccumulateHealingOverMultipleHeals() {
            player.takeDamage(80);
            player.heal(10);
            player.heal(15);
            player.heal(5);
            assertEquals(50, player.getHealth());
        }

        @Test
        @DisplayName("should handle zero healing")
        void shouldHandleZeroHealing() {
            player.takeDamage(30);
            player.heal(0);
            assertEquals(70, player.getHealth());
        }
    }

    @Nested
    @DisplayName("Combat Scenarios")
    class CombatScenarios {

        @Test
        @DisplayName("should survive multiple small hits")
        void shouldSurviveMultipleSmallHits() {
            for (int i = 0; i < 9; i++) {
                player.takeDamage(10);
            }
            assertTrue(player.isAlive());
            assertEquals(10, player.getHealth());
        }

        @Test
        @DisplayName("should die on the tenth small hit")
        void shouldDieOnTenthSmallHit() {
            for (int i = 0; i < 10; i++) {
                player.takeDamage(10);
            }
            assertFalse(player.isAlive());
        }

        @Test
        @DisplayName("should handle damage and heal cycle")
        void shouldHandleDamageAndHealCycle() {
            player.takeDamage(40);
            assertEquals(60, player.getHealth());
            player.heal(20);
            assertEquals(80, player.getHealth());
            player.takeDamage(30);
            assertEquals(50, player.getHealth());
            player.heal(100);
            assertEquals(100, player.getHealth());
        }
    }
}

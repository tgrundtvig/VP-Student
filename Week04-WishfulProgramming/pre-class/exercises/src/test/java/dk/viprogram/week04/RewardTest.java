package dk.viprogram.week04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Reward implementations.
 *
 * These tests verify that your reward classes correctly implement
 * the Reward interface and grant appropriate rewards to players.
 */
class RewardTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestHero", 100, 10);
    }

    @Nested
    @DisplayName("Exercise 2a: GoldReward")
    class GoldRewardTests {

        @Test
        @DisplayName("GoldReward should have correct description")
        void goldRewardDescription() {
            Reward reward = new GoldReward(50);
            assertEquals("50 gold", reward.getDescription());
        }

        @Test
        @DisplayName("GoldReward should add gold to player")
        void goldRewardGrantsGold() {
            Reward reward = new GoldReward(100);
            int initialGold = player.getGold();

            reward.grantTo(player);

            assertEquals(initialGold + 100, player.getGold());
        }

        @Test
        @DisplayName("Different gold amounts should work correctly")
        void differentGoldAmounts() {
            Reward small = new GoldReward(10);
            Reward large = new GoldReward(500);

            assertEquals("10 gold", small.getDescription());
            assertEquals("500 gold", large.getDescription());
        }
    }

    @Nested
    @DisplayName("Exercise 2b: ExperienceReward")
    class ExperienceRewardTests {

        @Test
        @DisplayName("ExperienceReward should have correct description")
        void experienceRewardDescription() {
            Reward reward = new ExperienceReward(100);
            assertEquals("100 XP", reward.getDescription());
        }

        @Test
        @DisplayName("ExperienceReward should add experience to player")
        void experienceRewardGrantsXP() {
            Reward reward = new ExperienceReward(50);
            int initialXP = player.getExperience();

            reward.grantTo(player);

            assertEquals(initialXP + 50, player.getExperience());
        }
    }

    @Nested
    @DisplayName("Exercise 2c: HealingReward")
    class HealingRewardTests {

        @Test
        @DisplayName("HealingReward should have correct description")
        void healingRewardDescription() {
            Reward reward = new HealingReward(30);
            assertEquals("Restore 30 HP", reward.getDescription());
        }

        @Test
        @DisplayName("HealingReward should heal the player")
        void healingRewardHealsPlayer() {
            // Damage the player first
            player.takeDamage(50);
            int damagedHealth = player.getHealth();

            Reward reward = new HealingReward(30);
            reward.grantTo(player);

            assertEquals(damagedHealth + 30, player.getHealth());
        }
    }

    @Nested
    @DisplayName("Exercise 2d: CompositeReward")
    class CompositeRewardTests {

        @Test
        @DisplayName("CompositeReward should combine descriptions")
        void compositeRewardDescription() {
            Reward composite = new CompositeReward(
                new GoldReward(50),
                new ExperienceReward(100)
            );

            String description = composite.getDescription();
            assertTrue(description.contains("50 gold"));
            assertTrue(description.contains("100 XP"));
        }

        @Test
        @DisplayName("CompositeReward should grant all rewards")
        void compositeRewardGrantsAll() {
            int initialGold = player.getGold();
            int initialXP = player.getExperience();

            Reward composite = new CompositeReward(
                new GoldReward(50),
                new ExperienceReward(100)
            );

            composite.grantTo(player);

            assertEquals(initialGold + 50, player.getGold());
            assertEquals(initialXP + 100, player.getExperience());
        }

        @Test
        @DisplayName("CompositeReward with three rewards")
        void compositeWithThreeRewards() {
            player.takeDamage(20); // Damage player first
            int initialGold = player.getGold();
            int initialXP = player.getExperience();
            int damagedHealth = player.getHealth();

            Reward composite = new CompositeReward(
                new GoldReward(25),
                new ExperienceReward(50),
                new HealingReward(15)
            );

            composite.grantTo(player);

            assertEquals(initialGold + 25, player.getGold());
            assertEquals(initialXP + 50, player.getExperience());
            assertEquals(damagedHealth + 15, player.getHealth());
        }
    }
}

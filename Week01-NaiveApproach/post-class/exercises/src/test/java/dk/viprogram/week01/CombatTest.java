package dk.viprogram.week01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Combat class.
 * When all tests pass, you've correctly implemented Combat!
 */
class CombatTest {

    private Player player;
    private Monster monster;

    @BeforeEach
    void setUp() {
        player = new Player("TestHero", 100, 20);
        monster = new Monster("TestMonster", "test", 50, 10, 25, 30);
    }

    @Test
    @DisplayName("Combat executes a round")
    void combatExecutesRound() {
        Combat combat = new Combat(player, monster);

        combat.executeRound();

        // Player should have attacked monster
        assertTrue(monster.getHealth() < 50);
        // Monster should have attacked player
        assertTrue(player.getHealth() < 100);
    }

    @Test
    @DisplayName("Combat ends when monster is defeated")
    void combatEndsWhenMonsterDefeated() {
        // Strong player vs weak monster
        player = new Player("StrongHero", 100, 100);
        monster = new Monster("WeakMonster", "test", 10, 5, 10, 20);

        Combat combat = new Combat(player, monster);
        combat.executeRound();

        assertTrue(combat.isCombatEnded());
        assertTrue(combat.didPlayerWin());
        assertFalse(monster.isAlive());
    }

    @Test
    @DisplayName("Combat ends when player is defeated")
    void combatEndsWhenPlayerDefeated() {
        // Weak player vs strong monster
        player = new Player("WeakHero", 10, 1);
        monster = new Monster("StrongMonster", "test", 100, 100, 10, 20);

        Combat combat = new Combat(player, monster);
        combat.executeRound();

        assertTrue(combat.isCombatEnded());
        assertFalse(combat.didPlayerWin());
        assertFalse(player.isAlive());
    }

    @Test
    @DisplayName("Player receives rewards on victory")
    void playerReceivesRewardsOnVictory() {
        player = new Player("StrongHero", 100, 100);
        monster = new Monster("TestMonster", "test", 10, 5, 25, 30);
        int initialGold = player.getGold();
        int initialXP = player.getExperience();

        Combat combat = new Combat(player, monster);
        combat.runFullCombat();

        assertTrue(combat.didPlayerWin());
        assertEquals(initialGold + 25, player.getGold());
        assertEquals(initialXP + 30, player.getExperience());
    }

    @Test
    @DisplayName("Full combat runs until completion")
    void fullCombatRunsUntilCompletion() {
        Combat combat = new Combat(player, monster);
        combat.runFullCombat();

        assertTrue(combat.isCombatEnded());
        assertTrue(combat.didPlayerWin() || !player.isAlive());
    }

    @Test
    @DisplayName("Combat log records events")
    void combatLogRecordsEvents() {
        Combat combat = new Combat(player, monster);
        combat.runFullCombat();

        assertFalse(combat.getCombatLog().isEmpty());
        assertTrue(combat.getCombatLog().stream()
            .anyMatch(log -> log.contains("attacks")));
    }

    @Test
    @DisplayName("Player with armor takes reduced damage")
    void playerWithArmorTakesReducedDamage() {
        Item armor = Item.chainMail(); // +7 defense
        player.addItem(armor);
        player.equipArmor(armor);

        Combat combat = new Combat(player, monster);
        combat.executeRound();

        // Monster attacks for 10, armor reduces by 7, so player takes 3 damage
        // But minimum damage is 1, so should take at least 1
        assertTrue(player.getHealth() <= 100);
        assertTrue(player.getHealth() >= 97); // At most 3 damage taken
    }

    @Test
    @DisplayName("Player with weapon deals more damage")
    void playerWithWeaponDealsMoreDamage() {
        Item sword = Item.ironSword(); // +10 attack
        player.addItem(sword);
        player.equipWeapon(sword);

        int expectedDamage = player.getAttackPower(); // 20 + 10 = 30

        Combat combat = new Combat(player, monster);
        combat.executeRound();

        // Monster should have taken at least 30 damage
        assertTrue(monster.getHealth() <= 50 - expectedDamage);
    }
}

package dk.viprogram.week02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for EnhancedCharacter
 *
 * These tests will help you discover the complexity of testing
 * when everything is tightly coupled.
 *
 * Notice how hard it is to test individual features in isolation!
 */
public class EnhancedCharacterTest {
    private EnhancedCharacter fireballCharacter;
    private EnhancedCharacter healCharacter;
    private EnhancedCharacter stealthCharacter;

    @BeforeEach
    public void setUp() {
        fireballCharacter = new EnhancedCharacter("Wizard", 100, 20, "Fireball");
        healCharacter = new EnhancedCharacter("Priest", 100, 15, "Heal");
        stealthCharacter = new EnhancedCharacter("Rogue", 80, 25, "Stealth");
    }

    @Test
    public void testInitialState() {
        assertEquals("Wizard", fireballCharacter.getName());
        assertEquals(100, fireballCharacter.getHealth());
        assertEquals(50, fireballCharacter.getMana());
        assertEquals(0, fireballCharacter.getAbilityCooldown());
    }

    @Test
    public void testUseFireballAbility() {
        // TODO: Test that Fireball can be used when there's enough mana
        // But wait - how do we test the damage it deals?
        // The ability doesn't return damage, it just... happens?

        boolean used = fireballCharacter.useSpecialAbility();
        // What should we assert here?
        // How do we know it worked correctly?
    }

    @Test
    public void testAbilityCooldown() {
        // TODO: Test that abilities go on cooldown after use
        // But how many turns? And how do we simulate turns?

        fireballCharacter.useSpecialAbility();
        // Now it should be on cooldown...

        boolean usedAgain = fireballCharacter.useSpecialAbility();
        // Should be false, but for how long?

        // Simulate some turns
        fireballCharacter.processTurn();
        fireballCharacter.processTurn();
        fireballCharacter.processTurn();

        // Can we use it now? How do we know?
    }

    @Test
    public void testStealthAffectsNextAttack() {
        // TODO: Test that Stealth makes the next attack do 3x damage
        // But the attack calculation is in the character...
        // And it needs to track state between method calls...

        int normalDamage = stealthCharacter.calculateAttackDamage();

        stealthCharacter.useSpecialAbility();
        int stealthDamage = stealthCharacter.calculateAttackDamage();

        // Should be 3x, but only once
        int secondAttack = stealthCharacter.calculateAttackDamage();

        // How do we test this without actually having combat?
    }

    @Test
    public void testManaRegeneration() {
        // TODO: Test that mana regenerates each turn
        // Use ability to reduce mana
        fireballCharacter.useSpecialAbility();
        int manaAfterUse = fireballCharacter.getMana();

        // Process a turn
        fireballCharacter.processTurn();
        int manaAfterTurn = fireballCharacter.getMana();

        // Should regenerate 5 mana
        // But what if it was already full?
        // What if regeneration rules change?
    }

    @Test
    public void testHealAbility() {
        // TODO: Test that Heal restores health
        // But first we need to damage the character...
        // How do we damage it for testing without having a full battle?

        healCharacter.setHealth(50);  // Manually set health for testing
        healCharacter.useSpecialAbility();

        // What should the health be now?
        // 50% of max health is 50, so 50 + 50 = 100?
        // But what if they're already at full health?
    }

    @Test
    public void testInsufficientMana() {
        // TODO: Test that abilities can't be used without enough mana
        // But how do we reduce mana for testing?

        // Manually set mana to 0 for testing
        // But wait, there's no setMana method!
        // Do we add it just for testing?
    }

    @Test
    public void testComplexScenario() {
        // TODO: Test a complex scenario with multiple abilities and turns
        // This test will become very long and fragile!

        // Use Stealth
        stealthCharacter.useSpecialAbility();

        // Attack (should be 3x damage)
        int damage1 = stealthCharacter.calculateAttackDamage();

        // Process turn (reduces cooldown, regenerates mana)
        stealthCharacter.processTurn();

        // Try to use ability again (should fail - on cooldown)
        boolean usedAgain = stealthCharacter.useSpecialAbility();

        // Attack (should be normal damage)
        int damage2 = stealthCharacter.calculateAttackDamage();

        // Process multiple turns
        for (int i = 0; i < 4; i++) {
            stealthCharacter.processTurn();
        }

        // Now we should be able to use ability again
        boolean usedFinal = stealthCharacter.useSpecialAbility();

        // How do we assert all of this?
        // The test is testing EVERYTHING at once!
    }

    // Notice the problems:
    // 1. We can't test abilities in isolation
    // 2. We need to manipulate internal state for testing
    // 3. Tests are testing multiple things at once
    // 4. Changes to one feature break tests for other features
    // 5. We're testing implementation details, not behavior
}
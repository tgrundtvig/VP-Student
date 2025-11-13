package dk.viprogram.week01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the CombatCalculator class.
 * When all tests pass, you've correctly implemented CombatCalculator!
 */
class CombatCalculatorTest {

    private final CombatCalculator calculator = new CombatCalculator();

    @Test
    void shouldCalculateBasicDamage() {
        int damage = calculator.calculateDamage(20, 5);
        assertEquals(15, damage);
    }

    @Test
    void shouldReturnMinimumDamageOfOne() {
        int damage = calculator.calculateDamage(10, 15);
        assertEquals(1, damage, "Minimum damage should be 1");
    }

    @Test
    void shouldReturnMinimumDamageWhenEqualPowers() {
        int damage = calculator.calculateDamage(10, 10);
        assertEquals(1, damage);
    }

    @Test
    void shouldDetectCriticalHit() {
        assertTrue(calculator.isCriticalHit(90));
        assertTrue(calculator.isCriticalHit(95));
        assertTrue(calculator.isCriticalHit(99));
    }

    @Test
    void shouldNotBeCriticalHitBelowThreshold() {
        assertFalse(calculator.isCriticalHit(89));
        assertFalse(calculator.isCriticalHit(50));
        assertFalse(calculator.isCriticalHit(0));
    }

    @Test
    void shouldDoubleDamageForCritical() {
        assertEquals(20, calculator.calculateCriticalDamage(10));
        assertEquals(40, calculator.calculateCriticalDamage(20));
        assertEquals(2, calculator.calculateCriticalDamage(1));
    }

    @Test
    void shouldDetectEvasion() {
        assertTrue(calculator.canEvade(85));
        assertTrue(calculator.canEvade(90));
        assertTrue(calculator.canEvade(99));
    }

    @Test
    void shouldNotEvadeBelowThreshold() {
        assertFalse(calculator.canEvade(84));
        assertFalse(calculator.canEvade(50));
        assertFalse(calculator.canEvade(0));
    }

    @Test
    void shouldReturnZeroDamageWhenEvaded() {
        int damage = calculator.calculateFinalDamage(20, 5, 50, 90);
        assertEquals(0, damage, "Evaded attacks should deal 0 damage");
    }

    @Test
    void shouldReturnCriticalDamageWhenCriticalHit() {
        int damage = calculator.calculateFinalDamage(20, 5, 95, 50);
        // Base damage: 20 - 5 = 15, Critical: 15 * 2 = 30
        assertEquals(30, damage);
    }

    @Test
    void shouldReturnNormalDamageWhenNoSpecialConditions() {
        int damage = calculator.calculateFinalDamage(20, 5, 50, 50);
        // Normal damage: 20 - 5 = 15
        assertEquals(15, damage);
    }

    @Test
    void shouldPrioritizeEvasionOverCritical() {
        // Both critical (95) and evade (90) conditions met - evasion wins
        int damage = calculator.calculateFinalDamage(20, 5, 95, 90);
        assertEquals(0, damage, "Evasion should prevent even critical hits");
    }

    @Test
    void shouldHandleMinimumDamageScenarios() {
        // High defense, no crit, no evade
        int damage = calculator.calculateFinalDamage(10, 15, 50, 50);
        assertEquals(1, damage, "Should still deal minimum 1 damage");
    }

    @Test
    void shouldHandleCriticalWithMinimumDamage() {
        // High defense but critical hit
        int damage = calculator.calculateFinalDamage(10, 15, 95, 50);
        // Minimum damage is 1, critical: 1 * 2 = 2
        assertEquals(2, damage);
    }
}

package dk.viprogram.week03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for ICombatant implementations
 *
 * Notice how we can test ANY implementation of ICombatant!
 * This is the power of interfaces - write tests once, use for all implementations.
 */
public class CombatantTest {
    private ICombatant warrior;
    private ICombatant mage;
    private ICombatant mock;

    @BeforeEach
    public void setUp() {
        warrior = new SimpleWarrior("Conan", 100, 15);
        mage = new SimpleMage("Gandalf", 80, 20);
        mock = new MockCombatant(50, 10);
    }

    @Test
    public void testInterfaceContract() {
        // All implementations must fulfill the contract
        assertNotNull(warrior);
        assertNotNull(mage);
        assertNotNull(mock);

        // All must implement required methods
        assertTrue(warrior.getHealth() > 0);
        assertTrue(mage.getHealth() > 0);
        assertTrue(mock.getHealth() > 0);
    }

    @Test
    public void testPolymorphism() {
        // We can treat all implementations the same way!
        ICombatant[] combatants = {warrior, mage, mock};

        for (ICombatant c : combatants) {
            assertTrue(c.isAlive());  // All use the default method
            assertNotNull(c.getCombatStatus());  // All use the default method
        }
    }

    @Test
    public void testCombatBetweenDifferentTypes() {
        // Warrior attacks Mage - they're both ICombatants!
        int mageHealthBefore = mage.getHealth();
        warrior.attack(mage);

        // Mage should take damage
        assertTrue(mage.getHealth() < mageHealthBefore);
    }

    @Test
    public void testMockingForIsolation() {
        // We can test combat logic with mocks!
        MockCombatant attacker = new MockCombatant(100, 20);
        MockCombatant defender = new MockCombatant(100, 15);

        // Test attack counting
        attacker.attack(defender);
        assertEquals(1, attacker.getAttackCount());
        assertEquals(20, defender.getTotalDamageReceived());

        // Test multiple attacks
        attacker.attack(defender);
        assertEquals(2, attacker.getAttackCount());
        assertEquals(40, defender.getTotalDamageReceived());
    }

    @Test
    public void testDefaultMethods() {
        // All implementations get isAlive() for free
        ICombatant combatant = new MockCombatant(100, 10);
        assertTrue(combatant.isAlive());

        // Reduce health to 0
        combatant.takeDamage(100);
        assertFalse(combatant.isAlive());
    }

    @Test
    public void testDifferentImplementationsBehaveDifferently() {
        // Create fresh instances
        SimpleWarrior w = new SimpleWarrior("Test Warrior", 100, 20);
        SimpleMage m = new SimpleMage("Test Mage", 100, 20);

        // Both implement ICombatant but behave differently
        // Warrior has consistent attack
        assertEquals(20, w.getAttackPower());
        assertEquals(20, w.getAttackPower());  // Same every time

        // Mage attack varies with mana (once implemented)
        // This test will pass once you implement SimpleMage correctly
    }

    @Test
    public void testInterfaceAsParameterType() {
        // Method that accepts ANY ICombatant
        performCombat(warrior, mage);
        performCombat(mage, mock);
        performCombat(mock, warrior);
        // All combinations work!
    }

    // This method accepts ANY ICombatant implementation!
    private void performCombat(ICombatant attacker, ICombatant defender) {
        int healthBefore = defender.getHealth();
        attacker.attack(defender);

        // Verify combat occurred
        assertTrue(defender.getHealth() <= healthBefore,
            "Defender should take damage or no damage if attacker is dead");
    }

    @Test
    public void testInterfaceVariableDeclaration() {
        // Always declare variables as interface type!
        ICombatant c1 = new SimpleWarrior("W1", 100, 10);
        ICombatant c2 = new SimpleMage("M1", 80, 15);
        ICombatant c3 = new MockCombatant(50, 5);

        // Can swap implementations without changing code
        c1 = new SimpleMage("M2", 100, 10);  // Was warrior, now mage!
        c2 = new MockCombatant(80, 15);      // Was mage, now mock!

        // Code using c1 and c2 doesn't need to change!
    }
}
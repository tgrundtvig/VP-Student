package dk.viprogram.week03;

/**
 * Exercise 4: Mock Implementation for Testing
 *
 * This is a simple implementation used ONLY for testing.
 * It shows how interfaces enable easy testing by allowing us to
 * create simple, predictable implementations.
 *
 * A mock tracks method calls so we can verify behavior in tests.
 *
 * TODO: Complete this mock implementation
 * Keep it simple - just track the values
 */
public class MockCombatant implements ICombatant {
    private int health;
    private int maxHealth;
    private int attackPower;
    private int damageReceived = 0;
    private int attackCount = 0;

    public MockCombatant(int health, int attackPower) {
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
    }

    @Override
    public int getHealth() {
        // TODO: Return current health
        return 0;
    }

    @Override
    public int getMaxHealth() {
        // TODO: Return maximum health
        return 0;
    }

    @Override
    public int getAttackPower() {
        // TODO: Return attack power
        return 0;
    }

    @Override
    public void takeDamage(int damage) {
        // TODO: Track damage for testing
        // 1. Reduce health by damage (don't go below 0)
        // 2. Add damage to damageReceived counter
    }

    @Override
    public void attack(ICombatant target) {
        // TODO: Track attacks for testing
        // 1. Only attack if both this and target are alive
        // 2. Deal damage to target
        // 3. Increment attackCount
    }

    // Testing helper methods - these are already implemented for you

    /**
     * Get the total damage this mock has received (for testing).
     * @return total damage received
     */
    public int getTotalDamageReceived() {
        return damageReceived;
    }

    /**
     * Get the number of attacks this mock has performed (for testing).
     * @return attack count
     */
    public int getAttackCount() {
        return attackCount;
    }

    /**
     * Reset all counters to zero (for testing).
     */
    public void resetCounters() {
        damageReceived = 0;
        attackCount = 0;
    }
}

package dk.viprogram.week03;

/**
 * Exercise 4: Mock Implementation for Testing
 *
 * This is a simple implementation used ONLY for testing.
 * It shows how interfaces enable easy testing.
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
        // TODO: Implement
        return health;
    }

    @Override
    public int getMaxHealth() {
        // TODO: Implement
        return maxHealth;
    }

    @Override
    public int getAttackPower() {
        // TODO: Implement
        return attackPower;
    }

    @Override
    public void takeDamage(int damage) {
        // TODO: Track damage for testing
        // Update both health and damageReceived
        health = Math.max(0, health - damage);
        damageReceived += damage;
    }

    @Override
    public void attack(ICombatant target) {
        // TODO: Track attacks for testing
        // Increment attackCount and call target.takeDamage
        if (this.isAlive() && target.isAlive()) {
            target.takeDamage(attackPower);
            attackCount++;
        }
    }

    // Testing helper methods
    public int getTotalDamageReceived() {
        return damageReceived;
    }

    public int getAttackCount() {
        return attackCount;
    }

    public void resetCounters() {
        damageReceived = 0;
        attackCount = 0;
    }
}
package dk.viprogram.week03;

/**
 * Exercise 2: Implementing an Interface
 *
 * This class implements the ICombatant interface.
 * It MUST provide implementations for all non-default methods.
 *
 * TODO: Complete the implementation of all required methods
 */
public class SimpleWarrior implements ICombatant {
    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;

    public SimpleWarrior(String name, int maxHealth, int attackPower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
    }

    @Override
    public int getHealth() {
        // TODO: Return current health
        return health;
    }

    @Override
    public int getMaxHealth() {
        // TODO: Return maximum health
        return maxHealth;
    }

    @Override
    public int getAttackPower() {
        // TODO: Return attack power
        return attackPower;
    }

    @Override
    public void takeDamage(int damage) {
        // TODO: Reduce health by damage amount
        // Make sure health doesn't go below 0
        health = Math.max(0, health - damage);
    }

    @Override
    public void attack(ICombatant target) {
        // TODO: Make this warrior attack the target
        // The warrior should deal its attack power as damage
        if (this.isAlive() && target.isAlive()) {
            target.takeDamage(this.attackPower);
        }
    }

    // Notice: We get isAlive() and getCombatStatus() for free from the interface!

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Warrior %s: %s", name, getCombatStatus());
    }
}
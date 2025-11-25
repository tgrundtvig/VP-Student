package dk.viprogram.week03;

/**
 * Exercise 1: Your First Interface
 *
 * This interface defines a contract for anything that can engage in combat.
 * Notice how it specifies WHAT a combatant can do, not HOW it does it.
 *
 * An interface contains:
 * - Method signatures (no implementation)
 * - Constants (public static final)
 * - Default methods (with implementation)
 * - Static methods
 *
 * TODO: Study this interface and understand what it requires
 */
public interface ICombatant {
    /**
     * Get the current health of this combatant
     * @return current health points
     */
    int getHealth();

    /**
     * Get the maximum health of this combatant
     * @return maximum health points
     */
    int getMaxHealth();

    /**
     * Get the attack power of this combatant
     * @return attack damage
     */
    int getAttackPower();

    /**
     * Take damage from an attack
     * @param damage the amount of damage to take
     */
    void takeDamage(int damage);

    /**
     * Attack another combatant
     * @param target the combatant to attack
     */
    void attack(ICombatant target);

    /**
     * Check if this combatant is still alive
     * @return true if health > 0
     *
     * This is a DEFAULT method - it provides an implementation
     * that all implementing classes get for free!
     */
    default boolean isAlive() {
        return getHealth() > 0;
    }

    /**
     * Get a combat description
     * @return description of this combatant's state
     *
     * Another default method using other interface methods
     */
    default String getCombatStatus() {
        return String.format("Health: %d/%d, Attack: %d",
            getHealth(), getMaxHealth(), getAttackPower());
    }
}
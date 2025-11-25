package dk.viprogram.week03;

/**
 * Exercise 3: Another Implementation
 *
 * This class also implements ICombatant but with different behavior.
 * Mages have less health but more attack power.
 *
 * TODO: Implement this class differently from SimpleWarrior
 * - Mages do 1.5x damage but have less health
 * - Mages have mana that affects their attacks
 */
public class SimpleMage implements ICombatant {
    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;
    private int mana;
    private int maxMana;

    public SimpleMage(String name, int maxHealth, int attackPower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
        this.maxMana = 100;
        this.mana = maxMana;
    }

    @Override
    public int getHealth() {
        // TODO: Implement
        return 0;
    }

    @Override
    public int getMaxHealth() {
        // TODO: Implement
        return 0;
    }

    @Override
    public int getAttackPower() {
        // TODO: Return higher attack if mana available
        // If mana > 0: return attackPower * 1.5
        // Otherwise: return attackPower / 2
        return 0;
    }

    @Override
    public void takeDamage(int damage) {
        // TODO: Mages take full damage
    }

    @Override
    public void attack(ICombatant target) {
        // TODO: Attack uses mana
        // If mana >= 10: use magical attack (1.5x damage), consume 10 mana
        // Otherwise: use weak physical attack (0.5x damage)
    }

    public int getMana() {
        return mana;
    }

    public void restoreMana(int amount) {
        mana = Math.min(maxMana, mana + amount);
    }

    @Override
    public String toString() {
        return String.format("Mage %s: %s, Mana: %d/%d",
            name, getCombatStatus(), mana, maxMana);
    }
}
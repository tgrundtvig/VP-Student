package dk.viprogram.week04;

/**
 * A simple Player class for the quest system exercises.
 * This class is PROVIDED - you do not need to modify it.
 */
public class Player {
    private final String name;
    private int health;
    private final int maxHealth;
    private int gold;
    private int experience;
    private int attackPower;

    public Player(String name, int maxHealth, int attackPower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
        this.gold = 0;
        this.experience = 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getGold() {
        return gold;
    }

    public int getExperience() {
        return experience;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public void addExperience(int amount) {
        this.experience += amount;
    }

    public void heal(int amount) {
        this.health = Math.min(health + amount, maxHealth);
    }

    public void takeDamage(int amount) {
        this.health = Math.max(0, health - amount);
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String toString() {
        return String.format("%s [HP: %d/%d, ATK: %d, Gold: %d, XP: %d]",
            name, health, maxHealth, attackPower, gold, experience);
    }
}

package dk.viprogram.week04;

/**
 * Simple Player class for the exercises.
 * This is PROVIDED - do not modify.
 */
public class Player {
    private String name;
    private int gold;
    private int experience;
    private int health;
    private int maxHealth;
    
    public Player(String name) {
        this.name = name;
        this.gold = 0;
        this.experience = 0;
        this.maxHealth = 100;
        this.health = maxHealth;
    }
    
    public String getName() { return name; }
    public int getGold() { return gold; }
    public int getExperience() { return experience; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    
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
        this.health = Math.max(health - amount, 0);
    }
    
    public boolean isAlive() {
        return health > 0;
    }
}

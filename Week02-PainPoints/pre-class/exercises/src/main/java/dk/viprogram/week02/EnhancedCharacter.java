package dk.viprogram.week02;

/**
 * Exercise 1: Extending the Character Class
 *
 * Take your Character class from Week 1 and add special abilities.
 * You'll quickly discover that adding features isn't as simple as it seems!
 *
 * Requirements:
 * - Add a "specialAbility" field that can be "Fireball", "Heal", or "Stealth"
 * - Add a mana system (abilities cost mana)
 * - Add a cooldown system (abilities can't be used every turn)
 * - Make abilities affect combat differently
 *
 * TODO: Copy your Character class from Week 1 and extend it here
 * TODO: Notice how many things you need to change
 * TODO: Consider what happens if you want to add more abilities later
 */
public class EnhancedCharacter {
    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;
    private int level;

    // New fields for abilities
    private String specialAbility;  // "Fireball", "Heal", or "Stealth"
    private int mana;
    private int maxMana;
    private int abilityCooldown;    // Turns until ability can be used again

    public EnhancedCharacter(String name, int health, int attackPower, String specialAbility) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
        this.level = 1;
        this.specialAbility = specialAbility;
        this.maxMana = 50;
        this.mana = maxMana;
        this.abilityCooldown = 0;
    }

    /**
     * Use the character's special ability
     *
     * TODO: Implement this method
     * - Fireball: Deals 2x attack power damage, costs 20 mana, 3 turn cooldown
     * - Heal: Restores 50% of max health, costs 15 mana, 2 turn cooldown
     * - Stealth: Next attack does 3x damage, costs 25 mana, 4 turn cooldown
     *
     * Return true if ability was used successfully, false otherwise
     */
    public boolean useSpecialAbility() {
        // TODO: Implement special ability logic
        // Notice: How do you handle the different effects?
        // How does Stealth's "next attack" work?
        // Where do you store the state?
        return false;
    }

    /**
     * Process a turn (reduces cooldowns, regenerates mana)
     */
    public void processTurn() {
        // TODO: Reduce cooldown if > 0
        // TODO: Regenerate 5 mana per turn (up to maxMana)
    }

    /**
     * Attack another character
     * TODO: Modify this to account for Stealth bonus
     */
    public int calculateAttackDamage() {
        // TODO: Return attack damage
        // If Stealth was used, this should be 3x normal damage (once)
        return attackPower;
    }

    // TODO: Add getters and setters
    // Notice: How many methods need to change to support abilities?

    public String getName() { return name; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getMana() { return mana; }
    public String getSpecialAbility() { return specialAbility; }
    public int getAbilityCooldown() { return abilityCooldown; }
}
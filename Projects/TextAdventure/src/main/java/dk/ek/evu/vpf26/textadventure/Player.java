package dk.ek.evu.vpf26.textadventure;

public class Player
{
    private final String name; // Player's name
    private int health; // Current health points
    private final int maxHealth; // Maximum health (for healing limits)
    private final int attackPower; // Damage dealt per attack

    public Player(String name, int attackPower)
    {
        this.name = name;
        this.attackPower = attackPower;
        this.health = 100;
        this.maxHealth = 100;
    }

    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return health;
    }

    public int getAttackPower()
    {
        return attackPower;
    }

    public int takeDamage(int amount) // Reduces health, minimum 0
    {
        health -= amount;
        health = Math.max(health, 0);
        return health;
    }

    public boolean isAlive() // Returns true if health > 0
    {
        return health > 0;
    }

    public int heal(int amount)
    {
        health += amount;
        health = Math.min(health, maxHealth);
        return health;
    }
}

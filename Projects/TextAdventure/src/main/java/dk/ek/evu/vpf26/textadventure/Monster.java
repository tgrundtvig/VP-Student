package dk.ek.evu.vpf26.textadventure;

public class Monster
{
    private final String name;
    private int health;
    private final int damage;

    public Monster(String name, int health, int damage)
    {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int takeDamage(int amount)
    {
        health -= amount;
        health = Math.max(health, 0);
        return health;
    }

    public boolean isAlive() // Returns true if health > 0
    {
        return health > 0;
    }
}

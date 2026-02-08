package dk.viprogram.week03;

/**
 * Basic implementation of IPlayer.
 *
 * Notice: Uses IGuild interface, not concrete class.
 * The player doesn't need to know about guild implementation details!
 */
public class BasicPlayer implements IPlayer {
    private final String name;
    private int health;
    private int maxHealth;
    private int attackPower;
    private int gold;
    private int experience;
    private int level;
    private IGuild guild;

    public BasicPlayer(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
        this.gold = 0;
        this.experience = 0;
        this.level = 1;
        this.guild = null;
    }

    @Override
    public String getName() {
        // TODO: Return the player name
        return null;
    }

    @Override
    public int getLevel() {
        // TODO: Return the player level
        return 0;
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
    public int getGold() {
        // TODO: Return current gold
        return 0;
    }

    @Override
    public void joinGuild(IGuild guild) {
        // TODO: Join a guild. If the player is already in a guild,
        // leave the current guild first (remove this player from it).
        // Then set the new guild and add this player to it.
    }

    @Override
    public void leaveGuild() {
        // TODO: Leave the current guild. Remove this player from the guild
        // and set the guild reference to null.
        // Do nothing if the player is not in a guild.
    }

    @Override
    public IGuild getGuild() {
        // TODO: Return the player's current guild (or null if not in one)
        return null;
    }

    @Override
    public void addGold(int amount) {
        // TODO: Add the given amount of gold to the player
    }

    @Override
    public void addExperience(int amount) {
        // TODO: Add experience to the player.
        // If experience reaches or exceeds the threshold (level * 100),
        // subtract the threshold and call levelUp().
        // Repeat in case of multiple level-ups at once.
    }

    /**
     * Levels up the player:
     * - Increment level by 1
     * - Increase maxHealth by 10
     * - Restore health to maxHealth
     * - Increase attackPower by 2
     */
    private void levelUp() {
        // TODO: Implement the level up logic described in the JavaDoc above.
    }

    @Override
    public void takeDamage(int amount) {
        // TODO: Reduce health by the given amount (minimum damage is 1).
        // Health cannot go below 0.
    }

    @Override
    public void heal(int amount) {
        // TODO: Increase health by the given amount.
        // Health cannot exceed maxHealth.
    }

    @Override
    public boolean isAlive() {
        // TODO: Return true if health is greater than 0
        return false;
    }

    public int getAttackPower() {
        // TODO: Return the player's attack power
        return 0;
    }
}

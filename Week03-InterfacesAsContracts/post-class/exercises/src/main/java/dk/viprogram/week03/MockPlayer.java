package dk.viprogram.week03;

/**
 * Mock implementation of IPlayer for testing.
 *
 * Notice how easy it is to create test doubles with interfaces!
 * No need for complex Player setup or real game state.
 */
public class MockPlayer implements IPlayer {
    private final String name;
    private int level;
    private int health;
    private int maxHealth;
    private int gold;
    private IGuild guild;

    public MockPlayer(String name) {
        this.name = name;
        this.level = 1;
        this.health = 100;
        this.maxHealth = 100;
        this.gold = 0;
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
        // TODO: Set the guild and add this player to it (if guild is not null)
    }

    @Override
    public void leaveGuild() {
        // TODO: Remove this player from the guild (if in one) and set guild to null
    }

    @Override
    public IGuild getGuild() {
        // TODO: Return the current guild
        return null;
    }

    @Override
    public void addGold(int amount) {
        // TODO: Add gold to the player
    }

    @Override
    public void addExperience(int amount) {
        // Simplified for mock - no leveling logic needed
    }

    @Override
    public void takeDamage(int amount) {
        // TODO: Reduce health by amount, minimum health is 0
    }

    @Override
    public void heal(int amount) {
        // TODO: Increase health by amount, maximum is maxHealth
    }

    @Override
    public boolean isAlive() {
        // TODO: Return true if health is greater than 0
        return false;
    }

    // Test helper methods

    public void setLevel(int level) {
        // TODO: Set the level (used for test setup)
    }

    public void setHealth(int health) {
        // TODO: Set the health (used for test setup)
    }

    public void setGold(int gold) {
        // TODO: Set the gold (used for test setup)
    }
}

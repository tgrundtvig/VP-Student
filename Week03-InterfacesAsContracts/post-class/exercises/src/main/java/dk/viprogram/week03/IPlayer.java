package dk.viprogram.week03;

/**
 * Interface defining the contract for a Player.
 *
 * Notice: Uses interface (IGuild) not concrete class.
 * This breaks the circular dependency!
 */
public interface IPlayer {
    String getName();
    int getLevel();
    int getHealth();
    int getMaxHealth();
    int getGold();

    void joinGuild(IGuild guild);
    void leaveGuild();
    IGuild getGuild();

    void addGold(int amount);
    void addExperience(int amount);
    void takeDamage(int amount);
    void heal(int amount);

    boolean isAlive();
}

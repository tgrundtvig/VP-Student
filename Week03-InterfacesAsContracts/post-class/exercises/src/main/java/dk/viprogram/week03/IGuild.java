package dk.viprogram.week03;

/**
 * Interface defining the contract for a Guild.
 *
 * Notice: This interface only defines WHAT a guild can do,
 * not HOW it does it. No circular dependencies!
 */
public interface IGuild {
    String getName();
    int getLevel();
    void addMember(IPlayer player);
    void removeMember(IPlayer player);
    int getMemberCount();
    int getAttackBonus();
    int getDefenseBonus();
    void addToBank(int amount);
    boolean withdrawFromBank(int amount);
    int getBankBalance();
}

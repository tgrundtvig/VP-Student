package dk.viprogram.week03;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic implementation of IGuild.
 *
 * Notice: No circular dependency - we use IPlayer interface,
 * not the concrete Player class.
 */
public class BasicGuild implements IGuild {
    private final String name;
    private final List<IPlayer> members;
    private int level;
    private int bankGold;

    private static final int BASE_ATTACK_BONUS = 2;
    private static final int BASE_DEFENSE_BONUS = 1;

    public BasicGuild(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.level = 1;
        this.bankGold = 0;
    }

    @Override
    public String getName() {
        // TODO: Return the guild name
        return null;
    }

    @Override
    public int getLevel() {
        // TODO: Return the guild level
        return 0;
    }

    @Override
    public void addMember(IPlayer player) {
        // TODO: Add a player to the guild if they are not already a member.
        // After adding, recalculate the guild level.
        // Hint: Check if the player is already in the members list before adding.
    }

    @Override
    public void removeMember(IPlayer player) {
        // TODO: Remove the player from the guild.
        // After removing, recalculate the guild level.
    }

    /**
     * Recalculates the guild level based on the number of members.
     *
     * Level thresholds:
     * - 20+ members: level 4
     * - 10+ members: level 3
     * - 5+ members: level 2
     * - Otherwise: level 1
     */
    private void recalculateLevel() {
        // TODO: Implement the level calculation based on member count.
        // Use the thresholds described in the JavaDoc above.
    }

    @Override
    public int getMemberCount() {
        // TODO: Return the number of members in the guild
        return 0;
    }

    @Override
    public int getAttackBonus() {
        // TODO: Return the attack bonus (BASE_ATTACK_BONUS * level)
        return 0;
    }

    @Override
    public int getDefenseBonus() {
        // TODO: Return the defense bonus (BASE_DEFENSE_BONUS * level)
        return 0;
    }

    @Override
    public void addToBank(int amount) {
        // TODO: Add the given amount to the guild bank
    }

    @Override
    public boolean withdrawFromBank(int amount) {
        // TODO: Withdraw the given amount from the guild bank.
        // Return true if successful, false if insufficient funds.
        // Do not change the balance if there are insufficient funds.
        return false;
    }

    @Override
    public int getBankBalance() {
        // TODO: Return the current bank balance
        return 0;
    }
}

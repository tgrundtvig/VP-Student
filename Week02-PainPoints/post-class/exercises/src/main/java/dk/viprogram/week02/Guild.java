package dk.viprogram.week02;

import java.util.ArrayList;
import java.util.List;

/**
 * Guild system implementation - the "painful" way.
 *
 * TEACHING NOTE: This demonstrates circular dependencies and
 * tight coupling between Guild and Player.
 *
 * Notice:
 * - Bidirectional relationship with Player
 * - Guild must know about Player internals
 * - Hard to test without creating real Players
 * - Changes to Player might break Guild
 */
public class Guild {
    private String name;
    private List<Player> members;  // Circular dependency with Player!
    private Player leader;
    private int level;
    private int bankGold;

    // Bonuses scale with guild level
    private static final int BASE_ATTACK_BONUS = 2;
    private static final int BASE_DEFENSE_BONUS = 1;

    public Guild(String name, Player founder) {
        this.name = name;
        this.members = new ArrayList<>();
        this.leader = founder;
        this.level = 1;
        this.bankGold = 0;

        // TODO: Founder automatically joins the guild.
        // Hint: Add the founder as a member and tell the founder about this guild.
        // Think about the circular dependency this creates!
    }

    public void addMember(Player player) {
        // TODO: Add a player to the guild if they are not already a member.
        // After adding, check if the guild should level up.
    }

    public void removeMember(Player player) {
        // TODO: Remove a player from the guild.
        // If the removed player was the leader and the guild is not empty,
        // assign the first remaining member as the new leader.
    }

    private void checkLevelUp() {
        // TODO: Update the guild level based on member count.
        // Level thresholds:
        //   5 or more members  -> level 2
        //   10 or more members -> level 3
        //   20 or more members -> level 4
        // Otherwise level stays at 1.
    }

    public int getAttackBonus() {
        // TODO: Return the attack bonus (BASE_ATTACK_BONUS * level).
        return 0;
    }

    public int getDefenseBonus() {
        // TODO: Return the defense bonus (BASE_DEFENSE_BONUS * level).
        return 0;
    }

    public void addToBank(int amount) {
        // TODO: Add gold to the guild bank.
    }

    public boolean withdrawFromBank(Player player, int amount) {
        // TODO: Only the leader can withdraw from the bank.
        // Return false if the player is not the leader.
        // Return false if there is not enough gold.
        // Otherwise, subtract the amount and return true.
        return false;
    }

    // PAIN POINT: Getting guild-wide stats requires iterating all players
    public int getTotalGuildHealth() {
        // TODO: Sum up and return the health of all members.
        // Notice: This reaches into Player internals! (Feature envy)
        return 0;
    }

    public int getTotalGuildLevel() {
        // TODO: Sum up and return the level of all members.
        // Notice: More feature envy!
        return 0;
    }

    // PAIN POINT: How do we test this without real players?
    public boolean canRaid(String raidDifficulty) {
        // TODO: Check if the guild's total member level meets the
        // required threshold for the given raid difficulty:
        //   "EASY"   -> requires total level >= 5
        //   "NORMAL" -> requires total level >= 10
        //   "HARD"   -> requires total level >= 20
        //   default  -> requires total level >= 100
        return false;
    }

    // Getters
    public String getName() { return name; }
    public List<Player> getMembers() { return members; }
    public Player getLeader() { return leader; }
    public int getLevel() { return level; }
    public int getBankGold() { return bankGold; }
    public int getMemberCount() { return members.size(); }
}

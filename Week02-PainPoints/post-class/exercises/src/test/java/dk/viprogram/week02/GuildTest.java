package dk.viprogram.week02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Guild system.
 *
 * TEACHING NOTE: Notice how hard it is to test the guild system
 * because of the circular dependencies with Player.
 *
 * We can't create a Guild without a Player.
 * We can't test Guild bonuses without checking Player state.
 * Every test needs full Player objects.
 */
class GuildTest {

    private Player founder;
    private Guild guild;

    @BeforeEach
    void setUp() {
        // PAIN: Must create real Player objects for every test
        founder = new Player("GuildMaster", 100, 10);
        guild = new Guild("TestGuild", founder);
    }

    @Test
    @DisplayName("Guild is created with founder as member and leader")
    void guildCreatedWithFounder() {
        assertEquals("TestGuild", guild.getName());
        assertEquals(founder, guild.getLeader());
        assertEquals(1, guild.getMemberCount());
        assertTrue(guild.getMembers().contains(founder));
    }

    @Test
    @DisplayName("Guild starts at level 1")
    void guildStartsAtLevel1() {
        assertEquals(1, guild.getLevel());
    }

    @Test
    @DisplayName("Adding members increases guild size")
    void addingMembersIncreasesSize() {
        Player member = new Player("Member1", 100, 10);
        guild.addMember(member);

        assertEquals(2, guild.getMemberCount());
    }

    @Test
    @DisplayName("Guild levels up at 5 members")
    void guildLevelsUpAt5Members() {
        // Add 4 more members (founder + 4 = 5)
        for (int i = 0; i < 4; i++) {
            guild.addMember(new Player("Member" + i, 100, 10));
        }

        assertEquals(2, guild.getLevel());
    }

    @Test
    @DisplayName("Guild provides attack bonus based on level")
    void guildProvidesAttackBonus() {
        // Level 1 guild
        assertEquals(2, guild.getAttackBonus());

        // Level up to level 2
        for (int i = 0; i < 4; i++) {
            guild.addMember(new Player("Member" + i, 100, 10));
        }

        assertEquals(4, guild.getAttackBonus());
    }

    @Test
    @DisplayName("Player attack power includes guild bonus")
    void playerAttackIncludesGuildBonus() {
        // PAIN: We need to check Player state to verify Guild behavior
        int baseAttack = founder.getEffectiveAttackPower();

        // Base attack is 10, guild bonus is 2
        // But wait - founder's getEffectiveAttackPower already includes guild bonus!
        // This is confusing...
        assertEquals(12, baseAttack);
    }

    @Test
    @DisplayName("Gold is shared with guild bank")
    void goldSharedWithGuildBank() {
        int initialBank = guild.getBankGold();

        founder.addGold(100);  // 10% should go to guild

        assertTrue(guild.getBankGold() > initialBank);
    }

    @Test
    @DisplayName("Only leader can withdraw from bank")
    void onlyLeaderCanWithdraw() {
        guild.addToBank(100);
        Player member = new Player("Member", 100, 10);
        guild.addMember(member);

        assertFalse(guild.withdrawFromBank(member, 50));
        assertTrue(guild.withdrawFromBank(founder, 50));
    }

    @Test
    @DisplayName("When leader leaves, new leader is assigned")
    void newLeaderWhenLeaderLeaves() {
        Player member = new Player("Member", 100, 10);
        guild.addMember(member);

        guild.removeMember(founder);

        assertEquals(member, guild.getLeader());
    }

    // PAIN POINT: How do we test canRaid without creating many players?
    @Test
    @DisplayName("Guild can check raid eligibility")
    void guildCanCheckRaidEligibility() {
        // Need combined level of 5 for EASY raid
        // Each player starts at level 1
        // So we need 5 members

        assertFalse(guild.canRaid("EASY"));

        for (int i = 0; i < 4; i++) {
            guild.addMember(new Player("Member" + i, 100, 10));
        }

        assertTrue(guild.canRaid("EASY"));
    }
}

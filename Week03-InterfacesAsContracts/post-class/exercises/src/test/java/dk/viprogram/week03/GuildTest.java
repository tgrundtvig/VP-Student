package dk.viprogram.week03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Guild system using interfaces.
 *
 * Notice: We can test Guild behavior using MockPlayer!
 * No need for complex Player setup.
 */
class GuildTest {

    private IGuild guild;

    @BeforeEach
    void setUp() {
        guild = new BasicGuild("TestGuild");
    }

    @Test
    @DisplayName("Guild has name")
    void guildHasName() {
        assertEquals("TestGuild", guild.getName());
    }

    @Test
    @DisplayName("Guild starts at level 1")
    void guildStartsAtLevel1() {
        assertEquals(1, guild.getLevel());
    }

    @Test
    @DisplayName("Guild starts with no members")
    void guildStartsEmpty() {
        assertEquals(0, guild.getMemberCount());
    }

    @Test
    @DisplayName("Can add members to guild")
    void canAddMembers() {
        IPlayer player = new MockPlayer("TestPlayer");
        guild.addMember(player);

        assertEquals(1, guild.getMemberCount());
    }

    @Test
    @DisplayName("Guild levels up at 5 members")
    void guildLevelsUpAt5Members() {
        for (int i = 0; i < 5; i++) {
            guild.addMember(new MockPlayer("Player" + i));
        }

        assertEquals(2, guild.getLevel());
    }

    @Test
    @DisplayName("Guild levels up at 10 members")
    void guildLevelsUpAt10Members() {
        for (int i = 0; i < 10; i++) {
            guild.addMember(new MockPlayer("Player" + i));
        }

        assertEquals(3, guild.getLevel());
    }

    @Test
    @DisplayName("Attack bonus scales with level")
    void attackBonusScalesWithLevel() {
        assertEquals(2, guild.getAttackBonus());  // Level 1

        for (int i = 0; i < 5; i++) {
            guild.addMember(new MockPlayer("Player" + i));
        }

        assertEquals(4, guild.getAttackBonus());  // Level 2
    }

    @Test
    @DisplayName("Defense bonus scales with level")
    void defenseBonusScalesWithLevel() {
        assertEquals(1, guild.getDefenseBonus());  // Level 1

        for (int i = 0; i < 5; i++) {
            guild.addMember(new MockPlayer("Player" + i));
        }

        assertEquals(2, guild.getDefenseBonus());  // Level 2
    }

    @Test
    @DisplayName("Can add gold to bank")
    void canAddToBank() {
        guild.addToBank(100);
        assertEquals(100, guild.getBankBalance());
    }

    @Test
    @DisplayName("Can withdraw from bank")
    void canWithdrawFromBank() {
        guild.addToBank(100);

        assertTrue(guild.withdrawFromBank(50));
        assertEquals(50, guild.getBankBalance());
    }

    @Test
    @DisplayName("Cannot withdraw more than balance")
    void cannotOverdrawBank() {
        guild.addToBank(50);

        assertFalse(guild.withdrawFromBank(100));
        assertEquals(50, guild.getBankBalance());
    }

    @Test
    @DisplayName("Removing member updates count")
    void removingMemberUpdatesCount() {
        IPlayer player = new MockPlayer("TestPlayer");
        guild.addMember(player);
        guild.removeMember(player);

        assertEquals(0, guild.getMemberCount());
    }

    @Test
    @DisplayName("Removing members can reduce level")
    void removingMembersReducesLevel() {
        // Add 5 members to reach level 2
        IPlayer[] players = new IPlayer[5];
        for (int i = 0; i < 5; i++) {
            players[i] = new MockPlayer("Player" + i);
            guild.addMember(players[i]);
        }
        assertEquals(2, guild.getLevel());

        // Remove one to drop below threshold
        guild.removeMember(players[0]);
        assertEquals(1, guild.getLevel());
    }
}

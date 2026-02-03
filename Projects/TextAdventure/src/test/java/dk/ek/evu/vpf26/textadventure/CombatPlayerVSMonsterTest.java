package dk.ek.evu.vpf26.textadventure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for CombatPlayerVSMonster.
 *
 * NOTE: This test class demonstrates the difficulty of testing tightly coupled code.
 * The CombatPlayerVSMonster class has several design issues that make testing painful:
 *
 * 1. Direct dependency on Scanner for input (hard to mock)
 * 2. Direct use of System.out for output (requires stream redirection)
 * 3. Thread.sleep calls that slow down tests
 * 4. Static method with no way to inject dependencies
 *
 * These tests use System.in/System.out redirection, which is fragile and verbose.
 * In later weeks, we will learn how interfaces solve these problems elegantly.
 */
@DisplayName("CombatPlayerVSMonster")
class CombatPlayerVSMonsterTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputCapture;

    @BeforeEach
    void setUp() {
        outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private Scanner createScannerWithInput(String... inputs) {
        String input = String.join("\n", inputs) + "\n";
        return new Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    private String getCapturedOutput() {
        return outputCapture.toString();
    }

    @Nested
    @DisplayName("Run Command")
    class RunCommand {

        @Test
        @DisplayName("should allow player to run from battle")
        void shouldAllowPlayerToRunFromBattle() {
            Player player = new Player("Hero", 10);
            Monster monster = new Monster("Goblin", 30, 5);
            Scanner scanner = createScannerWithInput("run");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            assertTrue(output.contains("runs from the battle"));
            assertTrue(player.isAlive());
            assertTrue(monster.isAlive());
        }

        @Test
        @DisplayName("should preserve player health when running")
        void shouldPreservePlayerHealthWhenRunning() {
            Player player = new Player("Hero", 10);
            Monster monster = new Monster("Goblin", 30, 5);
            Scanner scanner = createScannerWithInput("run");

            int healthBefore = player.getHealth();
            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            assertEquals(healthBefore, player.getHealth());
        }

        @Test
        @DisplayName("should preserve monster health when player runs")
        void shouldPreserveMonsterHealthWhenPlayerRuns() {
            Player player = new Player("Hero", 10);
            Monster monster = new Monster("Goblin", 30, 5);
            Scanner scanner = createScannerWithInput("run");

            int healthBefore = monster.getHealth();
            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            assertEquals(healthBefore, monster.getHealth());
        }

        @Test
        @DisplayName("should be case insensitive for run command")
        void shouldBeCaseInsensitiveForRunCommand() {
            Player player = new Player("Hero", 10);
            Monster monster = new Monster("Goblin", 30, 5);
            Scanner scanner = createScannerWithInput("RUN");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            assertTrue(output.contains("runs from the battle"));
        }
    }

    @Nested
    @DisplayName("Invalid Commands")
    class InvalidCommands {

        @Test
        @DisplayName("should reject invalid command and prompt again")
        void shouldRejectInvalidCommandAndPromptAgain() {
            Player player = new Player("Hero", 10);
            Monster monster = new Monster("Goblin", 30, 5);
            Scanner scanner = createScannerWithInput("jump", "run");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            assertTrue(output.contains("is not a valid choice"));
            assertTrue(output.contains("runs from the battle"));
        }

        @Test
        @DisplayName("should handle multiple invalid commands before valid one")
        void shouldHandleMultipleInvalidCommandsBeforeValidOne() {
            Player player = new Player("Hero", 10);
            Monster monster = new Monster("Goblin", 30, 5);
            Scanner scanner = createScannerWithInput("attack", "flee", "escape", "run");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            long invalidCount = output.lines()
                    .filter(line -> line.contains("is not a valid choice"))
                    .count();
            assertEquals(3, invalidCount);
        }
    }

    @Nested
    @DisplayName("Fight Command - Player Wins")
    class FightCommandPlayerWins {

        @Test
        @DisplayName("should allow player to kill weak monster")
        void shouldAllowPlayerToKillWeakMonster() {
            Player player = new Player("Hero", 100);
            Monster monster = new Monster("Rat", 10, 5);
            Scanner scanner = createScannerWithInput("fight");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            assertTrue(output.contains("is dead"));
            assertFalse(monster.isAlive());
            assertTrue(player.isAlive());
        }

        @Test
        @DisplayName("should show monster death message")
        void shouldShowMonsterDeathMessage() {
            Player player = new Player("Hero", 50);
            Monster monster = new Monster("Goblin", 30, 5);
            Scanner scanner = createScannerWithInput("fight");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            assertTrue(output.contains("Goblin") && output.contains("is dead"));
        }
    }

    @Nested
    @DisplayName("Fight Command - Monster Wins")
    class FightCommandMonsterWins {

        @Test
        @DisplayName("should allow monster to kill player")
        void shouldAllowMonsterToKillPlayer() {
            Player player = new Player("Hero", 5);
            Monster monster = new Monster("Dragon", 500, 200);
            Scanner scanner = createScannerWithInput("fight");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            assertTrue(output.contains("is dead"));
            assertFalse(player.isAlive());
            assertTrue(monster.isAlive());
        }

        @Test
        @DisplayName("should show player death message")
        void shouldShowPlayerDeathMessage() {
            Player player = new Player("Hero", 5);
            Monster monster = new Monster("Dragon", 500, 200);
            Scanner scanner = createScannerWithInput("fight");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            assertTrue(output.contains("Hero") && output.contains("is dead"));
        }
    }

    @Nested
    @DisplayName("Combat Turn Order")
    class CombatTurnOrder {

        @Test
        @DisplayName("monster attacks first when monster damage is higher")
        void monsterAttacksFirstWhenMonsterDamageIsHigher() {
            Player player = new Player("Hero", 10);
            Monster monster = new Monster("Dragon", 100, 50);
            Scanner scanner = createScannerWithInput("fight", "run");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            int monsterAttackPos = output.indexOf("Dragon attacks");
            int playerAttackPos = output.indexOf("Hero is attacking");
            assertTrue(monsterAttackPos < playerAttackPos,
                    "Monster should attack before player when monster has higher damage");
        }

        @Test
        @DisplayName("player attacks first when player damage is higher or equal")
        void playerAttacksFirstWhenPlayerDamageIsHigherOrEqual() {
            Player player = new Player("Hero", 50);
            Monster monster = new Monster("Goblin", 100, 10);
            Scanner scanner = createScannerWithInput("fight", "run");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            int playerAttackPos = output.indexOf("Hero is attacking");
            int monsterAttackPos = output.indexOf("Goblin attacks");
            assertTrue(playerAttackPos < monsterAttackPos,
                    "Player should attack before monster when player has higher damage");
        }
    }

    @Nested
    @DisplayName("Multiple Combat Rounds")
    class MultipleCombatRounds {

        @Test
        @DisplayName("should handle multi-round combat until monster dies")
        void shouldHandleMultiRoundCombatUntilMonsterDies() {
            Player player = new Player("Hero", 20);
            Monster monster = new Monster("Goblin", 50, 10);
            Scanner scanner = createScannerWithInput("fight", "fight", "fight");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            assertFalse(monster.isAlive());
            assertTrue(player.isAlive());
        }

        @Test
        @DisplayName("should handle multi-round combat until player dies")
        void shouldHandleMultiRoundCombatUntilPlayerDies() {
            Player player = new Player("Hero", 10);
            Monster monster = new Monster("Dragon", 200, 30);
            Scanner scanner = createScannerWithInput("fight", "fight", "fight", "fight");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            assertFalse(player.isAlive());
            assertTrue(monster.isAlive());
        }

        @Test
        @DisplayName("should allow running mid-combat")
        void shouldAllowRunningMidCombat() {
            Player player = new Player("Hero", 15);
            Monster monster = new Monster("Goblin", 100, 20);
            Scanner scanner = createScannerWithInput("fight", "fight", "run");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            assertTrue(player.isAlive());
            assertTrue(monster.isAlive());
            assertTrue(player.getHealth() < 100, "Player should have taken damage");
            assertTrue(monster.getHealth() < 100, "Monster should have taken damage");
        }
    }

    @Nested
    @DisplayName("Health Display")
    class HealthDisplay {

        @Test
        @DisplayName("should display player health at start of combat")
        void shouldDisplayPlayerHealthAtStartOfCombat() {
            Player player = new Player("Hero", 10);
            Monster monster = new Monster("Goblin", 30, 5);
            Scanner scanner = createScannerWithInput("run");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            assertTrue(output.contains("Hero has 100 health"));
        }

        @Test
        @DisplayName("should display monster health at start of combat")
        void shouldDisplayMonsterHealthAtStartOfCombat() {
            Player player = new Player("Hero", 10);
            Monster monster = new Monster("Goblin", 30, 5);
            Scanner scanner = createScannerWithInput("run");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            String output = getCapturedOutput();
            assertTrue(output.contains("Goblin has 30 health"));
        }
    }

    @Nested
    @DisplayName("Case Sensitivity")
    class CaseSensitivity {

        @Test
        @DisplayName("should accept FIGHT in uppercase")
        void shouldAcceptFightInUppercase() {
            Player player = new Player("Hero", 100);
            Monster monster = new Monster("Rat", 10, 5);
            Scanner scanner = createScannerWithInput("FIGHT");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            assertFalse(monster.isAlive());
        }

        @Test
        @DisplayName("should accept Fight in mixed case")
        void shouldAcceptFightInMixedCase() {
            Player player = new Player("Hero", 100);
            Monster monster = new Monster("Rat", 10, 5);
            Scanner scanner = createScannerWithInput("Fight");

            CombatPlayerVSMonster.PerformBattle(player, monster, scanner);

            assertFalse(monster.isAlive());
        }
    }
}

package dk.viprogram.week03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for IGameMode implementations
 *
 * Compare this to Week 2's GameModeTest!
 * Notice how much cleaner and more focused these tests are.
 */
public class GameModeTest {
    private IGameMode survivalMode;

    @BeforeEach
    public void setUp() {
        survivalMode = new SurvivalMode(5);  // Max 5 waves
    }

    @Test
    public void testGameModeInitialization() {
        survivalMode.initialize();
        assertFalse(survivalMode.isComplete());
        assertEquals("Survival Mode", survivalMode.getModeName());
    }

    @Test
    public void testPlayingRounds() {
        survivalMode.initialize();

        // Play some rounds
        int roundsPlayed = 0;
        while (!survivalMode.isComplete() && roundsPlayed < 10) {
            survivalMode.playRound();
            roundsPlayed++;
        }

        // Game should be complete
        assertTrue(survivalMode.isComplete());
        assertNotNull(survivalMode.getResult());
    }

    @Test
    public void testNoIfElseNeeded() {
        // This method works with ANY game mode!
        runGameMode(survivalMode);

        // If we had other modes:
        // runGameMode(storyMode);
        // runGameMode(arenaMode);
        // No if-else needed!
    }

    // Generic method that works with ANY IGameMode implementation
    private void runGameMode(IGameMode mode) {
        mode.initialize();
        while (!mode.isComplete()) {
            boolean continueGame = mode.playRound();
            if (!continueGame) {
                break;
            }
        }
        System.out.println(mode.getResult());
    }

    @Test
    public void testEasyMocking() {
        // We can create a mock game mode for testing!
        IGameMode testMode = new IGameMode() {
            private int rounds = 0;

            @Override
            public void initialize() { rounds = 0; }

            @Override
            public boolean playRound() {
                rounds++;
                return rounds < 3;
            }

            @Override
            public boolean isComplete() { return rounds >= 3; }

            @Override
            public String getResult() { return "Test complete"; }

            @Override
            public String getModeName() { return "Test Mode"; }
        };

        // Use it just like any other mode
        runGameMode(testMode);
        assertTrue(testMode.isComplete());
    }

    @Test
    public void testPolymorphicBehavior() {
        IGameMode mode = survivalMode;  // Interface variable

        // Could swap implementation without changing code
        // mode = new StoryMode();
        // mode = new ArenaMode();

        mode.initialize();
        assertFalse(mode.isComplete());
    }
}
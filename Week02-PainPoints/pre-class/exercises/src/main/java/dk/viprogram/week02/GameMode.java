package dk.viprogram.week02;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Exercise 2: Multiple Game Modes
 *
 * Try to add different game modes to your RPG game.
 * You'll discover that supporting multiple modes makes the code complex and fragile.
 *
 * Game Modes:
 * 1. STORY - Linear progression through predetermined encounters
 * 2. SURVIVAL - Endless waves of increasingly difficult enemies
 * 3. ARENA - PvP mode where two players fight each other
 *
 * TODO: Implement the different game modes
 * TODO: Notice how much duplication you need
 * TODO: Consider what happens when you want to add a fourth mode
 */
public class GameMode {
    private String mode;  // "STORY", "SURVIVAL", or "ARENA"
    private EnhancedCharacter player1;
    private EnhancedCharacter player2;  // Only used in ARENA mode
    private ArrayList<String> storyEncounters;
    private int survivalWave;
    private int score;

    public GameMode(String mode) {
        this.mode = mode;
        this.storyEncounters = new ArrayList<>();
        this.survivalWave = 1;
        this.score = 0;

        // TODO: Initialize based on mode
        if (mode.equals("STORY")) {
            initializeStoryMode();
        } else if (mode.equals("SURVIVAL")) {
            initializeSurvivalMode();
        } else if (mode.equals("ARENA")) {
            initializeArenaMode();
        }
        // Notice: What if we add more modes?
    }

    private void initializeStoryMode() {
        // TODO: Set up story encounters
        storyEncounters.add("Goblin");
        storyEncounters.add("Orc");
        storyEncounters.add("Dragon");
    }

    private void initializeSurvivalMode() {
        // TODO: Set up survival mode
        // Waves get progressively harder
    }

    private void initializeArenaMode() {
        // TODO: Set up PvP arena
        // Need two players
    }

    /**
     * Run one round of the game based on the current mode
     *
     * TODO: Implement different logic for each mode
     * Notice: How much conditional logic is needed?
     */
    public void playRound(Scanner scanner) {
        // TODO: Different logic for each mode
        if (mode.equals("STORY")) {
            playStoryRound(scanner);
        } else if (mode.equals("SURVIVAL")) {
            playSurvivalRound(scanner);
        } else if (mode.equals("ARENA")) {
            playArenaRound(scanner);
        }
        // What happens when we add achievements? Leaderboards? Daily challenges?
    }

    private void playStoryRound(Scanner scanner) {
        // TODO: Implement story mode round
        // Progress through predetermined encounters
    }

    private void playSurvivalRound(Scanner scanner) {
        // TODO: Implement survival mode round
        // Face waves of enemies
        // Increase difficulty each wave
    }

    private void playArenaRound(Scanner scanner) {
        // TODO: Implement PvP round
        // Two players take turns
    }

    /**
     * Check if the game is over based on the mode
     *
     * TODO: Different end conditions for each mode
     */
    public boolean isGameOver() {
        // TODO: Different end conditions for each mode
        if (mode.equals("STORY")) {
            // Game over when all encounters are complete or player dies
            return false;
        } else if (mode.equals("SURVIVAL")) {
            // Game over when player dies
            return false;
        } else if (mode.equals("ARENA")) {
            // Game over when one player defeats the other
            return false;
        }
        return true;
    }

    /**
     * Get the score/result based on the mode
     *
     * TODO: Different scoring for each mode
     */
    public String getResult() {
        // TODO: Return appropriate result based on mode
        return "Game Over";
    }

    // Getters
    public String getMode() { return mode; }
    public int getSurvivalWave() { return survivalWave; }
    public int getScore() { return score; }
}